package com.ps.exercise.eip.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

public class JSONToXMLRouteTest extends AbstractRouteTest {

	private String jsonPayload = "{\"firstName\" : \"john\", \"lastName\" : \"wick\", \"age\" : \"18\"}";
	
	private String xmlOutput = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n" + 
			"<customer><firstName>john</firstName><lastName>wick</lastName><age>18</age></customer>";
	
	@EndpointInject(uri = "{{eip.route.json-to-xml.start}}")
    protected ProducerTemplate producerTemplate;

	@EndpointInject(uri = "{{eip.route.json-to-xml.end}}")
    protected MockEndpoint mockEndpoint;
	
	@Test
	public void jsonShouldBeTransformedToXML() throws InterruptedException {
		mockEndpoint.expectedMessageCount(1);
		mockEndpoint.expectedBodiesReceived(xmlOutput);
		producerTemplate.sendBody("{{eip.route.json-to-xml.start}}", jsonPayload);
		mockEndpoint.assertIsSatisfied();
	}
}
