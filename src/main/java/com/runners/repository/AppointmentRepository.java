package com.runners.repository;


import com.runners.domain.Appointment;
import com.runners.domain.Doctor;
import com.runners.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {


    List<Appointment> findAllByPatient(Patient patient);
    List<Appointment> findAllByDoctor(Doctor doctor);








}


