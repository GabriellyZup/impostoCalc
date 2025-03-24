package com.impostoCalc.service;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.dtos.request.UserLoginRequestDTO;
import com.impostoCalc.dtos.request.UserRegisterRequestDTO;
import com.impostoCalc.dtos.response.UserLoginResponseDTO;
import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import com.impostoCalc.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTO requestDTO) {
        Optional<User> user = userRepository.findByUsername(requestDTO.getUsername());
        if (user.isPresent()) {
            throw new RuntimeException("Nome de usuário já existe.");
        }

//        try {
//            String roleStr = "";
//            role = Role.valueOf(roleStr);
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException("Papel inválido. Use 'ADMIN' ou 'USER'.");
//        }

        User newUser = new User();
        newUser.setUsername(requestDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        newUser.setRole(requestDTO.getRole()); // Uso direto do enum
        userRepository.save(newUser);

        return new UserRegisterResponseDTO(newUser.getId(), newUser.getUsername(), newUser.getRole());
    }

//    @Override
//    public UserLoginResponseDTO authenticateUser(UserLoginRequestDTO userLoginRequestDTO) {
//        User user = userRepository.findByUsername(userLoginRequestDTO.getUsername())
//                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
//
//        if (passwordEncoder.matches(userLoginRequestDTO.getPassword(), user.getPassword())) {
//            String token = tokenService.generateToken(user);
//            return new UserLoginResponseDTO(token);
//        }
//
//        throw new RuntimeException("Credenciais inválidas.");
//    }


    //    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTO requestDTO) {
//        if (userRepository.findByUsername(requestDTO.getUsername()) != null) {
//            throw new RuntimeException("Nome de usuário já existe.");
//        }
//
//        if (requestDTO.getUserRole() == null || (!requestDTO.getUserRole().equals(UserRole.USER) && !requestDTO.getUserRole().equals(UserRole.ADMIN))) {
//            throw new RuntimeException("Papel inválido. Deve ser USER ou ADMIN.");
//        }
//
//        User user = new User();
//        user.setUsername(requestDTO.getUsername());
//        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
//        user.setUserRole(requestDTO.getUserRole());
//
//        User savedUser = userRepository.save(user);
//        return toResponseDTO(savedUser);
//    }
//
    @Override

    public UserLoginResponseDTO authenticateUser(UserLoginRequestDTO userLoginRequestDTO) {
        User user = userRepository.findByUsername(userLoginRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(userLoginRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciais inválidas.");
        }

        return new UserLoginResponseDTO(tokenService.generateToken(user));
    }


    private UserRegisterResponseDTO toResponseDTO(User user) {
        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setRole(user.getRole());
        return responseDTO;
    }
}