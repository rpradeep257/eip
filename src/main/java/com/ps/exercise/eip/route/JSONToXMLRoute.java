package com.ps.exercise.eip.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.processor.validation.SchemaValidationException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


/**
 * Route for transforming incoming JSON to XML
 */

@Component
public class JSONToXMLRoute extends RouteBuilder {

	@Value("${eip.route.json-to-xml.start}")
    private String routeStart;
	
	@Value("${eip.route.json-to-xml.end}")
	private String routeEnd;

    @Override
    public void configure() throws Exception {
    	RouteDefinition routeDefinition = from(routeStart)
                .routeId(routeStart);
    	
    	
        routeDefinition.onException(SchemaValidationException.class)
                    .log(LoggingLevel.ERROR, "Invalid payload ${body}")
                    .handled(true)
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.BAD_REQUEST.value()))
                    .setBody().constant("Transformation to XML failed")
                    .end();
                    
        routeDefinition.process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        JSONObject jsonObject = new JSONObject(exchange.getIn().getBody().toString());
                        String tranformedXML = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n" + XML.toString(jsonObject, "customer");
                        exchange.getIn().setBody(tranformedXML);
                    }
                })
                .to(routeEnd)
                .end();
    }
}
