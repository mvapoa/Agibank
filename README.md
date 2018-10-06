# Agibank

## Technologies Used
Bellow is the list of the used technologies for this project.
- Java 8: The micro services were created to run using Java 8.
- SpringBoot: Used to create the micro-services.
- Docker: Used to run applications in containers and ease their the deploy and scaling.
- Docker Compose: Used to build multiple docker applications using a single command.

## Project Modules
- agibank-importer 

## How to Test
In order to run the project you'll need to build the docker image. Inside folder you'll need to execute the following commands:
- **>mvn clean package** : to build
- **>docker build . -t agibank-importer:latest** : to build
- **>docker-compose up -d** : to deploy

=====================================================================
## Tecnologias Usadas
Abaixo está a lista das tecnologias usadas para este projeto.
- Java 8: Os micro-serviços foram criados para rodar usando o Java 8.
- SpringBoot: Usado para criar os micro-serviços.
- Docker: Usado para executar aplicativos em contêineres e facilitar a implantação e o dimensionamento.
- Docker Compose: Usado para criar vários aplicativos docker usando um único comando.

## Módulos de Projetos
- agibank-importer 

## Como Testar
Para executar o projeto, você precisará criar a imagem do docker. Dentro da pasta, você precisará executar os seguintes comandos:
- **>mvn clean package** : Construir
- **>docker build . -t agibank-importer:latest** : Construir
- **>docker-compose up -d** : Implantar
