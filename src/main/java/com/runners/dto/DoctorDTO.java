package com.runners.dto;


import com.runners.domain.Doctor;
import com.runners.domain.enums.Department;
import com.runners.domain.enums.Prefix;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@AllArgsConstructor
@Getter
@Setter

public class DoctorDTO {


    @Size(min = 2, max = 30, message = "First name '${validatedValue}' must be between {min} and {max} long")
    @NotBlank(message = "First name cannot be empty or blank !")
    private String firstName;



    private Prefix prefix;


    private String tcNo;


    private Department department;


    private Integer dateOfGraduate;


    private Integer dateOfStart;

    public DoctorDTO(Doctor doctor){

        this.firstName = doctor.getName();
        this.prefix = doctor.getPrefixName();
        this.tcNo = doctor.getTcNo();
        this.department = doctor.getDepartmentName();
        this.dateOfGraduate = doctor.getDateOfGraduate();
        this.dateOfStart = doctor.getDateOfStart();

    }




}
