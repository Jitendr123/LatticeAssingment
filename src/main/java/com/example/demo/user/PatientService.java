package com.example.demo.user;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.doctor.DoctorRepo;
import com.example.demo.entity.DoctorsInfo;
import com.example.demo.entity.PatientInfo;
import com.example.demo.entity.SymptomSpecilityMapping;
import com.example.demo.helper.ResponseEntityObject;
import com.example.demo.symptomSpecilityMapping.SymptomSpecilityMappingRepo;

@Service
public class PatientService {
	@Autowired
	PatientRepo patientRepo;
	@Autowired
	SymptomSpecilityMappingRepo symptomSpecilityMappingRepo;
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
//    Arthritis, Back Pain, Tissue injuries (comes under Orthopedic speciality)
//    Dysmenorrhea (comes under Gynecology speciality)
//    Skin infection, skin burn (comes under Dermatology speciality)
//    Ear pain (comes under ENT speciality)
    public static boolean isValidSymptom(String sympytom) {
    	if(sympytom.equalsIgnoreCase("Arthritis") || 
    			sympytom.equalsIgnoreCase("Back Pain") ||
    			sympytom.equalsIgnoreCase("Tissue injuries") ||
    			sympytom.equalsIgnoreCase("Dysmenorrhea") ||
    			sympytom.equalsIgnoreCase("Skin infection") ||
    			sympytom.equalsIgnoreCase("skin burn") ||
    			sympytom.equalsIgnoreCase("Ear pain")) {
			return true;
		}else {
			return false;
		}
    }
//method use to create entry in our database
public ResponseEntity create(PatientInfo patientInfo) {
	try {
		//if data already exits with that phone number than return
		if(patientRepo.existsByPhoneNumber(patientInfo.getPhoneNumber())) {
			return new ResponseEntity("Your phone number already exits with data in our database so first delete you data than continue",HttpStatus.OK);
		}
		String doctorCity=patientInfo.getCity().toUpperCase();
//		Speciality can have 4 values i.e. Orthopedic, Gynecology, Dermatology, ENT specialist
		if(!isValidSymptom(patientInfo.getSympton())){
			return new ResponseEntity("Your sympton does not belong to our databaes but are expanding in to our specility",HttpStatus.OK);
		}
		else if(!isValidEmail(patientInfo.getEmail())){
			return new ResponseEntity("Invalid Email",HttpStatus.OK);
		}
		else if(patientInfo.getPhoneNumber().length()>10){
			return new ResponseEntity("mobile number can not be grater than 10 digit",HttpStatus.OK);
		}
		else if(patientInfo.getName().length()<=3){
			return new ResponseEntity("number of character in name should be grater than 3 digit",HttpStatus.OK);
		}
		else if((doctorCity.equals("NOIDA") || doctorCity.equals("FARIDABAD") || doctorCity.equals("Delhi"))) {
			patientInfo.setSympton(patientInfo.getSympton().toLowerCase());
			patientInfo.setCity(patientInfo.getCity().toLowerCase());
			PatientInfo patientSaveInfo= patientRepo.saveAndFlush(patientInfo);
			return new ResponseEntity(new ResponseEntityObject(true,"Success",patientSaveInfo),HttpStatus.OK);				
		}else {
			return new ResponseEntity("We are still waiting to expand to your location",HttpStatus.OK);		
		}
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
//method to delete patient info from database
public ResponseEntity delete(Long patientId) {
	try {
		if(!patientRepo.existsById(patientId)) {
			return new ResponseEntity("given patient id does not exits in our database",HttpStatus.OK);
		}else {
			patientRepo.deleteById(patientId);
			return new ResponseEntity(new ResponseEntityObject(true,"Success"),HttpStatus.OK);
		}
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
//method use to get data from database
public ResponseEntity get(Long patientId) {
	try {
		if(!patientRepo.existsById(patientId)) {
			return new ResponseEntity("given patient id does not exits in our database",HttpStatus.OK);
		}else {
			PatientInfo patientinfo= patientRepo.findById(patientId).orElse(null);
			return new ResponseEntity(new ResponseEntityObject(true,"Success",patientinfo),HttpStatus.OK);
		}
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

//method use to get data from database
public ResponseEntity getDoctorForPatient(Long patientId) {
	try {
		if(!patientRepo.existsById(patientId)) {
			return new ResponseEntity("given patient id does not exits in our database",HttpStatus.OK);
		}else {
			//get patient info so we can get symptom and location
			PatientInfo patientinfo= patientRepo.findById(patientId).orElse(null);
			String symptom=patientinfo.getSympton().toLowerCase();
			String city=patientinfo.getCity();
			//by the symptom we can get specility 
			SymptomSpecilityMapping symptomSpecilityMapping=symptomSpecilityMappingRepo.findBySymptomContaining(symptom);
			if(symptomSpecilityMapping==null) {
				return new ResponseEntity(new ResponseEntityObject(true,"According to your symptom we don't have any specility doctor"),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String specility=symptomSpecilityMapping.getSpecility();
//			System.out.println(specility+city);
			List<DoctorsInfo> doctorInfo=doctorRepo.findBySpecilityAndCity(specility,city);
//			if no doctor present according to location
			if(doctorInfo==null) {
				return new ResponseEntity(new ResponseEntityObject(true,"There isn’t any doctor present at your location for your symptom"),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity(new ResponseEntityObject(true,"Success",doctorInfo),HttpStatus.OK);
		}
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}



}
