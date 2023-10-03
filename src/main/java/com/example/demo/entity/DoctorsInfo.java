package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "DoctrosInfo")
public class DoctorsInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name="Doctor_id" )
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="city")
	private String city;
	@Column(name="email")
	private String email;
	@Column(name="phone")
	private String phone;
	@Column(name="specility")
	private String specility;

}
