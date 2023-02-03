package com.runners.controller;


import com.runners.domain.Doctor;
import com.runners.dto.DocResponse;
import com.runners.dto.DoctorDTO;
import com.runners.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping // http://localhost:8080/v1/doctor
    public ResponseEntity<Map<String, String>> createDoctor(@Valid @RequestBody Doctor doctor) {
        doctorService.createDoctor(doctor);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Doctor is created successfully ");
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<DocResponse>> getAllDoc(){

        List<DocResponse> docResponseList = doctorService.getAllDoc();
        return ResponseEntity.ok(docResponseList);

    }



    @GetMapping("/{id}") // http://localhost:8080/v1/doctor/1
    public ResponseEntity<DocResponse> getDocDTOById(@PathVariable("id") Long id) {

        DocResponse dto = doctorService.getByIdDTO(id);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}") // http://localhost:8080/v1/doctor/1
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        doctorService.deleteDoc(id);

        String message = "Doctor is deleted successfully.";

        return ResponseEntity.ok(message);


    }

    @PutMapping("/{id}") // http://localhost:8080/v1/doctor/1
    public ResponseEntity<Map<String, String>> updateDoctor(@PathVariable("id") Long id,
                                                           @Valid @RequestBody DoctorDTO doctorDTO){

        doctorService.updateDoctor(id,doctorDTO);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Doctor is updated successfully");
        map.put("status", "true");

        return new ResponseEntity<>(map,HttpStatus.OK);
    }


}
