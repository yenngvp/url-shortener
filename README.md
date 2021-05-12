# URL Shortener Web Application
Create and manage short links for your website.

## Features
- Show a table of short urls  and actual urls mapping: http://localhost:8088/
- Generate a new short url from an url: http://localhost:8088/generate-link
- Revert and redirect to the actual url from a short url: http://localhost:8088/redirect
- Allow to custom short url's domain, for example: https://bit.ly, http://localhost:8088/settings

## Project architecture
There are two separate apps client and server:
- The client is a ReactJS app. Built with `create-react-app` and using `node-fetch` to make REST requests to the server.
- The server is a Spring Boot app. Implementing Spring MVC, Spring REST, Spring Data JPA. Building with maven and testing with Spring Boot Test and JUnit.
- Database: PostgreSQL 13 via JDBC driver

### Spring boot source code
For the spring boot source code, I did split the app into multiple packages and each of them handling different logic:
1. The `com.urlshortener.app.config` package contains `Constants`, `LoadDatabase` at the first server start up, and a `WebConfig` bean to allow CORS access via REST api.
2. The `com.urlshortener.app.model` package contains domain model entities and mapped with the PostgreSQL schema.
3. The `com.urlshortener.app.repository` package contains Spring Data JPA repositories.
4. The `com.urlshortener.app.service` package contains service classes to handle all business logic processing, for example: generate a short url from user input.
5. The `com.urlshortener.app.web.rest` package contains REST controller methods, exposing API endpoints.

### Exception Handling
Exceptions are caught and thrown in the services during handling client requests. 
If there is any exception raised, it will be handled by a custom handler `com.urlshortener.app.service.exception.RestResponseEntityExceptionHandler`,
which is essentially a using Spring's `@ControllerAdvice` annotation to return a `BAD_REQUEST` (200) code together with error message to the client.

### Database schema
The database schema is located at `src/main/resources/schema.sql`. It will be run the first time when the server boots up,
to create table schema for the app.

## How to test
Run test:

`./mvnw test`

Please note that: there are only three test cases for `LinkController` to demonstrate how to write integration tests with spring boot.

## How to run
The project is configured to build and deploy with `docker` and `docker-compose`. The project is built with single command and is a production ready deployment.
- Prerequisite: Install `docker` on your PC from https://docs.docker.com/get-docker/.
Normally, that the only need to get `docker` ready (with Docker Desktop) from the above link. It will get docker engine and docker CLI installed and we are good to go.
- Unzip the project zip file `url-shortener.zip` and `cd url-shortener`
- Run `./deploy.sh` script in the project root folder to get everything up and running.
- Wait for few seconds when deployment finished, access http://localhost:8088 to use the app.
- Done

### What does the script do?
The `deploy.sh` basically does two important things:
1. Package the Spring Boot app with: `./mvnw clean package`, and
2. Run `docker compose up` to build the client, server app, download `postgres` docker image, init the database,
and start all servers.

## Developer
Built from scratch by Yen Nguyen in May 2021