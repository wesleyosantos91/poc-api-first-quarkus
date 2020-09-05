# POC - API FIRST QUARKUS

## Prova de conceito - API FIRST

> API first significa que a Application Programming Interface  é uma estratégia na qual a primeira ordem dos negócios é desenvolver uma API que coloque os interesses do desenvolvedor de destino em primeiro lugar e depois construa o produto sobre ele (seja um site, aplicativo móvel ou software SaaS). Ao desenvolver APIs com os desenvolvedores em mente, você e seus desenvolvedores estão economizando muito trabalho enquanto estabelecem as bases para que outros possam desenvolver.

# Tecnologias
- Java 11
- quarkus 1.7.0.Final
    - quarkus-resteasy
    - quarkus-resteasy-json
    - quarkus-hibernate-orm
    - quarkus-hibernate-orm-panache
    - quarkus-flyway
    - quarkus-jdbc-mysql
    - quarkus-hibernate-validator
    - quarkus-rest-client
    - quarkus-smallrye-openapi
    - quarkus-junit5
    - rest-assured
    - testcontainers
    - approvaltests
 - Git
 - MYSQL

# Execução

A execução das aplicações são feitas através do de um comando Maven que envoca a inicialização do Quarkus.

- Scripts
    ### Executar docker-compose
    - 1° comando: ``` cd src/main/docker/``` 
    - 2° comando: ```docker-compose -f docker-compose.yml up``` 
    ### Executar a aplicação
    -  ```./mvnw clean quarkus:dev```
    ### Executar testes
    -  ```./mvnw clean verify sonar:sonar```
    
# Utilização

## Swagger
http://localhost:8080/swagger-ui/#/

## Sonar
http://localhost:9000/