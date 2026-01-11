# Sales Management API

Sales Management API built with Spring Boot to manage **branches**, **products**, **categories**, **stock per branch**, and **sales**.

Features:
- **HTTP Basic Auth**
- **Role-based access control** (3 roles)
- **MySQL** persistence
- **Flyway migrations + seed**
- **Docker / Docker Compose**
- **Swagger/OpenAPI** documentation

---

## Tech Stack
- Java 17
- Spring Boot (Web, Validation, Data JPA)
- Spring Security (Basic Auth + Roles)
- MySQL 8
- Flyway
- Docker + Docker Compose
- springdoc-openapi (Swagger UI)

---

## Run with Docker (recommended)

### 1) Clone the repository
```bash
git clone https://github.com/Vickyrai01/sales-management-api.git
cd sales-management-api
```

### 2) Create `.env`
Create a `.env` file in the project root (you can copy `.env.example`):

```bash
cp .env.example .env
```

### 3) Start API + MySQL
```bash
docker compose up --build
```

- API: http://localhost:8080
- MySQL (host local): `localhost:3307`

### Stop / clean up
- Stop (keep data):
```bash
docker compose down
```

- Remove everything (including DB data):
```bash
docker compose down -v
```

---

## Environment Variables

### MySQL (container)
Configured via Docker Compose:
- `MYSQL_ROOT_PASSWORD`
- `MYSQL_DATABASE`
- `MYSQL_USER`
- `MYSQL_PASSWORD`

### API (Spring Boot)
Docker Compose injects:
- `DB_URL=jdbc:mysql://mysql:3306/${MYSQL_DATABASE}?allowPublicKeyRetrieval=true&useSSL=false`
- `DB_USER=${MYSQL_USER}`
- `DB_PASSWORD=${MYSQL_PASSWORD}`
- `DDL_AUTO=update`

---

## API Documentation (Swagger / OpenAPI)

Swagger UI:
- http://localhost:8080/swagger-ui/index.html

OpenAPI JSON:
- http://localhost:8080/v3/api-docs

> Swagger endpoints are publicly accessible (no auth required).

---

## Security (Basic Auth + Roles)

This API uses **HTTP Basic Authentication**.

### Roles
- **ADMIN**
    - Full access for administrative actions.
    - Can manage: branches, products, categories.
- **STOCK_MANAGER**
    - Operational access focused on stock control.
    - Can manage: branch stock.
    - Can read: branches and products.
- **SELLER**
    - Sales operations.
    - Can manage: sales and sale state transitions.

### Access Matrix (from `SecurityConfig`)
| Resource | GET | POST/PUT/DELETE |
|---|---|---|
| `/api/branch/**` | ADMIN, STOCK_MANAGER | ADMIN |
| `/api/product/**` | ADMIN, STOCK_MANAGER | ADMIN |
| `/api/category/**` | ADMIN | ADMIN |
| `/api/branch-stock/**` | STOCK_MANAGER | STOCK_MANAGER |
| `/api/sale/**` | SELLER | SELLER |

### Demo users
These users are inserted by Flyway seed:

- `admin` (role: ADMIN)
- `stock_manager` (role: STOCK_MANAGER)
- `seller` (role: SELLER)
- Password: **2003**  
> Note: passwords are stored as **BCrypt** hashes in the seed.

---

## Postman

This repository includes a Postman collection and an environment to test the API quickly.

### Files
- Collection: `postman/Sales management api.postman_collection.json`
- Environment: `postman/sales management api.postman_environment.json`

### Import (Postman)
1. Open Postman
2. Click **Import**
3. Import the collection JSON file
4. Import the environment JSON file
5. Select the environment **sales management api** in the top-right dropdown

### Authentication
All secured endpoints use **HTTP Basic Auth**.
The collection is configured to read credentials from the environment variables above.

### Swagger (public)
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

---

## Flyway (migrations)

Migrations location:
- `src/main/resources/db/migration`

Naming convention:
- `V1__init.sql`
- `V2__seed_data.sql`

Flyway tracking table:
- `flyway_schema_history`

---
## Observability (Logging)

This project includes a minimal logging setup to make the API behavior easy to understand while testing.

- Key business events are logged (e.g. sale creation and state transitions, stock updates).
- Exceptions are handled centrally via `RestExceptionHandler` and returned as consistent JSON error responses.
- Sensitive data (credentials) is not logged.

---

## Roadmap / Improvements

- Tests (unit + integration)

---