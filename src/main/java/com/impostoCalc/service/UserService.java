package com.impostoCalc.service;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.Role;
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

    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.getUsername()) != null) {
            throw new RuntimeException("Nome de usuário já existe.");
        }

        if (requestDTO.getRole() == null || (!requestDTO.getRole().equals(Role.USER) && !requestDTO.getRole().equals(Role.ADMIN))) {
            throw new RuntimeException("Papel inválido. Deve ser USER ou ADMIN.");
        }

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setRole(requestDTO.getRole());

        User savedUser = userRepository.save(user);
        return toResponseDTO(savedUser);
    }

    public UserResponseDTO loginUser(UserRequestDTO requestDTO) {
        User user = userRepository.findByUsername(requestDTO.getUsername());
        if (user == null || !passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciais inválidas.");
        }
        return toResponseDTO(user);
    }



    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setRole(user.getRole());
        return responseDTO;
    }
}