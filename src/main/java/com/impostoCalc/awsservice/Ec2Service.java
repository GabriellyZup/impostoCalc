package com.impostoCalc.awsservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsável por operações com EC2.
 * Retorna listas para integração com controllers e Swagger.
 */
@Service
@RequiredArgsConstructor
public class Ec2Service {

    private final Ec2Client ec2Client;

    /**
     * Lista todos os IDs das instâncias EC2.
     * @return Lista de instanceId das instâncias EC2.
     */
    public List<String> listInstances() {
        DescribeInstancesResponse response = ec2Client.describeInstances();
        // Retorna todos os instanceId das instâncias encontradas
        return response.reservations().stream()
                .flatMap(reservation -> reservation.instances().stream())
                .map(Instance::instanceId)
                .collect(Collectors.toList());
    }
}