package com.springboot.hospitalmanagement.dto;

import org.springframework.stereotype.Component;

import com.springboot.hospitalmanagement.model.MedicalHistory;
import com.springboot.hospitalmanagement.model.Patient;
import com.springboot.hospitalmanagement.model.User;

@Component
public class PatientRegistrationWithData {
	private User user;
    private Patient patient;
    private MedicalHistory medicalHistory;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(MedicalHistory medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
    
}
