package com.springboot.hospitalmanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hospitalmanagement.dto.MedicalHistoryDTO;
import com.springboot.hospitalmanagement.dto.PatientRegistrationWithData;
import com.springboot.hospitalmanagement.model.MedicalHistory;
import com.springboot.hospitalmanagement.service.MedicalHistoryService;

@RestController
public class MedicalHistoryController {
	@Autowired
	private MedicalHistoryService medicalHistoryService;


	@PostMapping("/api/medicalhistory/add")
	public ResponseEntity<?> addMedicalHistory(Principal principal, @RequestBody MedicalHistory medicalHistory) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(medicalHistoryService.addMedicalHistory(principal.getName(), medicalHistory));
	}
	
	@GetMapping("/api/medicalhistory/{patientId}")
    public ResponseEntity<?> getMedicalHistory(@PathVariable int patientId) {
        List<MedicalHistoryDTO> history = medicalHistoryService.getPatientMedicalHistoryById(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(history);
    }
	
	
	//add user,patient,medical history together
	@PostMapping("api/patient/add/all")
	public ResponseEntity<?> registerPatient(@RequestBody PatientRegistrationWithData data) {
	    try {
	        Object result = medicalHistoryService.registerPatientWithUserAndMedicalHistory(data);
	        return ResponseEntity.status(HttpStatus.OK).body(result);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}


}
