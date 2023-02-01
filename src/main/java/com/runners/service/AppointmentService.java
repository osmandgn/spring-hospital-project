package com.runners.service;


import com.runners.domain.Appointment;
import com.runners.domain.Doctor;
import com.runners.domain.Patient;
import com.runners.dto.AppDto;
import com.runners.dto.AppRequest;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.AppointmentRepository;
import com.runners.repository.DoctorRepository;
import com.runners.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    @Lazy
    private AppointmentRepository appointmentRepository;

    @Autowired
    @Lazy
    private DoctorService doctorService;

    @Autowired
    @Lazy
    private PatientService patientService;


    public void createAppointment(Appointment appointment) {

        Patient patient = patientService.getPatientById(appointment.getPatientId());
        Doctor doctor = doctorService.getDoctorById(appointment.getDoctorId());

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

    public List<AppDto> findAppDtoByDoctor(Doctor doctor){
      List<Appointment> appointments = appointmentRepository.findAllByDoctor(doctor);
      List<AppDto> appDtoList = new ArrayList<>();
      for(Appointment app: appointments){
          appDtoList.add(new AppDto(app));
      }
      return appDtoList;
    }

    public List<AppDto> findAppDtoByPatient(Patient patient){
        List<Appointment> appointments = appointmentRepository.findAllByPatient(patient);
        List<AppDto> appDtoList = new ArrayList<>();
        for(Appointment app: appointments){
            appDtoList.add(new AppDto(app));
        }
        return appDtoList;
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
       if( appointmentRepository.existsById(id)){
           appointmentRepository.deleteById(id);
       }else throw new ResourceNotFoundException("Appointment not found with id : "+id);
    }
}
