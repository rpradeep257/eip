package com.ps.exercise.eip;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.exercise.eip.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
public class CustomerServiceIntegrationTest {
	
	private String successPayload = "{\"firstName\" : \"john\", \"lastName\" : \"wick\", \"age\" : \"18\"}";
	private String errorPayload = "{\"firstName\" : \"john\", \"lastName\" : \"wick\"}";

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @LocalServerPort
	private int port;
	
	@Test
	public void shouldCreateCustomer() {
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:"+port+"/services/rest/api/customers", successPayload, String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Customer created", response.getBody());
		assertEquals(1, customerRepository.findAll().size());
	}
	
	@Test
	public void shouldFailBecauseOfInvalidJson() {
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:"+port+"/services/rest/api/customers", errorPayload, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("JSON validation failed", response.getBody());
	}

}
