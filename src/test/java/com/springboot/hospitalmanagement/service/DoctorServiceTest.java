package com.springboot.hospitalmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.hospitalmanagement.model.Doctor;
import com.springboot.hospitalmanagement.model.Patient;
import com.springboot.hospitalmanagement.model.PatientDoctor;
import com.springboot.hospitalmanagement.repository.DoctorRepository;
import com.springboot.hospitalmanagement.repository.PatientDoctorRepository;

@SpringBootTest
class DoctorServiceTest {

	@InjectMocks
	private DoctorService doctorService;

	@Mock
	private DoctorRepository doctorRepository;

	@Mock
	private PatientDoctorRepository patientDoctorRepository;

	private Doctor doctor;
	private Patient patient1;
	private Patient patient2;
	private PatientDoctor pd1;
	private PatientDoctor pd2;

	@BeforeEach
	public void init() {
		doctor = new Doctor();
		doctor.setId(1);
		doctor.setName("Mohan");

		patient1 = new Patient();
		patient1.setId(101);
		patient1.setName("Hari");

		patient2 = new Patient();
		patient2.setId(102);
		patient2.setName("Arun");

		pd1 = new PatientDoctor();
		pd1.setDoctor(doctor);
		pd1.setPatient(patient1);

		pd2 = new PatientDoctor();
		pd2.setDoctor(doctor);
		pd2.setPatient(patient2);
	}

	// for getting patient when a correct doctor id has patient available
	@Test
	public void getPatientByDoctorId() {
		List<PatientDoctor> pdList = Arrays.asList(pd1, pd2);
		List<Patient> expected = Arrays.asList(patient1, patient2);

		when(doctorRepository.getDoctorByUsername("mohan")).thenReturn(doctor);
		when(patientDoctorRepository.findByDoctorId(doctor.getId())).thenReturn(pdList);

		List<Patient> actual = doctorService.getPatientsByDoctorId("mohan");

		
		assertEquals(expected.get(0).getName(), actual.get(0).getName());

	}

	// Invalid doctor username
	@Test
	public void getPatientsByDoctorId_DoctorInvalid() {

		when(doctorRepository.getDoctorByUsername("random")).thenReturn(null);

		assertThrows(RuntimeException.class, () -> doctorService.getPatientsByDoctorId("random"));
	}
	
	//no patients for valid doctor
	
	@Test
	public void getPatientsByDoctorId_NoPatients() {
	    when(doctorRepository.getDoctorByUsername("mohan")).thenReturn(doctor);
	    when(patientDoctorRepository.findByDoctorId(doctor.getId())).thenReturn(List.of());

	    List<Patient> actual = doctorService.getPatientsByDoctorId("mohan");

	    assertEquals(0, actual.size());
	}
	@AfterEach
	public void end() {
		doctor=null;
		patient1=null;
		patient2=null;
		pd1=null;
		pd2=null;
	}
	

}
