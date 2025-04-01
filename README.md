# impostoCalc
![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green?style=for-the-badge&logo=spring)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.x-blue?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-24.0.2-blue?style=for-the-badge&logo=docker)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-✔️-black?style=for-the-badge&logo=github)
![Swagger](https://img.shields.io/badge/Swagger-3.0-green?style=for-the-badge&logo=swagger)
![JWT](https://img.shields.io/badge/JWT-0.11.5-orange?style=for-the-badge&logo=json-web-tokens)
![Maven](https://img.shields.io/badge/Maven-3.8.1-red?style=for-the-badge&logo=apache-maven)
![JUnit](https://img.shields.io/badge/JUnit-5.8.2-green?style=for-the-badge&logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-4.5.1-blue?style=for-the-badge&logo=mockito)

API RESTful para gerenciamento e cálculo de impostos no Brasil. Desenvolvida com **Java 17**, **Spring Boot**, **Spring Security**, e integração com **Docker** e **GitHub Actions** (em ajustes). **Princípios adotados:** Clean Code, SOLID, POO.

---

## 🔍 Visão Geral
A API foi desenvolvida para gerenciar e calcular impostos no Brasil. Ela permite:
- Gerenciar tipos de impostos (ICMS, ISS, IPI, etc.).
- Calcular o valor do imposto com base no tipo e no valor base fornecido.
- Autenticar e autorizar usuários utilizando JWT.
- Restringir o acesso a endpoints sensíveis com base no papel do usuário (`USER` ou `ADMIN`).
- Documentação interativa via Swagger

---

## 🛠️ Tecnologias Utilizadas
| Categoria          | Ferramentas                                                                 |
|--------------------|-----------------------------------------------------------------------------|
| Backend            | [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), [Spring Boot](https://spring.io/projects/spring-boot), [Spring Data JPA](https://spring.io/projects/spring-data-jpa), [Spring Security](https://spring.io/projects/spring-security) |
| Banco de Dados     | [PostgreSQL](https://www.postgresql.org/)                                   |
| Autenticação       | [JWT](https://jwt.io/)                                                     |
| Testes             | [JUnit 5](https://junit.org/junit5/), [Mockito](https://site.mockito.org/), [JaCoCo](https://www.eclemma.org/jacoco/) |
| Containerização    | [Docker](https://www.docker.com/), [Maven Jib Plugin](https://github.com/GoogleContainerTools/jib) |
| CI/CD              | [GitHub Actions](https://github.com/features/actions)                      |
| Documentação       | [Swagger](https://swagger.io/)                                              |
| Build              | [Maven](https://maven.apache.org/)                                         |

---
## Funcionalidades
- ✅ **Gerenciamento de impostos** (CRUD de tipos como ICMS, ISS, IPI).
- 🧮 **Cálculo automático** de impostos com base na alíquota.
- 🔒 **Autenticação JWT** com roles (`USER` e `ADMIN`).
- 📚 **Documentação interativa** via Swagger.
- 🐳 **Containerização** via Docker (configuração inicial).
- 🤖 **CI/CD** com GitHub Actions (em ajustes).

---

## Instruções para Executar o Código

## Pré-requisitos
Antes de executar o projeto, certifique-se de ter:
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) instalado.
- [Maven](https://maven.apache.org/download.cgi) instalado.
- [PostgreSQL](https://www.postgresql.org/download/) configurado e rodando


## 🚀 Executando o Projeto
1. **Clone o repositório:**
```bash
        git clone https://github.com/GabriellyZup/impostoCalc.git
        cd impostoCalc
```

2. **Configure as variáveis de ambiente:**
Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:
``` env
     SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/impostoCalc
     SPRING_DATASOURCE_USERNAME=postgres
     SPRING_DATASOURCE_PASSWORD=postgres
     JWT_SECRET=secret-key-123456
```

3. **Execute:**
```bash
      mvn spring-boot:run
```

4. A aplicação estará disponível em: [http://localhost:8081](http://localhost:8081)

## 📚 Documentação da API:
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


## 🔐 Endpoints Principais

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
# 🐳 Containerização
O projeto usa o **Maven Jib Plugin** para construir imagens OCI (compatíveis com Podman/Docker):

## Build da imagem:
```bash
        mvn compile jib:dockerBuild -Dimage=impostocalc:latest
```

## Execute com Podman:


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



# 🤖 CI/CD (GitHub Actions)
O fluxo de CI inclui:
- Testes com PostgreSQL em container
- Build da aplicação
- Cobertura de testes via JaCoCo
## Arquivo: .github/workflows/ci.yml
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

# ⁉️ Problemas Conhecidos
- **Containerização**: A integração com PostgreSQL via Docker requer ajustes nas variáveis de ambiente.
- **CI/CD**: Testes end-to-end ainda não implementados.

---

## Contribuição

Para contribuir com o projeto, siga os passos abaixo:

### Faça um fork do projeto

### Crie uma branch:
```bash
        git checkout -b feature/nova-feature
```

### Commit suas mudanças:
```bash
        git commit -m 'Adiciona nova feature'
```

### Push para a branch:
```bash
        git push origin feature/nova-feature
```

### Abra um Pull Request