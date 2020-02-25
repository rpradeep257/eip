package com.ps.exercise.eip.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ps.exercise.eip.entity.Customer;


/**
 * Camel route for processing customer details and passing to a service
 */

@Component
public class CustomerProcessingRoute extends RouteBuilder {

    @Value("${eip.route.customer-processing.start}")
    private String routeStart;

    @Value("${eip.route.customer-processing.end}")
    private String routeEnd;

    @Override
    public void configure() throws Exception {

        RouteDefinition routeDefinition = from(routeStart)
                .routeId(routeStart);

        routeDefinition.onException(Exception.class)
                .log(LoggingLevel.ERROR, "Customer processing failed ${body}")
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.BAD_REQUEST.value()))
                .setBody().constant("Customer processing failed")
                .end();

        routeDefinition.unmarshal().jacksonxml(com.ps.exercise.eip.generated.Customer.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        com.ps.exercise.eip.generated.Customer customerObject = (com.ps.exercise.eip.generated.Customer) exchange.getIn().getBody();
                        Customer newCustomer = new Customer();
                        newCustomer.setFirstName(customerObject.getFirstName());
                        newCustomer.setLastName(customerObject.getLastName());
                        exchange.getIn().setBody(newCustomer);
                    }
                })
                .to("bean:customerService?method=create")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
                .setBody().constant("Customer created");
    }
}
