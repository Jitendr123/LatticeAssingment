package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PatientInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/patient")
@Api(tags = "Patient Information", value = "Patient Information", description = "Operations pertaining to Patient in PatientInfo information Entity")
public class PatientController {
	@Autowired
	PatientService patientService;
	
	@ApiOperation(value = "API for Patient to create their profile", httpMethod = "Post", notes = " This Api use for Patient to create their profile")
	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	public ResponseEntity create(@RequestBody PatientInfo patientInfo) {
		return patientService.create(patientInfo);
	}
	
	@ApiOperation(value = "API for Patient to delete their profile", httpMethod = "Delete", notes = " This Api use for Patient to delete their profile")
	@RequestMapping(value = "/delete", method = { RequestMethod.DELETE })
	public ResponseEntity delete(@RequestParam Long patientId) {
		return patientService.delete(patientId);
	}
	
	@ApiOperation(value = "API for Patient to get their profile", httpMethod = "Get", notes = " This Api use for Patient to get their profile")
	@RequestMapping(value = "/get", method = { RequestMethod.GET })
	public ResponseEntity get(@RequestParam Long patientId) {
		return patientService.get(patientId);
	}
	
	@ApiOperation(value = "API for Patient to get doctor according to sympton and near by area", httpMethod = "Get", notes = " This Api use for Patient to get doctor info")
	@RequestMapping(value = "/findDoctor", method = { RequestMethod.GET })
	public ResponseEntity getDoctorForPatient(@RequestParam Long patientId) {
		return patientService.getDoctorForPatient(patientId);
	}

}
