package com.example.patientservice.controllers;

import com.example.patientservice.Interface.CreatePatientValidationGroup;
import com.example.patientservice.Interface.PatientServiceInterface;
import com.example.patientservice.entity.PatientEntity;
import com.example.patientservice.model.PatientModel;
import com.example.patientservice.model.PatientRequestDTO;
import com.example.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientContoller {
    @Autowired
    private PatientServiceInterface patientServiceInterface;

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientModel>> getAllPatients() {
        return ResponseEntity.ok(patientServiceInterface.getAllPatient());
    }

    @PostMapping
    @Operation(summary = "Create a New Patient")
    public ResponseEntity<PatientModel> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patient) {
        return ResponseEntity.ok(patientServiceInterface.createPatient(patient));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Patient")
    public ResponseEntity<PatientModel> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patient) {
        return ResponseEntity.ok(patientServiceInterface.updatePatient(id, patient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Patient")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id) {
        return ResponseEntity.ok(patientServiceInterface.deletePatient(id));
    }
}
