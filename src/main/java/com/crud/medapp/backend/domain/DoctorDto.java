package com.crud.medapp.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String last_name;
    private String specialization;
}
