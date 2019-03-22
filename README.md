# KLM Airlines Case study 
Contains spring boot and angular app

## Prerequisites.

**Clone `https://github.com/SanjeevKote/simple-travel-api-maven-mock.git` and run using `mvn spring-boot:run` 

## Compile and Run.
`mvn clean install` **It install npm and its dependencies via fronend-maven-plugin**
`mvn spring-boot:run` **Open Browser and Enter `http://localhost:7777` it will load Angular UI**

## For angular install npm and run using following commands.
`cd travelwithklm`
`npm install`
`npm start`

## Important Notes

- Make sure add bearer token in header before calling airports endpoints except matics.
- **Or Make sure you disable oAuth2 for seamless execution of end to end , Just edit configuration under `ResourceServerConfiguration.java` file in provided mock spring boot demo project using following way.**
```
http.anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll();
```

## Spring Boot Endpoints 

`http://localhost:7777/airports`    for fetching airport with param term , Make sure you add authorization bearer in header

`http://localhost:7777/fares/{origin}/{destination}`    for fetching fare using origin and destination

`http://localhost:7777/airports/{code}`    for fetching specific airport using code

###### Statatics Endoint
`http://localhost:7777/metrics/total/requests`

## Note for Token input box present under angularUI

 **Have provided token box for token in angular ui , make sure chnage it with letest one or leave it empty if oAuth is disabled.**



