package com.impostoCalc.service;

import com.impostoCalc.dtos.request.UserRegisterRequestDTO;
import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
import com.impostoCalc.dtos.request.UserLoginRequestDTO;
import com.impostoCalc.dtos.response.UserLoginResponseDTO;

public interface UserServiceI {
    UserRegisterResponseDTO registerUser(UserRegisterRequestDTO requestDTO);
    UserLoginResponseDTO authenticateUser(UserLoginRequestDTO userLoginRequestDTO);
}