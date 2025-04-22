package com.example.patientservice.kafka;

import com.example.patientservice.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendEvent(PatientEntity patientEntity) {
        PatientEvent patientEvent = PatientEvent.newBuilder()
                .setPatientId(patientEntity.getId().toString())
                .setEmail(patientEntity.getEmail())
                .setName(patientEntity.getName())
                .setEventType("PATIENT_CREATED")
                .build();
        try{
            kafkaTemplate.send("patientdata", patientEvent.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
