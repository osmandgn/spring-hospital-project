package com.runners.service;

import com.runners.domain.Patient;
import com.runners.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

   @Autowired
   private PatientRepository patientRepository;

    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
