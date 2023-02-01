package com.runners.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runners.domain.Appointment;
import com.runners.domain.Doctor;
import com.runners.domain.enums.Department;
import com.runners.domain.enums.Prefix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DocResponse {




    private String name;



    private Prefix prefixName;


    private Department departmentName;


    private Integer dateOfGraduate;


    private Integer dateOfStart;


    private List<Appointment> appointmentList = new ArrayList<>();

    public DocResponse(Doctor doctor) {
        this.name = doctor.getName();
        this.prefixName = doctor.getPrefixName();
        this.departmentName = doctor.getDepartmentName();
        this.dateOfGraduate = doctor.getDateOfGraduate();
        this.dateOfStart = doctor.getDateOfStart();
        this.appointmentList = doctor.getAppointmentList();
    }
}
