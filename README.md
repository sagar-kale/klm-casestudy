# KLM Airlines Case study 
Contains spring boot and angular app

## Important Notes.

**oAuth2 is handled on application level i.e spring boot side using OAuthRestTemplate and Spring-Security_oAuth2**

## Prerequisites.

**Clone `https://github.com/SanjeevKote/simple-travel-api-maven-mock.git` and run using `mvn spring-boot:run`.**

## Compile and Run.

`mvn clean install` **It will install npm and its dependencies via frontend-maven-plugin**

**Then run application using `mvn spring-boot:run` command , 

Open browser type url ->  `http://localhost:7777` It will load Angular UI**


## Spring Boot Endpoints 

`http://localhost:7777/airports`    for fetching airport with param term .

`http://localhost:7777/fares/{origin}/{destination}`    for fetching fare using origin and destination

`http://localhost:7777/airports/{code}`    for fetching specific airport using code

###### Statistics Endpoint

`http://localhost:7777/metrics/total/requests`

## UI

**Provided origin and destination inbuilt search at `search flight` Menu**

**Backend Statistics displayed at `Statistics` Menu**



