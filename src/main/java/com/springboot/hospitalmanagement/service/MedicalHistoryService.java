package com.springboot.hospitalmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.dto.MedicalHistoryDTO;
import com.springboot.hospitalmanagement.dto.PatientRegistrationWithData;
import com.springboot.hospitalmanagement.model.MedicalHistory;
import com.springboot.hospitalmanagement.model.Patient;
import com.springboot.hospitalmanagement.model.User;
import com.springboot.hospitalmanagement.repository.MedicalHistoryRepository;
import com.springboot.hospitalmanagement.repository.PatientRepository;
import com.springboot.hospitalmanagement.repository.UserRepository;

@Service
public class MedicalHistoryService {

    private MedicalHistoryRepository medicalHistoryRepository;
    private PatientRepository patientRepository;
    private UserService userService;
    @Autowired
    MedicalHistoryDTO dto;

	public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository,
			PatientRepository patientRepository, UserService userService) {
		this.medicalHistoryRepository = medicalHistoryRepository;
		this.patientRepository = patientRepository;
		this.userService=userService;
	}


	public MedicalHistory addMedicalHistory(String username, MedicalHistory patientMedical) {
        Patient patient = patientRepository.getPatientByUsername(username);
        if (patient == null) {
            throw new RuntimeException("Patient not found ");
            
        }

        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setIllness(patientMedical.getIllness());
        medicalHistory.setNumOfYears(patientMedical.getNumOfYears());
        medicalHistory.setCurrentMedication(patientMedical.getCurrentMedication());
        medicalHistory.setPatient(patient);

        return medicalHistoryRepository.save(medicalHistory);
    }
	public List<MedicalHistoryDTO> getPatientMedicalHistoryById(int patientId) {
		Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
		List<MedicalHistory>list=medicalHistoryRepository.findByPatientId(patientId);
		return dto.convertHistoryIntoDto(list);
		
	}
	
	 public Object registerPatientWithUserAndMedicalHistory(PatientRegistrationWithData data) {
	        //getting user from entire data
	        User user = userService.signUp(data.getUser());
	        if(user==null) throw new RuntimeException("Invalid User");
	        //getting patient
	        Patient patient = data.getPatient();
	        if(patient==null) throw new RuntimeException("Invalid Patient");
	        patient.setUser(user);
	        Patient savedPatient = patientRepository.save(patient);

	        //getting history from data
	        MedicalHistory medicalHistory = data.getMedicalHistory();
	        if(medicalHistory==null) throw new RuntimeException("Invalid Medical Data");
	        medicalHistory.setPatient(savedPatient);
	        MedicalHistory savedMedicalHistory = medicalHistoryRepository.save(medicalHistory);

	        Map<String, Object> response = new HashMap<>();
	        response.put("user", user);
	        response.put("patient", savedPatient);
	        response.put("medicalHistory", savedMedicalHistory);

	        return response;
	    }
}

