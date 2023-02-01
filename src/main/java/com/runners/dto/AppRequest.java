package com.runners.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class AppRequest {

    @NotNull
    private Long doctorId;


    @NotNull
    @NotBlank
    private String date;

    @NotNull
    private Integer hour;


    @NotNull
    private Integer minute;


    private String notes;


}
