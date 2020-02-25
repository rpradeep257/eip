
## Build

`mvn clean install`

## Start

`mvn spring-boot:run`
 
## Docker build

`docker build -t camel:latest .`

## Docker run

`docker run -p8080:8080 camel:latest`

## Sample payload

###### Success

`curl -H "Content-Type: application/json" -X POST -d '{"firstName" : "john", "lastName" : "rambo", "age" : 20}' http://localhost:8080/services/rest/api/customers`

###### Failure

`curl -H "Content-Type: application/json" -X POST -d '{"firstName" : "john", "lastName" : "rambo"}' http://localhost:8080/services/rest/api/customers`
