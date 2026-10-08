# NexoVentas API

API REST para la gestión comercial de PyMEs. Cubre productos, clientes, ventas e inventario, con autenticación JWT y documentación OpenAPI.

## Funcionalidades

- Catálogo de productos con SKU, precio, stock y stock mínimo.
- Clientes y registro de ventas con múltiples líneas.
- Descuento de stock atómico al confirmar una venta.
- Alertas de productos bajo mínimo.
- Endpoints protegidos mediante JWT.
- Documentación interactiva en Swagger UI.

## Tecnologías

Java 21, Spring Boot, Spring Security, Spring Data JPA, PostgreSQL, H2, JWT, OpenAPI y Docker.

## Ejecutar localmente

Requiere Java 21. El proyecto incluye Maven Wrapper, por lo que no necesitas instalar Maven globalmente.

```bash
.\mvnw.cmd spring-boot:run
```

La API quedará disponible en `http://localhost:8080` y Swagger en `http://localhost:8080/swagger-ui.html`.

Para levantarla con PostgreSQL:

```bash
docker compose up --build
```

## Publicar la API en Internet

El repositorio incluye `render.yaml`, que configura una API Docker y una base de datos PostgreSQL en Render. Tras subir este directorio a un repositorio de GitHub:

1. Inicia sesión en [Render](https://render.com) con GitHub.
2. Selecciona **New +** → **Blueprint** y elige tu repositorio.
3. Render detectará `render.yaml`; confirma la creación.
4. Al terminar, Render mostrará una URL pública similar a `https://nexoventas-api.onrender.com`.

La documentación quedará en `https://TU-URL/swagger-ui.html`. Render inyecta la URL de la base de datos y genera el secreto JWT, por lo que no debes subir contraseñas al repositorio.

## Autenticación de desarrollo

`POST /api/v1/auth/login`

```json
{ "username": "admin", "password": "admin123" }
```

Usa el `accessToken` devuelto como `Authorization: Bearer <token>`. Estas credenciales son exclusivamente de demostración: antes de desplegar, se deben reemplazar por usuarios almacenados en la base de datos y secretos configurados en variables de entorno.

## Endpoints principales

| Método | Ruta | Descripción |
| --- | --- | --- |
| POST | `/api/v1/auth/login` | Obtiene el JWT |
| GET / POST | `/api/v1/products` | Consulta o crea productos |
| GET | `/api/v1/products/low-stock` | Productos que requieren reposición |
| GET / POST | `/api/v1/customers` | Consulta o crea clientes |
| GET / POST | `/api/v1/sales` | Consulta o registra ventas |

### Ejemplo de venta

```json
{
  "customerId": "uuid-del-cliente",
  "items": [{ "productId": "uuid-del-producto", "quantity": 2 }]
}
```
