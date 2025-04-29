# Usando JRE para imagem menor, pois só precisamos rodar o .jar (build/testes são feitos fora do container)
FROM openjdk:17-jre-alpine

WORKDIR /app

# Copiando o .jar gerado pelo build externo (Maven)
COPY target/impostoCalc-0.0.1-SNAPSHOT.jar app.jar

# Copiando arquivos de configuração necessários
COPY .env .
COPY application.properties .

EXPOSE 8081

# Rodando a aplicação Spring Boot
CMD ["java", "-jar", "app.jar"]

# O build do código, execução de testes e provisionamento do banco NÃO são feitos aqui.
# Isso deve ser feito fora do container ou via podman-compose.