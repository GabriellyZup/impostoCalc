package com.impostoCalc.service;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;

public interface UserServiceImpl {
    UserResponseDTO registerUser(UserRequestDTO requestDTO);
}