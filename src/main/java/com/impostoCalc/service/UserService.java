package com.impostoCalc.service;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.getUsername()) != null) {
            throw new RuntimeException("Nome de usuário já existe.");
        }

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword()); // Sem hashing por enquanto
        user.setRole(requestDTO.getRole());

        User savedUser = userRepository.save(user);

        return toResponseDTO(savedUser);
    }

    public UserResponseDTO loginUser(UserRequestDTO requestDTO) {
        User user = userRepository.findByUsername(requestDTO.getUsername());
        if (user == null || !user.getPassword().equals(requestDTO.getPassword())) {
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