package com.example.demo.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DoctorsInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/doctor")
@Api(tags = "Doctors Information", value = "Doctors Information", description = "Operations pertaining to Doctors in Doctors information Entity")
public class DoctorController {
	@Autowired
	DoctorService doctorService;
	
	@ApiOperation(value = "API for Doctors to create their profile", httpMethod = "Post", notes = " This Api use for doctors to create their profile")
	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	public ResponseEntity create(@RequestBody DoctorsInfo doctorInfo) {
		return doctorService.create(doctorInfo);
	}
	
	@ApiOperation(value = "API for Doctors to delete their profile", httpMethod = "Delete", notes = " This Api use for doctors to delete their profile")
	@RequestMapping(value = "/delete", method = { RequestMethod.DELETE })
	public ResponseEntity delete(@RequestParam Long doctorId) {
		return doctorService.delete(doctorId);
	}
	
	@ApiOperation(value = "API for Doctors to get their profile", httpMethod = "Get", notes = " This Api use for doctors to get their profile")
	@RequestMapping(value = "/get", method = { RequestMethod.GET })
	public ResponseEntity get(@RequestParam Long doctorId) {
		return doctorService.get(doctorId);
	}

}
