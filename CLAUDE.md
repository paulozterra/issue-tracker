\# Issue Tracker — proyecto de práctica de desarrollo asistido por IA



\## Objetivo real

Esto NO es solo "construir un Issue Tracker". El objetivo de Paulo es aprender a dirigir y

supervisar un agente de IA en un flujo de desarrollo real — delegar implementación sin

delegar criterio técnico. La app es el vehículo, no el fin. No hay apuro por terminarla rápido.



\## Cómo trabajar en este proyecto (metodología acordada)

Para cualquier cambio significativo, seguir este flujo — NO implementar directo sin pasar por esto:

1\. Analizar el estado actual del proyecto.

2\. Explicar cómo se entiende el requisito.

3\. Proponer un plan.

4\. Identificar problemas, casos borde y riesgos.

5\. Esperar aprobación de Paulo antes de implementar (si el cambio es significativo).

6\. Implementar únicamente lo necesario — nada de refactors o cambios fuera de alcance sin consultar.

7\. Agregar las pruebas correspondientes.

8\. Explicar qué se cambió y qué debería validar manualmente.



Si Paulo reporta un bug/error generado por el agente: NO corregirlo de una. Primero analizar

causa raíz, proponer solución, implementarla, y agregar un test de regresión que detecte ese

error a futuro.



Reglas generales:

\- Commits pequeños y fáciles de revisar.

\- No tocar código fuera del alcance de la tarea actual sin consultar primero.

\- Cuando uses un concepto que probablemente esté fuera del nivel actual de Paulo, explica

&#x20; brevemente el razonamiento antes de tirar código. No hace falta explicar fundamentos que

&#x20; ya domina (SQL, bases de datos, APIs REST, programación básica — ver perfil abajo).



\## Perfil técnico de Paulo (para calibrar explicaciones)

\- Fuerte: SQL / PostgreSQL / bases de datos (joins, CTEs, window functions, conciliaciones,

&#x20; calidad de datos, migraciones).

\- Intermedio: Python, Java, Spring Boot (APIs, controllers/services/repos/entities), APIs

&#x20; REST, Postman, QA (Selenium, JMeter).

\- Básico/Intermedio: FastAPI, JavaScript, React, Git/GitHub, Angular (básico).

\- Básico: Docker, AWS, JMeter.



\## Stack definido

\- Backend: Java + Spring Boot (elegido porque Paulo tiene experiencia real de producción

&#x20; ahí, lo que le da criterio propio para revisar el código que genere el agente).

\- Frontend: React.

\- Base de datos: PostgreSQL (evaluando migrar a Supabase más adelante — no decidido aún).

\- Arquitectura: monolito en capas (controller/service/repository) para empezar. Nada de

&#x20; microservicios desde el día uno — eso queda como posible ejercicio deliberado más adelante.



\## Principio de arquitectura a tener presente

(Recomendación que Paulo recibió de un colega para el proyecto de su próximo trabajo, aplicable aquí también si el Issue Tracker llega a incorporar algo de IA):

Todo lo que se pueda calcular, derivar o validar con reglas fijas (IDs, hashes, routing,

validaciones, límites, formateo de estructuras conocidas, guards, validación final) debe

resolverse con código determinístico normal — testeable, auditable, barato y rápido. Un

modelo de IA se reserva solo para lo que realmente requiere interpretación, selección o

razonamiento sobre contenido no derivable. Por ahora el Issue Tracker no tiene ningún

componente de IA integrado — este principio aplica el día que se agregue uno (ej. sugerencia

automática de prioridad/etiquetas a partir de la descripción de una incidencia).



\## Funcionalidades previstas (crecimiento incremental, no todo de una vez)

Usuarios y autenticación, proyectos, miembros asociados a proyectos, roles y permisos,

creación/gestión de incidencias, estados (OPEN, IN\_PROGRESS, TESTING, CLOSED), prioridades,

asignación de responsables, comentarios, historial de cambios, adjuntos/evidencias,

dashboard básico, validaciones de autorización y seguridad, pruebas automatizadas.



\## Estado actual

\- Stack, metodología y principio de arquitectura acordados.

\- Repositorio GitHub creado y público: `paulozterra/issue-tracker`, conectado como `origin`.

\- Subagentes configurados en `.claude/agents/`: code-reviewer, architecture-reviewer,
  security-reviewer, test-writer, git-commit-helper.

\- Scaffolding inicial hecho: backend (Java 21 + Spring Boot 4.1.1, capas
  controller/service/repository/model/dto/config, Spring Security con CORS habilitado para
  `localhost:5173` y una `SecurityFilterChain` provisoria en `permitAll` documentada como
  temporal), frontend (React + Vite), base `issue_tracker_dev` en PostgreSQL local con un
  usuario de aplicación dedicado (`issue_tracker_app`, no el superusuario `postgres`). Ver
  `README.md` para los pasos de setup.

\- Decisión pendiente de ejecutar (no implementada todavía): migrar de
  `spring.jpa.hibernate.ddl-auto=update` a Flyway con migraciones versionadas en el momento
  en que se cree la primera entidad de negocio (Issue, User, etc.) — no dejarlo para después.

\- `SecurityConfig` en `permitAll` es provisorio: su reemplazo debe ser parte explícita de
  la tarea que implemente el modelo de usuarios/roles y autenticación real, no un paso
  aparte que se pueda posponer indefinidamente.

\- Todavía no hay entidades de negocio ni funcionalidad real implementada.



\## Próximo paso inmediato

Definir y proponer el plan para la primera entidad de negocio (probablemente `Issue` o

`User`, a confirmar con Paulo), incluyendo el modelo de datos, la migración Flyway

correspondiente y el CRUD básico en capas — siguiendo la metodología de arriba, sin

implementar nada sin aprobación de Paulo.

