package com.ps.exercise.eip.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.processor.validation.SchemaValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Camel route to validate XML against a schema
 */

@Component
public class XMLValidationRoute extends RouteBuilder {

    @Value("${eip.route.xml-validation.start}")
    private String routeStart;

    @Value("${eip.route.xml-validation.end}")
    private String routeEnd;

    @Override
    public void configure() throws Exception {


        RouteDefinition routeDefinition = from(routeStart)
                .routeId(routeStart);

        routeDefinition.onException(SchemaValidationException.class)
                .log(LoggingLevel.ERROR, "Invalid XML payload ${body}")
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.BAD_REQUEST.value()))
                .setBody().constant("XML validation failed")
                .end();

        routeDefinition.log(LoggingLevel.DEBUG, "Body : ${body}")
                .to("validator:customer.xsd")
                .log(LoggingLevel.INFO, "XML validation sucessful")
                .to(routeEnd)
                .end();
    }
}
