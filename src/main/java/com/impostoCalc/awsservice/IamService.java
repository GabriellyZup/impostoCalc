package com.impostoCalc.awsservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsável por operações com IAM.
 * Retorna listas para integração com controllers e Swagger.
 */
@Service
@RequiredArgsConstructor
public class IamService {

    private final IamClient iamClient;

    /**
     * Lista todos os nomes de usuários IAM.
     * @return Lista de nomes de usuários IAM.
     */
    public List<String> listUsers() {
        ListUsersResponse response = iamClient.listUsers();
        // Retorna apenas os nomes dos usuários
        return response.users().stream()
                .map(User::userName)
                .collect(Collectors.toList());
    }
}