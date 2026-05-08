# user_service

A production-ready event-driven microservice built with **Spring Boot**, **Apache Kafka**, and **Apache Avro** with Schema Registry. Designed as a foundational template for scalable, schema-enforced, event-sourced architectures in a microservice ecosystem.

---

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Roadmap](#roadmap)

---

## Overview

This service handles domain lifecycle events through a clean, layered architecture. It consumes events from Kafka, processes them through well-defined use cases, and emits downstream events for other services to react to.

Key design goals:
- **Schema-first**: All Kafka messages are validated against Avro schemas registered in Confluent Schema Registry
- **Clean Architecture**: Domain logic is fully decoupled from infrastructure concerns
- **Extensible**: Built to scale into a full microservice ecosystem with multiple bounded contexts, persistence layers, and inter-service communication

---

## Architecture

The service follows **Hexagonal Architecture (Ports & Adapters)**:

- **Domain layer** — business entities, domain events, and port interfaces
- **Application layer** — use-case command handlers orchestrated by a `Mediator`
- **Infrastructure layer** — concrete adapters for Kafka, HTTP, and the database

Events are consumed from Kafka, routed by Avro schema to the appropriate handler, and new domain events are published back to Kafka for downstream services.

---

## Tech Stack

| Component | Technology |
|---|---|
| Framework | Spring Boot |
| Language | Java 21 |
| Messaging | Apache Kafka (Spring Kafka) |
| Serialization | Apache Avro + Confluent Schema Registry |
| Mapping | MapStruct + Lombok |
| Build tool | Maven |
| Local infrastructure | Docker Compose |

---

## Getting Started

### Prerequisites

- Java 21+
- Docker & Docker Compose
- Maven (or use `./mvnw`)

### 1. Start local infrastructure

```bash
docker compose up -d
```

Starts Zookeeper, Kafka Broker, Schema Registry, and Confluent Control Center (UI available at http://localhost:9021).

### 2. Build the project

```bash
./mvnw clean install
```

> Avro Java classes are auto-generated from `.avsc` schemas during the build.

### 3. Run the application

```bash
./mvnw spring-boot:run
```

---

## Configuration

All configuration lives in `src/main/resources/application.yaml`. The main properties to adjust for a new environment are the Kafka broker address, consumer group ID, topic names, and Schema Registry URL.

---

## Roadmap

- [ ] Persistent database (PostgreSQL + JPA + migrations)
- [ ] Transactional Outbox pattern for reliable event publishing
- [ ] Dead Letter Queue (DLQ) handling for failed events
- [ ] Inter-service REST communication
- [ ] Distributed tracing and structured logging
- [ ] Prometheus metrics + Grafana dashboards
- [ ] Resilience patterns (retries, circuit breakers, idempotency)
- [ ] Integration tests with Testcontainers
- [ ] Kubernetes manifests and Helm chart
- [ ] CI/CD pipeline
