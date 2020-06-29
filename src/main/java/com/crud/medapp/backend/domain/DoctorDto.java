package com.crud.medapp.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String last_name;
    private String specialization;
    private List<Appointment> appointments = new ArrayList<>();

}
