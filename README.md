# Sales Management API

REST API para gestión de sucursales, productos y ventas.

Incluye:
- Autenticación con **Basic Auth**
- Autorización por **roles (3 roles)**
- Persistencia en **MySQL**
- Contenedorización con **Docker + Docker Compose**

---

## Stack
- Java 17
- Spring Boot (Web, Validation, Data JPA)
- Spring Security (Basic Auth + roles)
- MySQL 8
- Flyway
- Docker / Docker Compose

---

## Ejecutar con Docker (recomendado)

### 1) Clonar el repo
```bash
git clone https://github.com/Vickyrai01/sales-management-api.git
cd sales-management-api
```

### 2) Crear `.env`
Creá un `.env` en la raíz (podés copiar `.env.example`):

```bash
cp .env.example .env
```

### 3) Levantar API + MySQL
```bash
docker compose up --build
```

- API: http://localhost:8080
- MySQL (host local): `localhost:3307`

### Detener / borrar
- Detener (conserva datos):
```bash
docker compose down
```

- Borrar todo (incluye datos):
```bash
docker compose down -v
```

---

## Variables de entorno

### MySQL (contenedor)
Configuradas por Docker Compose:
- `MYSQL_ROOT_PASSWORD`
- `MYSQL_DATABASE`
- `MYSQL_USER`
- `MYSQL_PASSWORD`

### API (Spring Boot)
Docker Compose inyecta:
- `DB_URL=jdbc:mysql://mysql:3306/${MYSQL_DATABASE}?allowPublicKeyRetrieval=true&useSSL=false`
- `DB_USER=${MYSQL_USER}`
- `DB_PASSWORD=${MYSQL_PASSWORD}`
- `DDL_AUTO=update`

---

## Seguridad (Basic Auth + Roles)

Esta API usa **HTTP Basic Authentication**.

### Roles
- `ADMIN`: acceso a la creacion de productos, sucursales y categorias.
- `SELLER`: Encargado de operaciones sobre las ventas, crearlas, eliminarlas, modificarlas, aceptarlas, etc.
- `STOCK_MANAGER`: Acceso mas limitado, puede crear el stock de un producto en una sucursal

### Probar con curl (ejemplos)



### Respuestas esperadas
- `200 OK` si el usuario tiene permisos.
- `401 Unauthorized` si no mandás credenciales o son incorrectas.
- `403 Forbidden` si autenticás pero el rol no tiene acceso.

---

## Flyway (migraciones)
Flyway ejecuta migraciones en:

`src/main/resources/db/migration`

Convención:
- `V1__init.sql`
- `V2__...sql`

Flyway registra el estado en:
- `flyway_schema_history`

---
## Documentacion de la api

Corriendo el proyecto entrando al link:
- http://localhost:8080/swagger-ui/index.html

---

## Roadmap / mejoras
- Logs (logback + request logging)
- Tests (unit + integration)
