# Bank Account Service

This project is a Spring Boot application designed to manage bank accounts. It provides a microservice with both RESTful and GraphQL APIs for performing CRUD (Create, Read, Update, Delete) operations. The service uses an in-memory H2 database and includes initial data seeding for demonstration purposes.

## Features

*   **Dual APIs:** Offers both REST and GraphQL APIs for managing bank accounts.
*   **Data Persistence:** Uses Spring Data JPA with an in-memory H2 database.
*   **DTO Pattern:** Employs Data Transfer Objects (`BankAccountRequestDTO`, `BankAccountResponseDTO`) to separate the API layer from the domain model.
*   **Automatic REST Endpoints:** Leverages Spring Data REST to expose repository-based endpoints.
*   **API Documentation:** Integrated with SpringDoc to provide an interactive Swagger UI for the REST API.
*   **GraphQL IDE:** Includes the GraphiQL in-browser IDE for exploring and testing GraphQL queries and mutations.

## Technologies Used

*   Java 17
*   Spring Boot
*   Spring Web (MVC)
*   Spring Data JPA
*   Spring GraphQL
*   Spring Data REST
*   H2 Database
*   Lombok
*   Maven
*   SpringDoc OpenAPI (Swagger UI)

## Getting Started

Follow these instructions to get a local copy of the project up and running.

### Prerequisites

*   JDK 17 or later
*   Apache Maven

### Installation & Running

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/FatimaZahraLasfar/bank-account-service.git
    ```

2.  **Navigate to the project directory:**
    ```sh
    cd bank-account-service
    ```

3.  **Run the application using the Maven wrapper:**
    *   On macOS/Linux:
        ```sh
        ./mvnw spring-boot:run
        ```
    *   On Windows:
        ```sh
        ./mvnw.cmd spring-boot:run
        ```

The application will start on `http://localhost:8081`.

### Accessing the Service

Once the application is running, you can access the following tools:

*   **H2 Console:** `http://localhost:8081/h2-console`
    *   **JDBC URL:** `jdbc:h2:mem:account-db`
*   **Swagger UI (REST API):** `http://localhost:8081/swagger-ui.html`
*   **GraphiQL (GraphQL API):** `http://localhost:8081/graphiql`

## API Endpoints

The application exposes both REST and GraphQL endpoints.

### REST API

The custom REST API is available under the `/api` prefix.

| Method | Endpoint                 | Description                     |
|--------|--------------------------|---------------------------------|
| `GET`  | `/api/bankAccounts`      | Retrieve all bank accounts.     |
| `GET`  | `/api/bankAccounts/{id}` | Retrieve a specific account.    |
| `POST` | `/api/bankAccounts`      | Create a new bank account.      |
| `PUT`  | `/api/bankAccounts/{id}` | Update an existing account.     |
| `DELETE`|`/api/bankAccounts/{id}` | Delete a bank account.          |

**Example `POST /api/bankAccounts` Request Body:**
```json
{
    "balance": 50000.0,
    "currency": "USD",
    "type": "SAVING_ACCOUNT"
}
```

### Spring Data REST API

Spring Data REST automatically creates endpoints from the `BankAccountRepository`.

| Method | Endpoint                                 | Description                                                 |
|--------|------------------------------------------|-------------------------------------------------------------|
| `GET`  | `/bankAccounts`                          | Retrieve all accounts with a projection.                    |
| `GET`  | `/bankAccounts/{id}`                     | Retrieve a single account.                                  |
| `GET`  | `/bankAccounts/search/byType?t={type}`   | Find accounts by type (`CURRENT_ACCOUNT` or `SAVING_ACCOUNT`).|

### GraphQL API

Interact with the GraphQL API using the GraphiQL interface at `http://localhost:8081/graphiql`.

**Example Queries:**

*   **List all accounts:**
    ```graphql
    query {
      accountsList {
        id
        balance
        currency
        type
        createdAt
      }
    }
    ```

*   **Get a single account by ID:**
    ```graphql
    query {
      bankAccountById(id: "your-account-id") {
        id
        balance
        type
        currency
      }
    }
    ```

**Example Mutation:**

*   **Add a new account:**
    ```graphql
    mutation {
      addAccount(bankAccount: {
        balance: 75000.00,
        currency: "EUR",
        type: "CURRENT_ACCOUNT"
      }) {
        id
        createdAt
        balance
        currency
        type
      }
    }
    ```

## Project Structure
```
.
├── src
│   ├── main
│   │   ├── java/org/fzl/bankaccountservice
│   │   │   ├── dto          # Data Transfer Objects
│   │   │   ├── entities     # JPA Entities (BankAccount)
│   │   │   ├── enums        # AccountType enum
│   │   │   ├── mappers      # DTO-to-Entity mapping logic
│   │   │   ├── repositories # Spring Data JPA Repositories
│   │   │   ├── service      # Business logic implementation
│   │   │   └── web          # REST and GraphQL controllers
│   │   └── resources
│   │       ├── application.properties # Main configuration file
│   │       └── graphql
│   │           └── schema.graphqls    # GraphQL schema definition
└── pom.xml                  # Maven project configuration
