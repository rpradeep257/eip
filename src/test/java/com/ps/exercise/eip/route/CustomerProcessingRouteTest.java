package com.ps.exercise.eip.route;

import com.ps.exercise.eip.entity.Customer;
import com.ps.exercise.eip.service.CustomerService;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class CustomerProcessingRouteTest extends AbstractRouteTest {

    private String payload = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\r\n" +
            "<customer><firstName>a</firstName><lastName>b</lastName><age>20</age></customer>";

    @EndpointInject(uri = "{{eip.route.customer-processing.start}}")
    protected ProducerTemplate producerTemplate;

    @MockBean
    private CustomerService customerService;

    @Test
    public void test() {
        producerTemplate.sendBody("{{eip.route.customer-processing.start}}", payload);
        Mockito.verify(customerService, times(1)).create(any(Customer.class));
    }

}
