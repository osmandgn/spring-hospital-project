package com.runners.controller;

import com.runners.domain.Doctor;
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

    @PostMapping
    public ResponseEntity<Map<String, String>> createDoctor (@Valid @RequestBody Doctor doctor){
        doctorService.createDoctor(doctor);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Doctor created succesfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDocs(){
        List<Doctor> doctorList = doctorService.getAllDocs();
        return ResponseEntity.ok(doctorList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDocDTOByID(@PathVariable Long id){
        DoctorDTO doctorDTO = doctorService.getDocById(id);
        return ResponseEntity.ok(doctorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        doctorService.deleteById(id);
        String message = "Doctor is Deleted";
        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateDoctor(@PathVariable("id") Long id,
                                                            @Valid @RequestBody DoctorDTO doctorDTO){

        doctorService.updateDoctor(id,doctorDTO);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Doctor is updated successfully");
        map.put("status", "true");

        return new ResponseEntity<>(map,HttpStatus.OK);
    }


}
