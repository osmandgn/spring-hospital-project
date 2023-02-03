package com.runners.controller;


import com.runners.domain.Patient;
import com.runners.dto.AppPatDto;
import com.runners.dto.PatResponse;
import com.runners.dto.PatientDto;
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
@RequestMapping("v1/patient") //http://localhost:8080/v1/patient
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping
    public ResponseEntity<Map<String, String>> createPatient(@Valid @RequestBody Patient patient) {

        patientService.createPatient(patient);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Patient is created successfully.");
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }


    @GetMapping
    public ResponseEntity<List<PatResponse>> getAllPat() {
        List<PatResponse> patResponseList = patientService.getAllPatient();
        return ResponseEntity.ok(patResponseList);

    }

    @GetMapping("/query")
    public ResponseEntity<PatResponse> getPatient(@RequestParam("id") Long id) {

        PatResponse patientDto = patientService.findPatient(id);

        return ResponseEntity.ok(patientDto);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        String message = "Patient is deleted successfully !";

        return ResponseEntity.ok(message);

    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String, String>> updatePatient(@PathVariable("id") Long id, @Valid @RequestBody PatientDto patientDto) {

        patientService.updatePatient(id, patientDto);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Patient is updated successfully.");
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);


    }


}
