package com.example.demo.user;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PatientInfo;

public interface PatientRepo extends JpaRepository<PatientInfo, Long> {

	boolean existsByPhoneNumber(String phoneNumber);

}
