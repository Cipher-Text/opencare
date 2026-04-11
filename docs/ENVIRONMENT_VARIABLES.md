# Environment Variables

This document covers all environment variables for the OpenCare platform services.

**Required** = the service will fail to start without this value.
**Optional** = has a working default; override only when needed.
**Planned** = variable is documented for a feature not yet fully implemented.

---

## Backend (`open-care-backend`)

Create an `application-local.yml` or set these as system/Docker environment variables. Defaults shown are for local development.

### Server

| Variable | Default | Required | Description |
|---|---|---|---|
| `SERVER_PORT` | `6700` | Optional | HTTP port the backend listens on |

### Database (PostgreSQL)

| Variable | Default | Required | Description |
|---|---|---|---|
| `DB_HOSTNAME` | `localhost` | **Required** | PostgreSQL host |
| `DB_PORT` | `5432` | Optional | PostgreSQL port |
| `POSTGRES_USERNAME` | `postgres` | **Required** | Database username |
| `POSTGRES_PASSWORD` | `12345678` | **Required** | Database password — change in all non-local environments |

> Database name is `open_care`, schema is `opencare`.

### Authentication (Keycloak)

| Variable | Default | Required | Description |
|---|---|---|---|
| `KEYCLOAK_SERVER_URL` | `http://localhost:8080` | **Required** | Keycloak server base URL |
| `KEYCLOAK_REALM` | `opencare` | **Required** | Keycloak realm name |

> The backend validates JWTs issued by Keycloak. Ensure the realm exists and the client is configured before starting the backend.

### File Storage (MinIO)

| Variable | Default | Required | Description |
|---|---|---|---|
| `MINIO_ENDPOINT` | `http://127.0.0.1:9000` | **Required** | MinIO server URL |
| `MINIO_ACCESS_KEY` | `minioadmin` | **Required** | MinIO access key |
| `MINIO_SECRET_KEY` | `minioadmin123` | **Required** | MinIO secret key — change in all non-local environments |
| `MINIO_BUCKET_NAME` | `opencare` | Optional | Bucket name for file uploads |

### Search (Elasticsearch)

| Variable | Default | Required | Description |
|---|---|---|---|
| `ELASTICSEARCH_HOST` | `localhost` | **Required** | Elasticsearch host |
| `ELASTICSEARCH_PORT` | `9200` | Optional | Elasticsearch port |
| `SEARCH_HOSPITALS_ENABLED` | `true` | Optional | Enable hospital search indexing via Elasticsearch |
| `SEARCH_HOSPITALS_MAX_RESULTS` | `100` | Optional | Max results for hospital search |
| `SEARCH_DOCTORS_ENABLED` | `true` | Planned | Enable doctor search indexing — *doctor Elasticsearch index not yet implemented; falls back to PostgreSQL* |
| `SEARCH_DOCTORS_MAX_RESULTS` | `100` | Planned | Max results for doctor search |
| `SEARCH_INSTITUTIONS_ENABLED` | `true` | Planned | Enable institution search indexing — *not yet implemented* |
| `SEARCH_INSTITUTIONS_MAX_RESULTS` | `100` | Planned | Max results for institution search |
| `SEARCH_CACHE_ENABLED` | `true` | Optional | Enable search result caching |
| `SEARCH_CACHE_TTL` | `300` | Optional | Search cache TTL in seconds |
| `SEARCH_CACHE_MAX_SIZE` | `1000` | Optional | Max search cache entries |

### Rate Limiting

| Variable | Default | Required | Description |
|---|---|---|---|
| `RATE_LIMITING_ENABLED` | `true` | Optional | Enable API rate limiting on auth endpoints |
| `RATE_LIMITING_LIMIT` | `100` | Optional | Max requests per window |
| `RATE_LIMITING_REFRESH_PERIOD` | `60` | Optional | Rate limit window in seconds |

> Rate limiting currently applies to authentication endpoints only. Rate limiting for public directory endpoints (doctors, hospitals, blood donors) is a planned enhancement — see `CODE_REVIEW.md` item M8.

### Frontend Integration

| Variable | Default | Required | Description |
|---|---|---|---|
| `FRONTEND_REDIRECT_URI` | `http://localhost:5175/callback` | **Required** | OAuth2 redirect URI after login — must match the Keycloak client config |

### GitHub Integration

| Variable | Default | Required | Description |
|---|---|---|---|
| `GITHUB_TOKEN` | — | Optional | GitHub personal access token for contributor stats API. Service starts without it; GitHub endpoints return empty data. |

### Logging

| Variable | Default | Required | Description |
|---|---|---|---|
| `LOGGING_LEVEL_ROOT` | `error` | Optional | Root log level (`error`, `warn`, `info`, `debug`) |
| `LOGGING_LEVEL_SQL` | `debug` | Optional | SQL query log level |
| `LOGGING_LEVEL_WEB` | `debug` | Optional | Web request log level |

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

Create a `.env.local` file in the project root. Frontend runs on port **5175** by default.

| Variable | Default | Required | Description |
|---|---|---|---|
| `NEXT_PUBLIC_API_BASE_URL` | `https://api.opencarebd.com/api` | **Required** | Backend API base URL. Always set this in local development — see warning below. |
| `NEXT_PUBLIC_API_URL` | _(fallback for above)_ | Optional | Alternative env var name accepted as a fallback |

> **Warning:** If neither variable is set, the frontend silently calls `https://api.opencarebd.com/api` (production). A developer without `.env.local` will unknowingly read and mutate production data. Always create `.env.local` before starting the frontend locally. See `CODE_REVIEW.md` item 28.

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
