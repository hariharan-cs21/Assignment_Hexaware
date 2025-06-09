package com.springboot.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hospitalmanagement.model.Patient;
import com.springboot.hospitalmanagement.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	PatientService patientService;

	@PostMapping("/register")
	public Patient insertPatient(@RequestBody Patient patient) {
		return patientService.insertPatient(patient);
	}
}