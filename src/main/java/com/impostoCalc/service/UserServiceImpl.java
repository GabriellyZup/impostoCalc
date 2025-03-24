package com.impostoCalc.service;

import com.impostoCalc.dtos.UserRole;
import com.impostoCalc.dtos.request.UserRegisterRequestDTO;
import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
import com.impostoCalc.dtos.request.UserRegisterResponseDTO;
import com.impostoCalc.dtos.response.UserLoginResponseDTO;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.getUsername()) != null) {
            throw new RuntimeException("Nome de usuário já existe.");
        }

        if (requestDTO.getUserRole() == null || (!requestDTO.getUserRole().equals(UserRole.USER) && !requestDTO.getUserRole().equals(UserRole.ADMIN))) {
            throw new RuntimeException("Papel inválido. Deve ser USER ou ADMIN.");
        }

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setUserRole(requestDTO.getUserRole());

        User savedUser = userRepository.save(user);
        return toResponseDTO(savedUser);
    }

    public UserRegisterResponseDTO loginUser(UserRegisterRequestDTO requestDTO) {
        User user = userRepository.findByUsername(requestDTO.getUsername());
        if (user == null || !passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciais inválidas.");
        }
        return toResponseDTO(user);
    }



    private UserRegisterResponseDTO toResponseDTO(User user) {
        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setRole(user.getUserRole());
        return responseDTO;
    }
}