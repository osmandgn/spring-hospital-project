package com.runners.controller;


import com.runners.domain.Appointment;
import com.runners.dto.AppDto;
import com.runners.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/appointment")       // http://localhost:8080/v1/
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<String> createAppointment (@Valid @RequestBody Appointment appointment){
        appointmentService.createAppointment(appointment);

        String message = "Appointment is created successfully.";

        return ResponseEntity.ok(message);

    }

    @GetMapping
    public ResponseEntity<List<AppDto>> getAllDto(){
       List<AppDto> appDtoList = appointmentService.getAllDto();

       return ResponseEntity.ok(appDtoList);

    }







}
