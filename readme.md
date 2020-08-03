Projeto criado em Springboot com:

* JPA
* Mysql 
* RestFul
* Swagger
* Junit 

Foi criado perfis de inicialização para springboot (dev, test, hom);

Habilitado o Swagger em perfil dev para testes manuais e documentação;

Efetuado teste de integração com Junit, utilizando o banco de dados, configurado para uma schema de test;

Foi deixado habilitado em dev o Hibernate dll para criar e atualizar as tabelas;

Nos perfis de (hom, test) foi desabilitado funcionalidade de criar e atualizar as tabelas, para fazer isso através de liquibase.
 
#Passos para rodar o projeto


## Passo 1 - Clone projeto do GitHub


## Passo 2 - Banco de dados Mysql, utilizado versão 5.7
Recomendado uso de docker, documentação [link to MYSQL!](https://hub.docker.com/_/mysql)

execute os seguintes comandos.

docker pull mysql:5.7

docker run -d --name mysql57 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql:5.7


Crie as seguintes schemas:
* db_prova_dev  
* db_prova_test
* db_prova_hom


## Passo 3 - Maven run e build

### Inicializa com swagger, banco de dados criado através das entities [link Swagger!](http://localhost:8080/swagger-ui.html) :

mvn spring-boot:run -Dspring-boot.run.profiles=dev

### Incializa liquibase cria tabela e executa scripts de insert. Executa os testes na porta 8088:

mvn spring-boot:run -Dspring-boot.run.profiles=test

### Incializa liquibase cria tabela. Serviços rodando na porta 8080:

mvn spring-boot:run -Dspring-boot.run.profiles=hom

### Empacotando  (opcional retirar os testes  "-DskipTests")

mvn package spring-boot:repackage -Dspring-boot.run.profiles=dev -DskipTests


## Passo 4 - Criar uma imagem docker do projeto

Entre na pasta do projeto, nela contém um arquivo dockerfile, para criar imagem siga os comandos abaixo:

docker build -t springboot/demo .

docker create --name demo -u root -e TZ=America/Sao_Paulo -p 8080:8080 -it springboot/demo

docker start demo


