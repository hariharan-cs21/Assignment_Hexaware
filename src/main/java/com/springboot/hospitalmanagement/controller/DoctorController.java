package com.springboot.hospitalmanagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hospitalmanagement.model.Doctor;
import com.springboot.hospitalmanagement.service.DoctorService;


@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	@Autowired
	DoctorService doctorService;

	@PostMapping("/register")
	public Doctor insertDoctor(@RequestBody Doctor doctor) {
		return doctorService.insertDoctor(doctor);
	}
	@GetMapping("/patients")
	public ResponseEntity<?>getPatientByDoctorId(Principal principal){
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.getPatientsByDoctorId(principal.getName()));
	}
}
