package com.example.patientservice.Interface;

import com.example.patientservice.entity.PatientEntity;
import com.example.patientservice.model.PatientModel;
import com.example.patientservice.model.PatientRequestDTO;

import java.util.List;
import java.util.UUID;

public interface PatientServiceInterface {

    List<PatientModel> getAllPatient();

    PatientModel createPatient(PatientRequestDTO patient);

    PatientModel updatePatient(UUID id , PatientRequestDTO patient);

    String deletePatient(UUID id);
}
