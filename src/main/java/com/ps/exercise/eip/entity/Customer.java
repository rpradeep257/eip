package com.ps.exercise.eip.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ToString.Exclude
	private long id;
	@Version
	@ToString.Exclude
	private int version;
	private String firstName;
	private String lastName;
	private int age;

}
