package com.runners.service;

import com.runners.domain.Patient;
import com.runners.dto.PatientDto;
import com.runners.exception.ResourceNotFoundException;
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

    public PatientDto findPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Patient not found by id " + id));
        PatientDto patientDto = new PatientDto(patient);
        return patientDto;
    }

    public void deletePatient(Long id) {
        if (patientRepository.existsById(id)){
            patientRepository.deleteById(id);
        }else throw new ResourceNotFoundException("Patient not Found");
    }
}
