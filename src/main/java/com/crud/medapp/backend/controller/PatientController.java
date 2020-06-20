package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.PatientDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
    @RequestMapping(method = RequestMethod.POST, value = "createPatient")
    public void createPatient(PatientDto patientDto) {}

    @RequestMapping(method = RequestMethod.GET, value = "getPatient")
    public PatientDto getPatient(Long patientId) {
        return new PatientDto(1L, "Bob", "Morrison", "63218427159", "Zagadkowa 8, Zagadkowo");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPatients")
    public List<PatientDto> getPatients() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updatePatient")
    public PatientDto updatePatient(PatientDto patientDto) {
        return new PatientDto(1L, "Tony", "Morrison", "63218427159", "Zagadkowa 8, Zagadkowo");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deletePatient")
    public void deletePatient(Long patientId) {}
}
