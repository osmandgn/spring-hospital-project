package com.runners.dto;


import com.runners.domain.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class AppPatDto {


    private String date;

    private Integer hour;

    private Integer minute;


    private String notes;

    private String prefixTitle;

    private String doctorName;

    private String departmentName;



    public AppPatDto(Appointment appointment) {
        this.date= appointment.getDate();
        this.hour = appointment.getHour();
        this.minute = appointment.getMinute();
        this.notes = appointment.getNotes();
        this.doctorName= appointment.getDoctor().getName();
        this.prefixTitle = appointment.getDoctor().getPrefixName().name();
        this.departmentName= appointment.getDoctor().getDepartmentName().name();




    }
}
