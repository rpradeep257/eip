package com.ps.exercise.eip.route;


import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;


public class JSONValidationRouteTest extends AbstractRouteTest {

    private String successPayload = "{\"firstName\" : \"john\", \"lastName\" : \"wick\", \"age\" : \"18\"}";
    private String errorPayload = "{\"firstName\" : \"john\", \"lastName\" : \"wick\"}";

    @EndpointInject(uri = "{{eip.route.json-validation.start}}")
    protected ProducerTemplate producerTemplate;

    @EndpointInject(uri = "{{eip.route.json-validation.end}}")
    protected MockEndpoint mockEndpoint;

    @Before
    public void rest() {
        mockEndpoint.reset();
    }

    @Test
    public void jsonValidationShouldPass() throws InterruptedException {
        mockEndpoint.expectedMessageCount(1);
        producerTemplate.sendBody("{{eip.route.json-validation.start}}", successPayload);
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void jsonValidationShouldFail() throws InterruptedException {
        mockEndpoint.expectedMessageCount(0);
        producerTemplate.sendBody("{{eip.route.json-validation.start}}", errorPayload);
        mockEndpoint.assertIsSatisfied();

    }

}
