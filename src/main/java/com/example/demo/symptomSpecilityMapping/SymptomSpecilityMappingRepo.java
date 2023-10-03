package com.example.demo.symptomSpecilityMapping;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SymptomSpecilityMapping;

public interface SymptomSpecilityMappingRepo extends JpaRepository<SymptomSpecilityMapping, Long>{


	SymptomSpecilityMapping findBySymptomContaining(String symptom);

	SymptomSpecilityMapping findBySymptom(String symptom);

	

}
