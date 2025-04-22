package com.example.patientservice.service;

import com.example.patientservice.DtoMapper.PatientMapper;
import com.example.patientservice.Interface.PatientServiceInterface;
import com.example.patientservice.entity.PatientEntity;
import com.example.patientservice.exceptions.EmailAlreadyExistException;
import com.example.patientservice.exceptions.PatientNotFoundException;
import com.example.patientservice.grpc.BillingServiceGrpcClient;
import com.example.patientservice.kafka.KafkaProducer;
import com.example.patientservice.model.PatientModel;
import com.example.patientservice.model.PatientRequestDTO;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService implements PatientServiceInterface {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private BillingServiceGrpcClient billingServiceGrpcClient;
    @Autowired
    private KafkaProducer kafkaProducer;

    public PatientModel createPatient(PatientRequestDTO patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new EmailAlreadyExistException("A patient with this email " + "already exist" + patient.getEmail());
        }
        PatientEntity entity = patientRepository.save(PatientMapper.toEntity(patient));
        billingServiceGrpcClient.createBillingAccount(entity.getId().toString(), entity.getName(), entity.getEmail());
        kafkaProducer.sendEvent(entity);
        return PatientMapper.toDTO(entity);
    }

    @Override
    public PatientModel updatePatient(UUID id, PatientRequestDTO patient) {
        PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        if (patientRepository.existsByEmailAndIdNot(patient.getEmail(), id)) {
            throw new EmailAlreadyExistException("A patient with this email " + "already exist" + patient.getEmail());
        }
        patientEntity.setName(patient.getName());
        patientEntity.setEmail(patient.getEmail());
        patientEntity.setDateOfBirth(LocalDate.parse(patient.getDateOfBirth()));
        PatientEntity updatedPatient = patientRepository.save(patientEntity);
        return PatientMapper.toDTO(updatedPatient);
    }

    @Override
    public String deletePatient(UUID id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            return "Patient not found with ID: " + id;
        }
        return "Successfully deleted";
    }

    @Override
    public List<PatientModel> getAllPatient() {
        List<PatientEntity> patientEntities = patientRepository.findAll();
        List<PatientModel> patientModels = patientEntities.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
        return patientModels;
    }
}
