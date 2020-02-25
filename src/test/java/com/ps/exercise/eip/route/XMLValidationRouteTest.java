package com.ps.exercise.eip.route;


import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;

public class XMLValidationRouteTest extends AbstractRouteTest {
	
	private String successPayload = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\r\n" + 
			"<customer><firstName>a</firstName><lastName>b</lastName><age>20</age></customer>";
	
	private String errorPayload = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\r\n" + 
			"<customer><firstName>a</firstName><lastName>b</lastName></customer>";

	@EndpointInject(uri = "{{eip.route.xml-validation.start}}")
    protected ProducerTemplate producerTemplate;

	@EndpointInject(uri = "{{eip.route.xml-validation.end}}")
    protected MockEndpoint mockEndpoint;
	
	@Before
	public void rest() {
		mockEndpoint.reset();
	}
	
	@Test
	public void xmlValidationShouldPass() throws InterruptedException {
		mockEndpoint.expectedMessageCount(1);
		producerTemplate.sendBody("{{eip.route.xml-validation.start}}", successPayload);
		mockEndpoint.assertIsSatisfied();
	}

	@Test
	public void xmlValidationShouldFail() throws InterruptedException {
		mockEndpoint.expectedMessageCount(0);
		producerTemplate.sendBody("{{eip.route.xml-validation.start}}", errorPayload);
		mockEndpoint.assertIsSatisfied();
	}
}
