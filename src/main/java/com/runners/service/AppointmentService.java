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


//        boolean patientExists = patientService.existByid(appointment.getPatientId());
//        boolean doctorExists = doctorService.existById(appointment.getDoctorId());
//
//        if (!patientExists) {
//            throw new ResourceNotFoundException("Patient doesn't exist ! Please create new patient.");
//        } else if (!doctorExists) {
//            throw new ResourceNotFoundException("Doctor doesn't exist ! Please choose another doctor.");
//        } else {
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointmentRepository.save(appointment);




    }
}
