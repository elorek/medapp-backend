package com.crud.medapp.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private String last_name;
    private String pesel;
    private String adress;
    private List<Appointment> appointments = new ArrayList<>();

}
