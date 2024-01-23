# Scheduling with Spring Boot and Quartz
This project contains code samples for an article on task scheduling using Spring Boot and Quartz.
Project consists of 3 subprojects:
- `spring-boot-scheduler` - for scheduling with Spring Boot only.
- `quartz-single-node-scheduler` - for scheduling with Spring Boot and Quartz for single node applications without a job persistence.
- `quartz-cluster-scheduler` - for scheduling with Spring Boot and Quartz for applications in cluster mode with job persistence.

# How to run application
```bash
./gradlew :spring-boot-scheduler:bootRun
./gradlew :quartz-single-node-scheduler:bootRun
./gradlew :quartz-cluster-scheduler:bootRun
```

✨The `quartz-cluster-scheduler` application requires a connection to PostgreSQL, so either configure the paths
to your own base in the `application.yml`, or use the docker compose file to start postgres with the necessary settings in docker.
```bash
docker-compose -f quartz-cluster-scheduler/docker-compose.yml up -d
```
