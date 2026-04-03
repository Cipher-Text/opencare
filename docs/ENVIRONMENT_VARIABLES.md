# Environment Variables

This document covers all environment variables for the OpenCare platform services.

---

## Backend (`open-care-backend`)

Create an `application-local.yml` or set these as system/Docker environment variables. Defaults shown are for local development.

### Server

| Variable | Default | Description |
|---|---|---|
| `SERVER_PORT` | `6700` | HTTP port the backend listens on |

### Database (PostgreSQL)

| Variable | Default | Description |
|---|---|---|
| `DB_HOSTNAME` | `localhost` | PostgreSQL host |
| `DB_PORT` | `5432` | PostgreSQL port |
| `POSTGRES_USERNAME` | `postgres` | Database username |
| `POSTGRES_PASSWORD` | `12345678` | Database password |

> Database name is `open_care`, schema is `opencare`.

### Authentication (Keycloak)

| Variable | Default | Description |
|---|---|---|
| `KEYCLOAK_SERVER_URL` | `http://localhost:8080` | Keycloak server base URL |
| `KEYCLOAK_REALM` | `opencare` | Keycloak realm name |

> The backend validates JWTs issued by Keycloak. Ensure the realm exists and the client is configured before starting the backend.

### File Storage (MinIO)

| Variable | Default | Description |
|---|---|---|
| `MINIO_ENDPOINT` | `http://127.0.0.1:9000` | MinIO server URL |
| `MINIO_ACCESS_KEY` | `minioadmin` | MinIO access key |
| `MINIO_SECRET_KEY` | `minioadmin123` | MinIO secret key |
| `MINIO_BUCKET_NAME` | `opencare` | Bucket name for file uploads |

### Search (Elasticsearch)

| Variable | Default | Description |
|---|---|---|
| `ELASTICSEARCH_HOST` | `localhost` | Elasticsearch host |
| `ELASTICSEARCH_PORT` | `9200` | Elasticsearch port |
| `SEARCH_DOCTORS_ENABLED` | `true` | Enable doctor search indexing |
| `SEARCH_DOCTORS_MAX_RESULTS` | `100` | Max results for doctor search |
| `SEARCH_HOSPITALS_ENABLED` | `true` | Enable hospital search indexing |
| `SEARCH_HOSPITALS_MAX_RESULTS` | `100` | Max results for hospital search |
| `SEARCH_INSTITUTIONS_ENABLED` | `true` | Enable institution search indexing |
| `SEARCH_INSTITUTIONS_MAX_RESULTS` | `100` | Max results for institution search |
| `SEARCH_CACHE_ENABLED` | `true` | Enable search result caching |
| `SEARCH_CACHE_TTL` | `300` | Search cache TTL in seconds |
| `SEARCH_CACHE_MAX_SIZE` | `1000` | Max search cache entries |

### Rate Limiting

| Variable | Default | Description |
|---|---|---|
| `RATE_LIMITING_ENABLED` | `true` | Enable API rate limiting |
| `RATE_LIMITING_LIMIT` | `100` | Max requests per window |
| `RATE_LIMITING_REFRESH_PERIOD` | `60` | Rate limit window in seconds |

### Frontend Integration

| Variable | Default | Description |
|---|---|---|
| `FRONTEND_REDIRECT_URI` | `http://localhost:5173/callback` | OAuth2 redirect URI after login |

### GitHub Integration

| Variable | Default | Description |
|---|---|---|
| `GITHUB_TOKEN` | _(required)_ | GitHub personal access token for contributor stats API |

### Logging

| Variable | Default | Description |
|---|---|---|
| `LOGGING_LEVEL_ROOT` | `error` | Root log level (`error`, `warn`, `info`, `debug`) |
| `LOGGING_LEVEL_SQL` | `debug` | SQL query log level |
| `LOGGING_LEVEL_WEB` | `debug` | Web request log level |

### Example `.env` for Docker Compose

```env
SERVER_PORT=6700

DB_HOSTNAME=postgres
DB_PORT=5432
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=your_secure_password

KEYCLOAK_SERVER_URL=http://keycloak:8080
KEYCLOAK_REALM=opencare

MINIO_ENDPOINT=http://minio:9000
MINIO_ACCESS_KEY=minioadmin
MINIO_SECRET_KEY=your_minio_secret
MINIO_BUCKET_NAME=opencare

ELASTICSEARCH_HOST=elasticsearch
ELASTICSEARCH_PORT=9200

GITHUB_TOKEN=ghp_your_token_here

FRONTEND_REDIRECT_URI=http://localhost:5175/callback

LOGGING_LEVEL_ROOT=error
```

---

## Frontend (`open-care-frontend`)

Create a `.env.local` file in the project root.

| Variable | Default | Description |
|---|---|---|
| `NEXT_PUBLIC_API_BASE_URL` | `https://api.opencarebd.com/api` | Backend API base URL |
| `NEXT_PUBLIC_API_URL` | _(fallback for above)_ | Alternative env var name for API base URL |

> If neither variable is set, the frontend falls back to `https://api.opencarebd.com/api`.

### Example `.env.local`

```env
# Point to local backend during development
NEXT_PUBLIC_API_BASE_URL=http://localhost:6700/api
```

---

## Supporting Services (Docker Compose)

Refer to the docker-compose files in `open-care-backend/platform/` for service-specific configuration:

| File | Service | Default Ports |
|---|---|---|
| `keycloak-docker-compose.yml` | Keycloak | `8080` |
| `minio-docker-compose.yml` | MinIO | `9000` (API), `9001` (Console) |
| `redis-docker-compose.yml` | Redis | `6379` |
| `elasticsearch-docker-compose.yml` | Elasticsearch | `9200` |
