package com.runners.service;


import com.runners.domain.Patient;
import com.runners.dto.AppPatDto;
import com.runners.dto.PatResponse;
import com.runners.dto.PatientDto;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired

    private PatientRepository patientRepository;

    @Autowired
    @Lazy
    private AppointmentService appointmentService;


    public void createPatient(Patient patient) {

        patientRepository.save(patient);

    }

    public List<Patient> getAll() {

        return patientRepository.findAll();

    }


    public List<PatResponse> getAllPatient() {
         List<Patient> patientList = patientRepository.findAll();
         List<PatResponse> patResponseList = new ArrayList<>();

         for(Patient w: patientList){
             PatResponse pat = new PatResponse(w);
             List<AppPatDto> appPatDtoList = appointmentService.findAppDtoByPatient(w);
             pat.setList(appPatDtoList);
             patResponseList.add(pat);
         }
        return patResponseList;
    }

    public PatResponse findPatient(Long id) {

        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Patient not found by id : " + id));

        List<AppPatDto> appPatDtoList = appointmentService.findAppDtoByPatient(patient);
        PatResponse patientDto = new PatResponse(patient);
        patientDto.setList(appPatDtoList);

        return patientDto;

    }

    public void deletePatient(Long id) {

        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Patient not found by id : " + id);

    }


    public void updatePatient(Long id, PatientDto patientDto) {

        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found by id : " + id);
        }
        Patient patient = patientRepository.getById(id);

        patient.setName(patientDto.getFirstName());
        patient.setGender(patientDto.getGender());
        patient.setCity(patientDto.getCity());
        patient.setAddress(patientDto.getAddress());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setHealthInsurance(patientDto.isHealthInsurance());

        patientRepository.save(patient);

    }

    public Patient getPatientById(Long id) {

        return patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Patient not found by id :" + id));
    }

    public boolean existByid(Long id) {

        boolean exists = patientRepository.existsById(id);
        return exists;
    }



}
