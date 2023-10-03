package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "patient_info")
public class PatientInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name="patient_id" )
	private long id;
	@Column(name="name")   
	private String name;
	@Column(name="city")
	private String city;
	@Column(name="email")
	private String email;
	@Column(name="phone_number")
	private String phoneNumber; 
	@Column(name="sympton")    
	private String sympton;
	
	

}
