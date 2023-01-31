package com.runners.service;


import com.runners.domain.Appointment;
import com.runners.domain.Doctor;
import com.runners.domain.Patient;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.AppointmentRepository;
import com.runners.repository.DoctorRepository;
import com.runners.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;


    public void createAppointment(Appointment appointment) {

        Patient patient = patientService.getPatientById(appointment.getPatientId());
        Doctor doctor = doctorService.getDoctorById(appointment.getDoctorId());

            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointmentRepository.save(appointment);




    }

    public Appointment getAppointment(Long id) {
       return appointmentRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("Appointment Not Found"));
    }
}
