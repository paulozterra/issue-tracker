# Issue Tracker

Proyecto de práctica de desarrollo asistido por IA. Ver `CLAUDE.md` para la metodología y el contexto completo del proyecto.

## Stack

- Backend: Java 21 + Spring Boot 4.1.1 (`backend/`)
- Frontend: React + Vite (`frontend/`)
- Base de datos: PostgreSQL

## Setup local

### 1. Base de datos

Con PostgreSQL corriendo localmente, creá la base y un usuario de aplicación dedicado (no uses el superusuario `postgres` para la app):

```sql
CREATE DATABASE issue_tracker_dev;
CREATE USER issue_tracker_app WITH PASSWORD 'elegí-una-contraseña';
GRANT ALL PRIVILEGES ON DATABASE issue_tracker_dev TO issue_tracker_app;
ALTER DATABASE issue_tracker_dev OWNER TO issue_tracker_app;
```

### 2. Backend

```bash
cd backend
cp application-local.properties.example application-local.properties
# editá application-local.properties con el usuario/contraseña que creaste arriba
./mvnw spring-boot:run
```

Verificá que levantó con `curl http://localhost:8080/health` → `{"status":"UP"}`.

### 3. Frontend

```bash
cd frontend
npm install
npm run dev
```

Por defecto corre en `http://localhost:5173`, ya habilitado en el CORS del backend.

## Notas

- `application-local.properties` nunca se versiona (contiene credenciales). Usá `application-local.properties.example` como plantilla.
- `spring.jpa.hibernate.ddl-auto=update` es temporal para esta etapa de prototipado sin datos reales. Cuando se cree la primera entidad de negocio, migrar a Flyway con migraciones versionadas en vez de dejar que Hibernate autogenere el esquema.
- `SecurityConfig` permite todas las requests (`permitAll`) porque todavía no existe modelo de usuarios/roles. Es provisorio: debe reemplazarse como parte de la tarea que implemente autenticación real.
