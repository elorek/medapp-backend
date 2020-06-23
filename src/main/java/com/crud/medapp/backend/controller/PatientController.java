package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.PatientDto;
import com.crud.medapp.backend.mapper.PatientMapper;
import com.crud.medapp.backend.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private PatientMapper patientMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createPatient", consumes = APPLICATION_JSON_VALUE)
    public void createPatient(@RequestBody PatientDto patientDto) {
        databaseService.savePatient(patientMapper.mapToPatient(patientDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPatient")
    public PatientDto getPatient(@RequestParam Long patientId) throws PatientNotFoundException {
        return patientMapper.mapToPatientDto(databaseService.getPatient(patientId).orElseThrow(PatientNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPatients")
    public List<PatientDto> getPatients() {
        return patientMapper.mapToPatientDtoList(databaseService.getAllPatients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPatientsByDoctor")
    public List<PatientDto> getPatientsByDoctor(Doctor doctor) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updatePatient")
    public PatientDto updatePatient(@RequestBody PatientDto patientDto) {
        return patientMapper.mapToPatientDto(databaseService.savePatient(patientMapper.mapToPatient(patientDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deletePatient")
    public void deletePatient(@RequestParam Long patientId) {
        databaseService.deletePatient(patientId);
    }
}
