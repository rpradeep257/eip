package com.ps.exercise.eip.service;

import com.ps.exercise.eip.entity.Customer;
import com.ps.exercise.eip.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Default implementation of CustomerService
 */
@Service(value = "customerService")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class CustomerServiceImpl implements CustomerService {
	
    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
    	customerRepository.save(customer);
        return customer;
    }
}
