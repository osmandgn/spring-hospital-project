package com.runners.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runners.domain.enums.Department;
import com.runners.domain.enums.Prefix;
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
public class Doctor {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30, message = "First name '${validatedValue}' must be between {min} and {max} long")
    @NotBlank(message = "First name cannot be empty or blank !")
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Prefix prefixName;

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(min = 5, max=5, message = "TcNo '${validatedValue}' must be five characters exactly !")
    private String tcNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department departmentName;


    private Integer dateOfGraduate;


    private Integer dateOfStart;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList = new ArrayList<>();

    @JoinColumn(name = "user_id")
    @OneToOne
    private  User user;

























}
