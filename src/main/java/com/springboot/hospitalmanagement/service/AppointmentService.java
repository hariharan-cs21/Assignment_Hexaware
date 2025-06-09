package com.springboot.hospitalmanagement.service;

import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.model.Doctor;
import com.springboot.hospitalmanagement.model.Patient;
import com.springboot.hospitalmanagement.model.PatientDoctor;
import com.springboot.hospitalmanagement.repository.DoctorRepository;
import com.springboot.hospitalmanagement.repository.PatientDoctorRepository;
import com.springboot.hospitalmanagement.repository.PatientRepository;

@Service
public class AppointmentService {

   
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private PatientDoctorRepository patientDoctorRepository;
    

	public AppointmentService(PatientRepository patientRepository, DoctorRepository doctorRepository,
			PatientDoctorRepository patientDoctorRepository) {
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.patientDoctorRepository = patientDoctorRepository;
	}


	public PatientDoctor createAppointment(int patientId, int doctorId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        PatientDoctor appointment = new PatientDoctor();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return patientDoctorRepository.save(appointment);
    }
}
