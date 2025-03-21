//package com.impostoCalc.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.impostoCalc.model.User; // Alterado para usar a classe User do seu projeto
//import io.swagger.v3.oas.annotations.Operation; // Importação para documentar operações no Swagger
//import io.swagger.v3.oas.annotations.media.Schema; // Importação para descrever modelos no Swagger
//import io.swagger.v3.oas.annotations.responses.ApiResponse; // Importação para documentar respostas no Swagger
//import io.swagger.v3.oas.annotations.responses.ApiResponses; // Importação para múltiplas respostas no Swagger
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//import static org.springframework.security.config.Elements.JWT;
//
//@Service
//@Schema(description = "Serviço responsável pela geração e validação de tokens JWT.") // Adicionada descrição da classe no Swagger
//public class TokenService2 {
//
//    @Value("${api.security.token.secret}") //vai pra properties
//    private String secret;
//
//    @Operation(summary = "Gera um token JWT", description = "Este método gera um token JWT para autenticação com base no usuário fornecido.") // Adicionada documentação para o método
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Token gerado com sucesso."),
//            @ApiResponse(responseCode = "500", description = "Erro ao gerar o token.")
//    })
//    public String generateToken(User user) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            String token = JWT.create()
//                    .withIssuer("auth-api")
//                    .withSubject(user.getUsername()) // Alterado para usar o método getUsername() da sua classe User
//                    .withExpiresAt(genExpirationDate())
//                    .sign(algorithm);
//            return token;
//        } catch (JWTCreationException exception) {
//            throw new RuntimeException("Error while generating token", exception);
//        }
//    }
//
//    @Operation(summary = "Valida um token JWT", description = "Este método valida um token JWT e retorna o nome de usuário associado.") // Adicionada documentação para o método
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Token válido."),
//            @ApiResponse(responseCode = "401", description = "Token inválido ou expirado.")
//    })
//    public String validateToken(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            return JWT.require(algorithm)
//                    .withIssuer("impostoCalc")//quem foi o emissor
//                    .build()
//                    .verify(token)
//                    .getSubject(user.getUsername());//usuario
//        } catch (JWTVerificationException exception) {
//            return ""; // Retorna vazio em caso de token inválido
//        }
//    }
//
//    @Schema(description = "Gera a data de expiração do token JWT.") // Adicionada documentação para o método auxiliar
//    private Instant genExpirationDate() {
//        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
//    }
//}