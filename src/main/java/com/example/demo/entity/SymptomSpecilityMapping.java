package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="symptom_specility_mapping")
public class SymptomSpecilityMapping {
	//this table helpful to map sympton with specility so we dont need to map sympton with doctor in 
	//service it will save memory and time.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name="SymptomSpecilityMapping_id" )
	private long id;
	
	@Column(name="symptom")
	private String symptom;
	@Column(name="specility")
	private String specility; 
	// IN THIS TABLE WE HAVE THIS SYMPTOM WITH THEIR SPECILITY
//	1. Arthritis - Orthopedic
//	2. Back Pain - Orthopedic
//	3. Tissue injuries - Orthopedic
//	4. Dysmenorrhea - Gynecology
//	5. Skin infection - Dermatology
//	6. skin burn - Dermatology
//	7. Ear pain - ENT
	

}
