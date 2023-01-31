package com.runners.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runners.domain.enums.City;
import com.runners.domain.enums.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {


    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @NotBlank(message = "First name cannot be white space !")
    @Size(min = 2, max=20, message = "First name '${validatedValue}' must be between {min} and {max} long !")
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Integer dateOfBirth;

    @Enumerated(EnumType.STRING)
    private City city;


    private String address;

    private boolean healthInsurance;


    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Appointment> appointmentList = new ArrayList<>();


















}
