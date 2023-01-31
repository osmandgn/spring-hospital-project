package com.runners.controller;


import com.runners.domain.Appointment;
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

        String message = "Appointment is created succesfully.";

        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id){
        Appointment appointment = appointmentService.getAppointment(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointmentList(){
        List<Appointment> appointmentList = appointmentService.getAll();
        return ResponseEntity.ok(appointmentList);
    }









}
