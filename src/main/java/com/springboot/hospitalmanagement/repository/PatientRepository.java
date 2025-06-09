package com.springboot.hospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.hospitalmanagement.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	@Query("select p from Patient p where p.user.username=?1")
	Patient getPatientByUsername(String username);
}
