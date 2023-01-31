package com.runners.dto;


import com.runners.domain.Patient;
import com.runners.domain.enums.City;
import com.runners.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class PatientDto {

    @NotNull
    @NotBlank(message = "First name cannot be white space !")
    @Size(min = 2, max=20, message = "First name '${validatedValue}' must be between {min} and {max} long !")
    private String firstName;


    private Gender gender;


    private Integer dateOfBirth;


    private City city;


    private String address;

    private boolean healthInsurance;


    public PatientDto(Patient patient){

        this.firstName= patient.getName();
        this.gender = patient.getGender();
        this.dateOfBirth= patient.getDateOfBirth();
        this.city = patient.getCity();
        this.address = patient.getAddress();
        this.healthInsurance = patient.isHealthInsurance();


    }











}
