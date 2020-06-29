package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.DoctorDto;
import com.crud.medapp.backend.domain.Patient;
import com.crud.medapp.backend.domain.PatientDto;
import com.crud.medapp.backend.mapper.PatientMapper;
import com.crud.medapp.backend.service.DatabaseService;
import com.google.gson.Gson;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DatabaseService databaseService;
    @MockBean
    private PatientMapper patientMapper;

    @Test
    public void shouldCreatePatient() throws Exception {
        //Given
        Patient patient = new Patient(2L, "name2", "last_name2", "pesel2", "adress2",
                null);
        PatientDto patientDto = new PatientDto(2L, "name2", "last_name2", "pesel2",
                "adress2", null);
        when(databaseService.savePatient(patient)).thenReturn(patient);
        when(patientMapper.mapToPatientDto(patient)).thenReturn(patientDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(patientDto);

        //When & Then
        mockMvc.perform(post("/v1/patient/createPatient").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shoudFetchPatient() throws Exception {
        //Given
        Patient patient = new Patient(1L, "name1", "last_name1", "pesel1", "adress1",
                null);
        PatientDto patientDto = new PatientDto(1L, "name1", "last_name1", "pesel1", 
                "adress1", null);
        when(databaseService.getPatient(1L)).thenReturn(Optional.ofNullable(patient));
        when(patientMapper.mapToPatientDto(patient)).thenReturn(patientDto);

        //When & Then
        mockMvc.perform(get("/v1/patient/getPatient?patientId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name1")))
                .andExpect(jsonPath("$.last_name", is("last_name1")))
                .andExpect(jsonPath("$.pesel", is("pesel1")))
                .andExpect(jsonPath("$.adress", is("adress1")));
    }

    @Test
    public void shouldFetchPatients () throws Exception {
        //Given
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1L, "name1", "last_name1", "pesel1", "adress1",
                null));
        patients.add(new Patient(2L, "name2", "last_name2", "pesel2", "adress2",
                null));
        patients.add(new Patient(3L, "name3", "last_name3", "pesel3", "adress3",
                null));

        List<PatientDto> patientDtos = new ArrayList<>();
        patientDtos.add((new PatientDto(1L, "name1", "last_name1", "pesel1", "adress1",
                null)));
        patientDtos.add(new PatientDto(2L, "name2", "last_name2", "pesel2", "adress2",
                null));
        patientDtos.add(new PatientDto(3L, "name3", "last_name3", "pesel3", "adress2",
                null));

        when(databaseService.getAllPatients()).thenReturn(patients);
        when(patientMapper.mapToPatientDtoList(patients)).thenReturn(patientDtos);

        //When & Then
        mockMvc.perform(get("/v1/patient/getPatients").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("name1")))
                .andExpect(jsonPath("$[1].name", is("name2")))
                .andExpect(jsonPath("$[2].name", is("name3")))
                .andExpect(jsonPath("$[0].last_name", is("last_name1")))
                .andExpect(jsonPath("$[1].last_name", is("last_name2")))
                .andExpect(jsonPath("$[2].last_name", is("last_name3")))
                .andExpect(jsonPath("$[0].pesel", is("pesel1")))
                .andExpect(jsonPath("$[1].pesel", is("pesel2")))
                .andExpect(jsonPath("$[2].pesel", is("pesel3")))
                .andExpect(jsonPath("$[0].adress", is("adress1")))
                .andExpect(jsonPath("$[1].adress", is("adress2")))
                .andExpect(jsonPath("$[2].adress", is("adress3")));
    }

    @Test
    public void shouldUpdatePatient() throws Exception {
        //Given
        Patient patient = new Patient(3l, "name3", "last_name3", "pesel3", "adress3",
                null);
        PatientDto patientDto = new PatientDto(3L, "name3", "last_name3", "pesel3",
                "adress3",null);
        when(databaseService.savePatient(patient)).thenReturn(patient);
        when(patientMapper.mapToPatientDto(patient)).thenReturn(patientDto);
        when(patientMapper.mapToPatient(ArgumentMatchers.any())).thenReturn(patient);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(patientDto);

        //When & Then
        mockMvc.perform(put("/v1/patient/updatePatient").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("name3")))
                .andExpect(jsonPath("$.last_name", is("last_name3")))
                .andExpect(jsonPath("$.pesel", is("pesel3")))
                .andExpect(jsonPath("$.adress", is("adress3")));

    }

    @Test
    public void shouldDeletePatient() throws Exception {
        //Given
        Patient patient = new Patient(2L, "name2", "last_name2", "pesel2", "adress2",
                null);

        //When & Then
        mockMvc.perform(delete("/v1/patient/deletePatient?patientId=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
