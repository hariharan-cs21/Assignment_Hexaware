package com.springboot.hospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.hospitalmanagement.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	@Query("select d from Doctor d where d.user.username=?1")
	Doctor getDoctorByUsername(String username);
}

