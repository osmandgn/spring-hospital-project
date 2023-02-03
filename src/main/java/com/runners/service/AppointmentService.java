package com.runners.service;


import com.runners.domain.Appointment;
import com.runners.domain.Doctor;
import com.runners.domain.Patient;
import com.runners.dto.AppointmentDtoForDoc;
import com.runners.dto.AppDto;
import com.runners.dto.AppointmentDtoForPat;
import com.runners.dto.AppRequest;
import com.runners.exception.ConflictException;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.AppointmentRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        for (Appointment w : patient.getAppointmentList()) {
            if (Objects.equals(w.getDoctorId(), appointment.getDoctorId())) {
                throw new ConflictException("You cannot get appointment for the same Doctor");
            }
        }

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);

    }

    public List<AppDto> getAllDto() {

        List<Appointment> appointment = appointmentRepository.findAll();
        List<AppDto> appDtoList = new ArrayList<>();


        for (Appointment w : appointment) {
            AppDto app = new AppDto(w);
            appDtoList.add(app);
        }
        return appDtoList;

    }

    public AppDto findAppDto(Long id) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Appointment not found by id : " + id));
        AppDto appDto = new AppDto(appointment);
        return appDto;
    }

    public void updateAppointment(Long id, AppRequest appRequest) {

        Doctor doctor = doctorService.getDoctorById(appRequest.getDoctorId());
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Appointment not found by id : " + id));
        appointment.setDoctorId(appRequest.getDoctorId());
        appointment.setHour(appRequest.getHour());
        appointment.setDate(appRequest.getDate());
        appointment.setMinute(appRequest.getMinute());
        appointment.setNotes(appRequest.getNotes());

        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);


    }

    public void deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Appointment not found with id : " + id);
    }

    public List<AppointmentDtoForPat> findAppDtoByPatient(Patient patient) {

        List<Appointment> list = appointmentRepository.findAllByPatient(patient);
        List<AppointmentDtoForPat> appPatDtoList = new ArrayList<>();

        for (Appointment w : list) {

            appPatDtoList.add(new AppointmentDtoForPat(w));
        }
        return appPatDtoList;

    }

    public List<AppointmentDtoForDoc> findAppDtoByDoctor(Doctor doctor) {

        List<Appointment> list = appointmentRepository.findAllByDoctor(doctor);
        List<AppointmentDtoForDoc> appDocDtoList = new ArrayList<>();
        for (Appointment w : list) {
            appDocDtoList.add(new AppointmentDtoForDoc(w));
        }
        return appDocDtoList;
    }
}
