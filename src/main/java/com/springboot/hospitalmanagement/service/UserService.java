package com.springboot.hospitalmanagement.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.model.User;
import com.springboot.hospitalmanagement.repository.DoctorRepository;
import com.springboot.hospitalmanagement.repository.PatientRepository;
import com.springboot.hospitalmanagement.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private DoctorRepository doctorRepository;
	private PatientRepository patientRepository;

	public UserService(PatientRepository patientRepository,UserRepository userRepository, PasswordEncoder passwordEncoder,DoctorRepository doctorRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.doctorRepository=doctorRepository;
		this.patientRepository=patientRepository;

	}

	public User signUp(User user) {
		String plainPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	public Object getUserInfo(String username) {
		User user = userRepository.getByUsername(username);
		switch (user.getRole().toUpperCase()) {
		case "DOCTOR":
			return doctorRepository.getDoctorByUsername(username);
		case "PATIENT":
			return patientRepository.getPatientByUsername(username);
		default:
			return null;
		}
	}

}