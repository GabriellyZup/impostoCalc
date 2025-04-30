package com.impostoCalc.awsservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsável por encapsular operações do S3.
 * Todos os métodos retornam dados úteis para integração com controllers e Swagger.
 */
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    /**
     * Cria um bucket S3 com o nome informado.
     * @param bucketName Nome do bucket a ser criado.
     * @return true se criado com sucesso, false se já existe.
     */
    public boolean createBucket(String bucketName) {
        try {
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
            return true;
        } catch (S3Exception e) {
            // Se o bucket já existe, retorna false, senão relança a exceção
            if ("BucketAlreadyOwnedByYou".equals(e.awsErrorDetails().errorCode())) {
                return false;
            }
            throw e;
        }
    }

    /**
     * Faz upload de um arquivo para o bucket/key especificados.
     * @param bucketName Nome do bucket.
     * @param key Caminho/identificador do arquivo no bucket.
     * @param filePath Caminho local do arquivo a ser enviado.
     * @return true se upload foi bem-sucedido.
     */
    public boolean uploadFile(String bucketName, String key, Path filePath) {
        try {
            s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(key).build(), filePath);
            return true;
        } catch (S3Exception e) {
            // Log ou tratamento customizado pode ser adicionado aqui
            return false;
        }
    }

    /**
     * Lista todos os buckets S3 disponíveis.
     * @return Lista de nomes dos buckets.
     */
    public List<String> listBuckets() {
        ListBucketsResponse buckets = s3Client.listBuckets();
        // Retorna apenas os nomes dos buckets
        return buckets.buckets().stream()
                .map(Bucket::name)
                .collect(Collectors.toList());
    }

    /**
     * Lista todos os objetos (arquivos) de um bucket.
     * @param bucketName Nome do bucket.
     * @return Lista de chaves (nomes) dos objetos.
     */
    public List<String> listObjects(String bucketName) {
        ListObjectsV2Response response = s3Client.listObjectsV2(ListObjectsV2Request.builder().bucket(bucketName).build());
        return response.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }
}