# Pocket Guide

## Objetivo
    O projeto tem o objetivo de gerenciar e persistir os gastos enviados. 

## Requisitos
    Java 11
    Docker Compose
    Plugin Lombok
    
## Configuração e Execução do projeto

### Compilar o projeto
    ./gradlew build
    
### Executar os testes
    ./gradlew test

### Executar o projeto
    
    Antes de iniciar o projeto é necessário estar com a base de dados configurada e 
    executando.
    
    Para isso, o projeto já conta com uma imagem docker, basta rodar o comando:
    
    docker-compose up
    
    Feito isso podemos executar o comando:
    
    ./gradlew bootRun
