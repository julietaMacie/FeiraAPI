# FeiraAPI

API REST para gestão de feiras, expositores, estandes, visitantes e inscrições. Desenvolvida com **Spring Boot**, oferece autenticação JWT, controlo de acesso baseado em roles, e endpoints bem definidos para operações públicas e protegidas.

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security (JWT)
- Spring Web
- Spring Data JPA
- Hibernate
- Jakarta Validation
- Swagger / OpenAPI
- BCrypt para hashing de senhas
- Base de dados relacional (MySQL)

---

## 📌 Funcionalidades Principais

### 🔐 Autenticação e Autorização
- Registo de utilizadores (Visitantes ou Expositores)
- Login com retorno de token JWT
- Protecção de endpoints com base em roles (`VISITANTE`, `EXPOSITOR`)
- Stateless Authentication via JWT

### 🏢 Feiras
- Listagem pública de feiras
- Criação, actualização e remoção (acesso exclusivo ao `EXPOSITOR`)

### 🧍 Expositores
- Listagem e visualização pública
- Remoção (somente pelo próprio `EXPOSITOR`)

### 📦 Estandes
- Listagem e visualização pública
- Criação, atualização e remoção por `EXPOSITOR`

### 🧾 Inscrições
- Criação de inscrições por `VISITANTE`
- Listagem de inscrições por feira ou visitante
- Visualização e remoção por `VISITANTE` ou `EXPOSITOR` autorizado

### 👥 Visitantes
- Listagem restrita ao `EXPOSITOR`
- Visualização própria e por expositores
- Remoção permitida ao `VISITANTE` ou `EXPOSITOR` autorizado

---

## 📑 Endpoints Principais

### 🔐 Autenticação
| Método | Rota             | Acesso     | Descrição          |
|--------|------------------|------------|--------------------|
| POST   | `/auth/login`    | Público    | Login              |
| POST   | `/auth/register` | Público    | Registo de conta   |

### 🏢 Feiras
| Método | Rota             | Acesso     |
|--------|------------------|------------|
| GET    | `/feiras`        | Público    |
| GET    | `/feiras/{id}`   | Público    |
| POST   | `/feiras`        | EXPOSITOR  |
| PUT    | `/feiras/{id}`   | EXPOSITOR  |
| DELETE | `/feiras/{id}`   | EXPOSITOR  |

### 📦 Estandes
| Método | Rota                | Acesso     |
|--------|---------------------|------------|
| GET    | `/estandes`         | Público    |
| GET    | `/estandes/{id}`    | Público    |
| POST   | `/estandes`         | EXPOSITOR  |
| PUT    | `/estandes/{id}`    | EXPOSITOR  |
| DELETE | `/estandes/{id}`    | EXPOSITOR  |

### 👤 Expositores
| Método | Rota                 | Acesso     |
|--------|----------------------|------------|
| GET    | `/expositores`       | Público    |
| GET    | `/expositores/{id}`  | Público    |
| DELETE | `/expositores/{id}`  | EXPOSITOR  |

### 🧾 Inscrições
| Método | Rota                                 | Acesso                        |
|--------|--------------------------------------|-------------------------------|
| GET    | `/inscricoes`                        | EXPOSITOR                     |
| GET    | `/inscricoes/{id}`                   | VISITANTE, EXPOSITOR         |
| POST   | `/inscricoes`                        | VISITANTE                    |
| GET    | `/inscricoes/feira/{feiraId}`        | Público                      |
| GET    | `/inscricoes/visitante/{visitanteId}`| VISITANTE, EXPOSITOR         |
| DELETE | `/inscricoes/{id}`                   | VISITANTE, EXPOSITOR         |

---

## 🛡️ Segurança

A API utiliza autenticação JWT com filtros personalizados e roles:

- `VISITANTE`: pode inscrever-se, ver feiras, seus dados e cancelar inscrições.
- `EXPOSITOR`: pode criar e gerir feiras, estandes, visualizar inscrições e visitantes.

### Filtro de segurança

- `SecurityFilterChain` define claramente os endpoints públicos e os protegidos.
- Stateless (sem sessões) para maior escalabilidade.
- BCrypt para codificação de senhas.

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Maven ou Gradle
- PostgreSQL ou outro RDBMS
- IDE (ex: IntelliJ IDEA, VS Code)

### Passos

1. Clonar o repositório:

git clone https://github.com/julietaMacie/FeiraAPI.git
cd FeiraAPI

---

### Configurar o base de dados em application.properties` ou `application.yml`

### Usando application.properties

-`properties`

spring.datasource.url=jdbc:postgresql://localhost:5432/feira-api  
spring.datasource.username=seu_usuario  
spring.datasource.password=sua_senha  
spring.jpa.hibernate.ddl-auto=update 

-`Executar o projeto:`

./mvnw spring-boot:run

- `Aceder à documentação Swagger:`

http://localhost:8080/swagger-ui/index.html