package com.example.demo.doctor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DoctorsInfo;



public interface DoctorRepo extends JpaRepository<DoctorsInfo, Long> {

	boolean existsByPhone(String phone);

	List<DoctorsInfo> findBySpecilityAndCity(String specility, String city);

}
