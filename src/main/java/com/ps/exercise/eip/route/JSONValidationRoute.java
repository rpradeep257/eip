package com.ps.exercise.eip.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Camel route validating incoming JSON with schema
 */

@Component
public class JSONValidationRoute extends RouteBuilder {

    @Value("${eip.route.json-validation.start}")
    private String routeStart;

    @Value("${eip.route.json-validation.end}")
    private String routeEnd;

    @Override
    public void configure() throws Exception {

        RouteDefinition routeDefinition = from(routeStart)
                .routeId(routeStart);

        routeDefinition.onException(JsonValidationException.class)
                .log(LoggingLevel.ERROR, "Invalid JSON payload ${body}")
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.BAD_REQUEST.value()))
                .setBody().constant("JSON validation failed")
                .end();

        routeDefinition
                .log(LoggingLevel.DEBUG, "Body : ${body}")
                .convertBodyTo(String.class)
                .to("json-validator:customer-schema.json")
                .log(LoggingLevel.INFO, "JSON validation successful")
                .to(routeEnd);

    }
}
