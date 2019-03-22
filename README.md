# KLM Airlines Case study 
Contains spring boot and angular app

## Prerequisites.

**Clone `https://github.com/SanjeevKote/simple-travel-api-maven-mock.git` and run using `mvn spring-boot:run` 

## Compile and Run.
`mvn clean install` **It install npm and its dependencies via fronend-maven-plugin**
`mvn spring-boot:run` **Open Browser and Enter `http://localhost:7777` it will load Angular UI**


## Spring Boot Endpoints 

`http://localhost:7777/airports`    for fetching airport with param term , Make sure you add authorization bearer in header

`http://localhost:7777/fares/{origin}/{destination}`    for fetching fare using origin and destination

`http://localhost:7777/airports/{code}`    for fetching specific airport using code

###### Statatics Endoint
`http://localhost:7777/metrics/total/requests`

## UI
**Provided origin and destnation inbuild search**



