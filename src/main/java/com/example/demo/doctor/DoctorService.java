package com.example.demo.doctor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DoctorsInfo;
import com.example.demo.helper.ResponseEntityObject;



@Service
public class DoctorService {
	@Autowired
	DoctorRepo doctorRepo;
	
	//this method use for email is valid or not 
	    public static boolean isValidEmail(String email) {
	    	String EMAIL_REGEX ="^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.[A-Za-z]{2,4}$";
	        Pattern pattern = Pattern.compile(EMAIL_REGEX);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	    //this method to check specility belongs to our valid specility
	    public static boolean isValidSpecility(String specility) {
	    	if(specility.equalsIgnoreCase("ENT") || 
					specility.equalsIgnoreCase("DERMANTOLOGY") ||
					specility.equalsIgnoreCase("GYNECOLOGY") ||
					specility.equalsIgnoreCase("ORTHOPEDIC")) {
				return true;
			}else {
				return false;
			}
	    }
//    method use to create entry in our database
	public ResponseEntity create(DoctorsInfo doctorInfo) {
		try {
			//if data already exits with that phone number than return
			if(doctorRepo.existsByPhone(doctorInfo.getPhone())) {
				return new ResponseEntity("Your phone number already exits with data in our database so first delete you data than continue",HttpStatus.OK);
			}
			String doctorCity=doctorInfo.getCity().toUpperCase();
//			Speciality can have 4 values i.e. Orthopedic, Gynecology, Dermatology, ENT specialist
			if(!isValidSpecility(doctorInfo.getSpecility())){
				return new ResponseEntity("Your Specility does not belong to our databaes but we will contact you later",HttpStatus.OK);
			}
			else if(!isValidEmail(doctorInfo.getEmail())){
				return new ResponseEntity("Invalid Email",HttpStatus.OK);
			}
			else if(doctorInfo.getPhone().length()>10){
				return new ResponseEntity("mobile number can not be grater than 10 digit",HttpStatus.OK);
			}
			else if(doctorInfo.getName().length()<=3){
				return new ResponseEntity("number of character in name should be grater than 3 digit",HttpStatus.OK);
			}
			else if((doctorCity.equals("NOIDA") || doctorCity.equals("FARIDABAD") || doctorCity.equals("Delhi"))) {
				doctorInfo.setSpecility(doctorInfo.getSpecility().toLowerCase());
				DoctorsInfo d= doctorRepo.saveAndFlush(doctorInfo);
				return new ResponseEntity(new ResponseEntityObject(true,"Success",d),HttpStatus.OK);				
			}else {
				return new ResponseEntity("We are still waiting to expand to your location",HttpStatus.OK);		
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	method to delete doctor info from database
	public ResponseEntity delete(Long doctorId) {
		try {
			if(!doctorRepo.existsById(doctorId)) {
				return new ResponseEntity("given doctor id does not exits in our database",HttpStatus.OK);
			}else {
				doctorRepo.deleteById(doctorId);
				return new ResponseEntity(new ResponseEntityObject(true,"Success"),HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	method use to get data from database
	public ResponseEntity get(Long doctorId) {
		try {
			if(!doctorRepo.existsById(doctorId)) {
				return new ResponseEntity("given doctor id does not exits in our database",HttpStatus.OK);
			}else {
				DoctorsInfo doctorInfo= doctorRepo.findById(doctorId).orElse(null);
				return new ResponseEntity(new ResponseEntityObject(true,"Success",doctorInfo),HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	


}
