# impostoCalc

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-✔️-blue?style=for-the-badge&logo=docker)
![Podman Compose](https://img.shields.io/badge/Podman--Compose-1.x-purple?style=for-the-badge&logo=podman)
![LocalStack](https://img.shields.io/badge/LocalStack-3.x-purple?style=for-the-badge&logo=amazon-aws)
![AWS](https://img.shields.io/badge/AWS-✔️-orange?style=for-the-badge&logo=amazon-aws)
![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-blue?style=for-the-badge&logo=githubactions)
![Swagger](https://img.shields.io/badge/Swagger-UI-green?style=for-the-badge&logo=swagger)
![JWT](https://img.shields.io/badge/JWT-Auth-yellow?style=for-the-badge&logo=jsonwebtokens)
![JUnit](https://img.shields.io/badge/JUnit-5-red?style=for-the-badge&logo=java)
![Mockito](https://img.shields.io/badge/Mockito-✔️-green?style=for-the-badge&logo=java)

---

## Índice
- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Execução do Projeto](#execução-do-projeto)
- [Documentação da API](#documentação-da-api)
- [Endpoints](#endpoints)
- [Integração AWS](#integração-aws)
- [Containerização](#containerização)
- [CI/CD](#cicd)
- [Testes via Swagger](#testes-via-swagger)
- [Contribuição](#contribuição)
---

## Visão Geral

API RESTful para gerenciamento e cálculo de impostos no Brasil. Desenvolvida com **Java 17**, **Spring Boot**, **Spring Security**, e integração com **Docker** e **GitHub Actions**.

Principais funcionalidades:
- Gerenciamento de tipos de impostos (ICMS, ISS, IPI)
- Cálculo automático de impostos
- Autenticação JWT com controle de acesso
- Integração com AWS via LocalStack
- Documentação interativa via Swagger

---

## Tecnologias Utilizadas

| Categoria         | Ferramentas                                                                 |
|-------------------|----------------------------------------------------------------------------|
| Backend           | Java 17, Spring Boot, Spring Data JPA, Spring Security                      |
| Banco de Dados    | PostgreSQL                                                                  |
| Autenticação      | JWT                                                                         |
| Testes            | JUnit 5, Mockito, JaCoCo                                                    |
| Containerização   | Docker, Podman, Maven Jib Plugin                                            |
| Orquestração      | podman-compose, docker-compose                                              |
| CI/CD             | GitHub Actions                                                              |
| Documentação      | Swagger                                                                     |
| AWS (Simulado)    | LocalStack (EC2, S3, IAM)                                                   |

---
### Pré-requisitos
Antes de executar o projeto, certifique-se de ter:
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Docker](https://www.docker.com/) / [Podman](https://podman.io/) (opcional)

## Execução do Projeto

1. Clone o repositório:

```bash
git clone https://github.com/GabriellyZup/impostoCalc.git
cd impostoCalc
```
2. Configure o arquivo .env:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/impostoCalc
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
JWT_SECRET=secret-key-123456
Execute com Maven:
```
```bash
mvn spring-boot:run
A aplicação estará disponível em: http://localhost:8081
```

3. **Execute:**
```bash
      mvn spring-boot:run
```

4. A aplicação estará disponível em: [http://localhost:8081](http://localhost:8081)

## Documentação da API:
Acesse o Swagger em: [http://localhost:8081/swagger-ui](http://localhost:8081/swagger-ui)

## Testando a API

### Obtenção do Token JWT:
1. Registre um usuário utilizando o endpoint `/user/register`.
2. Faça login no endpoint `/user/login` para obter o token JWT.
3. Copie o token retornado.

### Testando no Swagger:
1. Acesse o Swagger: [http://localhost:8081/swagger-ui](http://localhost:8081/swagger-ui).
2. Clique no botão **Authorize** no canto superior direito.
3. Insira o token no formato: `Bearer <seu_token>`.
4. Teste os endpoints conforme necessário.

## Exemplos de Entrada e Saída


## Endpoints Principais

### Autenticação
| Método | Endpoint           | Descrição                          | Acesso    |
|--------|--------------------|------------------------------------|-----------|
| POST   | `/user/login`      | Gera token JWT                     | Público   |
| POST   | `/user/register`   | Registra novos usuários            | Público   |




### POST /user/login
Autentica usuários e gera um token JWT.

**Entrada:**
   ```json
        {
          "username": "usuario123",
          "password": "senhaSegura"
        }
   ```

**Resposta (200):**
   ```json
        {
          "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        }
   ```

### POST /user/register
Registra novos usuários no sistema.

**Entrada:**
   ```json
        {
          "username": "usuario123",
          "password": "senhaSegura",
          "role": "USER"
        }
   ```

**Resposta (201):**
   ```json
        {
          "id": 1,
          "username": "usuario123",
          "role": "USER"
        }
   ```


### Gerenciamento de Impostos
| Método | Endpoint           | Descrição                          | Acesso    |
|--------|--------------------|------------------------------------|-----------|
| GET    | `/tipos`           | Lista todos os tipos de impostos   | Público   |
| POST   | `/tipos`           | Cria novo tipo de imposto          | ADMIN     |
| DELETE | `/tipos/{id}`      | Exclui tipo de imposto             | ADMIN 

### GET /tipos**
Retorna a lista de todos os tipos de impostos cadastrados.  
**Resposta (200):**
```json
        [
            {
                "id": 1,
                "nome": "ICMS",
                "descricao": "Imposto sobre Circulação de Mercadorias e Serviços",
                "aliquota": 18.0
            },
            {
                "id": 2,
                "nome": "ISS",
                "descricao": "Imposto sobre Serviços",
                "aliquota": 5.0
            }
        ]
   ```
### Criar Tipo de Imposto (POST /tipos)
**Entrada:**
```json
        {
          "nome": "IPI",
          "descricao": "Imposto sobre Produtos Industrializados",
          "aliquota": 12.0
        }
```

### GET /tipos/{id}
Retorna os detalhes de um tipo de imposto específico pelo ID.

**Resposta (200):**
```json
        {
          "id": 1,
          "nome": "ICMS",
          "descricao": "Imposto sobre Circulação de Mercadorias e Serviços",
          "aliquota": 18.0
        }
```

**Saída:**
```json
        {
          "id": 3,
          "nome": "IPI",
          "descricao": "Imposto sobre Produtos Industrializados",
          "aliquota": 12.0
        }
```

---

### DELETE /tipos/{id}
        Exclui um tipo de imposto pelo ID. **Acesso restrito ao papel ADMIN.**

**Resposta (204):** Sem conteúdo.


### Cálculo de Impostos
| Método | Endpoint           | Descrição                          | Acesso        |
|--------|--------------------|------------------------------------|---------------|
| POST   | `/calculo`         | Calcula valor do imposto           | USER, ADMIN   |


### Calcular Imposto (POST /calculo)
**Entrada:**
```json
        {
          "tipoImpostoId": 1,
          "valorBase": 1000.0
        }
```

**Saída:**
```json
        {
          "tipoImposto": "ICMS",
          "valorBase": 1000.0,
          "aliquota": 18.0,
          "valorImposto": 180.0
        }
```

## Estrutura de Integração AWS (EC2, S3, IAM)

O projeto possui classes de configuração, service e controller para EC2, S3 e IAM, usando AWS SDK v2. Os endpoints estão disponíveis em `/aws/ec2`, `/aws/s3`, `/aws/iam` e usam LocalStack por padrão.

**Exemplo de endpoints:**
```
Método	Endpoint	       Descrição
GET	/aws/ec2/instances  Lista instâncias EC2
GET	/aws/s3/buckets	    Lista buckets S3
GET	/aws/iam/users	    Lista usuários IAM
```

**Configuração de endpoints e credenciais AWS:**

No arquivo `.env` ou `application.properties`:

```properties
aws.region=us-east-1
aws.access-key=test
aws.secret-key=test
aws.s3.endpoint=http://localstack:4566
aws.ec2.endpoint=http://localstack:4566
aws.iam.endpoint=http://localstack:4566


## Containerização
O projeto usa o **Maven Jib Plugin** para construir imagens OCI (compatíveis com Podman/Docker):

### Build da imagem:
```bash
        mvn compile jib:dockerBuild -Dimage=impostocalc:latest
```

### Execute com Podman:


```yaml

version: '3'
services:
    app:
      image: impostocalc:latest
      ports:
        - "8081:8081"
      environment:
        - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/impostocalc
        - SPRING_DATASOURCE_USERNAME=postgres
        - SPRING_DATASOURCE_PASSWORD=postgres
      depends_on:
        - db

    db:
      image: postgres:13
      environment:
        - POSTGRES_DB=impostocalc
        - POSTGRES_PASSWORD=postgres
      ports:
        - "5432:5432"
```



## CI/CD (GitHub Actions)
O fluxo de CI inclui:
- Testes com PostgreSQL em container
- Build da aplicação
- Cobertura de testes via JaCoCo
### Arquivo: .github/workflows/ci.yml
```yaml
        name: CI

        on: [push]

        jobs:
          build:
            runs-on: ubuntu-latest
            steps:
              - uses: actions/checkout@v2
              - name: Set up JDK 17
                uses: actions/setup-java@v2
                with:
                  java-version: '17'
                  distribution: 'temurin'
              - name: Build with Maven
                run: mvn clean install
```                
                
                
























  