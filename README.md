# Agibank

## Technologies Used
Bellow is the list of the used technologies for this project.
- Java 8: The micro services were created to run using java8
- SpringBoot: Used to create the micro-services
- Docker: Used to run applications in containers and ease their the deploy and scaling.
- Docker Compose: Used to build multiple docker applications using a single command.

## Project modules
- agibank-importer 

## How to test
In order to run the project you'll need to build the docker images. Inside each folder you'll need to execute the following commands:
agibank-importer
- >mvn clean package : to build
- >docker build . -t agibank-importer:latest : to build
- >docker-compose up -d : to deploy
