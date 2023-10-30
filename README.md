# Springboot-ms-example

This repository contains 2 microservices, an Eureka server and an api gateway. Microservices perfom a simplified "simulation" of the management process 
of Clients, Accounts and accounts' movements in a Banking entity.

## Execution
1. Clone the repo.
2. Create a MySql docker container, and expose the DB container port in 33060 host port. If you want to use a different port, specify it in the application.properties files
   both microservices
4. Create the database with the SQL script located in this repo.
5. Open each Spring project with favorite IDE.
6. Start the Eureka Server.
7. Start all microservices.
8. Start Api gateway.
9. Import Postman collection located in this repo and test.


