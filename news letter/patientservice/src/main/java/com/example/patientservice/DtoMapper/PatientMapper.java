package com.example.patientservice.DtoMapper;

import com.example.patientservice.entity.PatientEntity;
import com.example.patientservice.model.PatientModel;
import com.example.patientservice.model.PatientRequestDTO;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientModel toDTO(PatientEntity patientEntity) {
        PatientModel patientModelDTO = new PatientModel();
        patientModelDTO.setId(patientEntity.getId().toString());
        patientModelDTO.setName(patientEntity.getName());
        patientModelDTO.setAddress(patientEntity.getAddress());
        patientModelDTO.setEmail(patientEntity.getEmail());
        patientModelDTO.setDataOfBirth(patientEntity.getDateOfBirth().toString());
        return patientModelDTO;
    }
    public static PatientEntity toEntity(PatientRequestDTO patientRequestDTO) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setName(patientRequestDTO.getName());
        patientEntity.setAddress(patientRequestDTO.getAddress());
        patientEntity.setEmail(patientRequestDTO.getEmail());
        patientEntity.setAddress(String.valueOf(patientRequestDTO.getAddress()));
        patientEntity.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patientEntity.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patientEntity;
    }
}
