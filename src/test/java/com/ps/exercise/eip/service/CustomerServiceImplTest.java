package com.ps.exercise.eip.service;

import com.ps.exercise.eip.entity.Customer;
import com.ps.exercise.eip.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @MockBean
    private static CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void customerShouldBeSaved() {

        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setAge(18);

        customerService.create(customer);

        Mockito.verify(customerRepository, times(1)).save(customer);

    }

}
