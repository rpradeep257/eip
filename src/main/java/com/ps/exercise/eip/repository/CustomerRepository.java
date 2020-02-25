package com.ps.exercise.eip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ps.exercise.eip.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
