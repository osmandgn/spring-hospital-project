package com.runners.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runners.domain.Appointment;
import com.runners.domain.Patient;
import com.runners.domain.enums.City;
import com.runners.domain.enums.Gender;
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
public class PatResponse {


    private String name;

    private Gender gender;


    private Integer dateOfBirth;


    private City city;


    private String address;

    private boolean healthInsurance;


    private List<AppDto> appointmentList = new ArrayList<>();

    public PatResponse(Patient patient) {
        this.name = patient.getName();
        this.gender = patient.getGender();
        this.dateOfBirth = patient.getDateOfBirth();
        this.city = patient.getCity();
        this.address = patient.getAddress();
        this.healthInsurance = patient.isHealthInsurance();
    }
}
