<<<<<<< HEAD
# DC-PROJECT
=======
# Smart Railway Distributed System

This repository contains a minimal end-to-end demonstration of distributed systems concepts through a railway booking system.

Folders
- `backend` - multi-module Spring Boot project (gateway-service, booking-service, analytics-service, node-manager, clock-service, common-lib)
- `frontend` - React + Vite + Tailwind frontend

Prerequisites
- Git
- Java 20
- Maven
- Node.js & npm
- PostgreSQL 18 (if running without Docker)

Run locally (without Docker)

1. Ensure you have Java 20, Maven, Node.js/npm installed.

2. Start a PostgreSQL instance (or rely on the H2 in-memory fallback used by default). If using Postgres, create a database named `smartrail` and set env vars:

   - `SPRING_DATASOURCE_URL` (e.g. jdbc:postgresql://localhost:5432/smartrail)
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`

3. Build and run backend services. You can run using the Spring Boot plugin during development or run the packaged jars.

   - Run with Maven (dev):

     mvn -pl backend/booking-service spring-boot:run

   - Or package then run the jars (recommended to produce consistent executable jars):

     mvn -pl backend -am package -DskipTests
     java -jar backend/booking-service/target/booking-service-0.0.1-SNAPSHOT.jar
     java -jar backend/gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar
```markdown
# Smart Railway Distributed System

This repository contains a minimal end-to-end demonstration of distributed systems concepts through a railway booking system.

Folders
- `backend` - multi-module Spring Boot project (gateway-service, booking-service, analytics-service, node-manager, clock-service, common-lib)
- `frontend` - React + Vite + Tailwind frontend

Prerequisites
- Git
- Java 20
- Maven
- Node.js & npm
- PostgreSQL 18 (if running without Docker)

Run locally (without Docker)

1. Ensure you have Java 20, Maven, Node.js/npm installed.

2. Start a PostgreSQL instance (or rely on the H2 in-memory fallback used by default). If using Postgres, create a database named `smartrail` and set env vars:

   - `SPRING_DATASOURCE_URL` (e.g. jdbc:postgresql://localhost:5432/smartrail)
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`

3. Build and run backend services. You can run using the Spring Boot plugin during development or run the packaged jars.

   - Run with Maven (dev):

     mvn -pl backend/booking-service spring-boot:run

   - Or package then run the jars (recommended to produce consistent executable jars):

     mvn -pl backend -am package -DskipTests
     java -jar backend/booking-service/target/booking-service-0.0.1-SNAPSHOT.jar
     java -jar backend/gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar

4. Start frontend:

   cd frontend
   npm install
   npm run dev

Run with docker-compose
1. Copy `.env.sample` to `.env` and edit values if needed.
2. From repo root:

   docker-compose up --build

This will start PostgreSQL, Kafka (optional), booking-service, analytics-service, gateway, Prometheus, Grafana.

APIs (via gateway)
- GET /api/trains
- POST /api/book  { userId, trainId, seats }
- GET /api/bookings/{userId}
- GET /api/analytics/summary

Distributed features (automatic, backend-only)
- Node manager (leader election): `backend/node-manager` runs scheduled background tasks that simulate Bully and Ring elections. Logs are written to the service logs. The implementation is in `ElectionService`.
- Clock synchronization: `backend/clock-service` runs background tasks that simulate Cristian, Berkeley, Lamport, and Vector clock syncs. Check `ClockSyncService` for implementation and logging.
- Load balancing: Gateway routes requests to downstream services; you can run multiple instances and expand the gateway to use an actual load balancer.
- Distributed logging: All services log events (elections, clock syncs, bookings). Logs are not exposed to the UI.

Testing
- Unit and integration tests use JUnit and Testcontainers. Example:

  mvn -pl backend/booking-service test

Troubleshooting
- If Flyway migrations don't run: ensure `SPRING_DATASOURCE_URL` is correct and DB is reachable.
- If frontend cannot reach APIs: ensure `gateway-service` running on port 8080 and `vite` dev server is configured to proxy (or use relative URLs as the app does).

Notes
- This repo is intentionally a focused demonstration. The node-manager and clock-service implementations are simulation-style: they run automatically as background scheduled tasks and log events; they do not require UI interaction.

Publishing to GitHub
---------------------

To publish this local repository to GitHub and enable VS Code to show remote sync:

1. Create an empty repository on GitHub (use the web UI) and copy the HTTPS remote URL (e.g. https://github.com/<your-user>/smartrail.git).

2. From the repository root (this project), add the remote and push the current main branch:

   git remote add origin <REMOTE_URL>
   git branch -M main
   git push -u origin main

Or run the helper script included at `scripts/push-to-github.ps1` which will prompt for the remote URL and push for you.

If you prefer using the GitHub CLI (`gh`) you can instead run:

   gh repo create <your-user>/smartrail --public --source=. --remote=origin --push

VS Code integration
--------------------

- Open the project folder in VS Code (File → Open Folder → select this repo). The built-in Git pane will show local changes and branches.
- Sign in to GitHub in VS Code (click the Accounts icon in the lower left or use the Command Palette: 'GitHub: Sign in'). After signing in, the Source Control view will show remote features and you can push/pull from the UI.
- After the remote is added and you push, VS Code will show the remote branch and allow you to sync changes using the Source Control toolbar.

Security note: avoid committing secrets (passwords, tokens). Use `.env` or other secrets mechanisms and add them to `.gitignore`.

CI / GitHub Actions
-------------------

A basic CI workflow was added at `.github/workflows/ci.yml`. It will run on push and pull requests to `main` and will:

- Start a PostgreSQL service for tests.
- Build the backend modules with Maven (packaging the JARs).
- Install and build the frontend (Vite production build).

After you push changes to `main`, check the Actions tab on GitHub to see workflow runs and logs.

```
--------------------
