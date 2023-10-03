package com.example.demo.symptomSpecilityMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PatientInfo;
import com.example.demo.entity.SymptomSpecilityMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/SymptomSpecilityMapping")
@Api(tags = "SymptomSpecilityMapping", value = "map specility with symptom", description = "it will help to get specility according to symptom")
public class SymptomSpecilityMappingController {
	@Autowired
	SymptomSpecilityMappingService symptomSpecilityMappingService;
	
	@ApiOperation(value = "API for Patient to create their profile", httpMethod = "Post", notes = " This Api use for Patient to create their profile")
	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	public ResponseEntity create(@RequestBody SymptomSpecilityMapping symptomSpecilityMapping) {
		return symptomSpecilityMappingService.create(symptomSpecilityMapping);
	}

}
