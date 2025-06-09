package com.springboot.hospitalmanagement.service;

import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.model.Patient;
import com.springboot.hospitalmanagement.model.User;
import com.springboot.hospitalmanagement.repository.PatientRepository;

@Service
public class PatientService {

	private PatientRepository patientRepository;
	private UserService userService;

	public PatientService(PatientRepository patientRepository,UserService userService) {
		this.patientRepository = patientRepository;
		this.userService = userService;
	}
	public Patient getPatientByUsername(String username) {
		return patientRepository.getPatientByUsername(username);
	}

	public Patient insertPatient(Patient patient) {
		User user = patient.getUser();
		user.setRole("PATIENT");
		user = userService.signUp(user);
		patient.setUser(user);
		return patientRepository.save(patient);
	}

}
