package com.example.demo.symptomSpecilityMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SymptomSpecilityMapping;
import com.example.demo.helper.ResponseEntityObject;

@Service
public class SymptomSpecilityMappingService {
	@Autowired
	SymptomSpecilityMappingRepo symptomSpecilityMappingRepo;

	public ResponseEntity create(SymptomSpecilityMapping symptomSpecilityMapping) {
		try {
			if(symptomSpecilityMapping.getSpecility()==null || symptomSpecilityMapping.getSymptom()==null) {
				return new ResponseEntity(new ResponseEntityObject(true,"your data is empty"),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			SymptomSpecilityMapping ssdata= symptomSpecilityMappingRepo.findBySymptom(symptomSpecilityMapping.getSymptom());
			if(ssdata !=null) {
				return new ResponseEntity(new ResponseEntityObject(true,"According to this symptom data is present",ssdata),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			SymptomSpecilityMapping saveData=symptomSpecilityMappingRepo.saveAndFlush(symptomSpecilityMapping);
			return new ResponseEntity(new ResponseEntityObject(true,"Success",saveData),HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
