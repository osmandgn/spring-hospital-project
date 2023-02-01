package com.runners.dto;

import com.runners.domain.Appointment;
import com.runners.domain.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter

public class AppDto {


    private String date;

    private Integer hour;

    private Integer minute;


    private String notes;

    private String patientName;

    private String doctorName;

    private String departmentName;

    public AppDto(Appointment appointment) {
        this.doctorName= appointment.getDoctor().getName();
        this.patientName= appointment.getPatient().getName();
        this.date= appointment.getDate();
        this.hour = appointment.getHour();
        this.minute = appointment.getMinute();
        this.notes = appointment.getNotes();
        this.departmentName = appointment.getDoctor().getDepartmentName().name();
    }

}
