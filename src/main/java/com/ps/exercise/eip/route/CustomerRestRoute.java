package com.ps.exercise.eip.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Camel route for processing customer details
 *
 */
@Component
public class CustomerRestRoute extends RouteBuilder {

    private final String RESOURCE_PATH = "/customers";
    
    @Value("${eip.route.customer-rest.end}")
    private String routeEnd;

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .dataFormatProperty("prettyPrint", "true");

        rest()
        	.post(RESOURCE_PATH)
        		.description("Create a customer")
        			.route().to(routeEnd).endRest();
        
    }
}
