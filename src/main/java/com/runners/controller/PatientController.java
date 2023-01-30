package com.runners.controller;

import com.runners.domain.Doctor;
import com.runners.domain.Patient;
import com.runners.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping
    public ResponseEntity<Map<String, String>> createPatient(@Valid @RequestBody Patient patient){
        patientService.createPatient(patient);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Doctor created succesfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> patients = patientService.getAll();
        return ResponseEntity.ok(patients);
    }
}
