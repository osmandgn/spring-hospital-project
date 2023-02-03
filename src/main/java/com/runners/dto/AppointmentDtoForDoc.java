package com.runners.dto;

import com.runners.domain.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentDtoForDoc {

    private String date;

    private Integer hour;

    private Integer minute;


    private String notes;

    private String patientName;

    private Integer dateOfBirth;

    private boolean healthInsurance;

    public AppointmentDtoForDoc(Appointment appointment) {
        this.date= appointment.getDate();
        this.hour = appointment.getHour();
        this.minute = appointment.getMinute();
        this.notes = appointment.getNotes();
        this.patientName = appointment.getPatient().getName();
        this.dateOfBirth = appointment.getPatient().getDateOfBirth();
        this.healthInsurance = appointment.getPatient().isHealthInsurance();

    }
}
