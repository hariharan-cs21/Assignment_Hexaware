package com.springboot.hospitalmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.model.Doctor;
import com.springboot.hospitalmanagement.model.Patient;
import com.springboot.hospitalmanagement.model.PatientDoctor;
import com.springboot.hospitalmanagement.model.User;
import com.springboot.hospitalmanagement.repository.DoctorRepository;
import com.springboot.hospitalmanagement.repository.PatientDoctorRepository;

@Service
public class DoctorService {

	private DoctorRepository doctorRepository;
	private UserService userService;
	private PatientDoctorRepository patientDoctorRepository;

	public DoctorService(DoctorRepository doctorRepository,
			com.springboot.hospitalmanagement.service.UserService userService,
			PatientDoctorRepository patientDoctorRepository) {
		this.doctorRepository = doctorRepository;
		this.userService = userService;
		this.patientDoctorRepository = patientDoctorRepository;
	}

	public Doctor getDoctorByUsername(String username) {
		return doctorRepository.getDoctorByUsername(username);
	}

	public Doctor insertDoctor(Doctor doctor) {
		User user = doctor.getUser();
		user.setRole("DOCTOR");
		user = userService.signUp(user);
		doctor.setUser(user);
		return doctorRepository.save(doctor);
	}
	public List<Patient>getPatientsByDoctorId(String username){
		Doctor doctor=doctorRepository.getDoctorByUsername(username);
		if(doctor==null) throw new RuntimeException("Doctor not found");
		List<PatientDoctor> list = patientDoctorRepository.findByDoctorId(doctor.getId());
		return list.stream().map(v->v.getPatient()).toList();
		
	}

}
