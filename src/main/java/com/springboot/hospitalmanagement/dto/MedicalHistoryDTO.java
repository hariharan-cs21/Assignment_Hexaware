package com.springboot.hospitalmanagement.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.hospitalmanagement.model.MedicalHistory;

@Component
public class MedicalHistoryDTO {
	private String illness;
    private int numOfYears;
    private String currentMedication;
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	public int getNumOfYears() {
		return numOfYears;
	}
	public void setNumOfYears(int numOfYears) {
		this.numOfYears = numOfYears;
	}
	public String getCurrentMedication() {
		return currentMedication;
	}
	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}
	public List<MedicalHistoryDTO> convertHistoryIntoDto(List<MedicalHistory> list) {
		List<MedicalHistoryDTO> listDto = new ArrayList<>();
		list.stream().forEach(i -> {
			MedicalHistoryDTO dto=new MedicalHistoryDTO();
			dto.setIllness(i.getIllness());
			dto.setCurrentMedication(i.getCurrentMedication());
			dto.setNumOfYears(i.getNumOfYears());
			listDto.add(dto);
		});

		return listDto;
	}
}
