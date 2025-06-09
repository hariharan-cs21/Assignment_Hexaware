package com.springboot.hospitalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalmanagement.model.PatientDoctor;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer> {
	List<PatientDoctor> findByDoctorId(int doctorId);
}
