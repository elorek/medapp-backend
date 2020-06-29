package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.DoctorDto;
import com.crud.medapp.backend.mapper.DoctorMapper;
import com.crud.medapp.backend.service.DatabaseService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DatabaseService databaseService;
    @MockBean
    private DoctorMapper doctorMapper;

    @Test
    public void shouldCreateDoctor() throws Exception {
        //Given
        Doctor doctor = new Doctor(2L, "name2", "last_name2", "spec2", null);
        DoctorDto doctorDto = new DoctorDto(2L, "name2", "last_name2", "spec2", null);
        when(databaseService.saveDoctor(doctor)).thenReturn(doctor);
        when(doctorMapper.mapToDoctorDto(doctor)).thenReturn(doctorDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(doctorDto);

        //When & Then
        mockMvc.perform(post("/v1/doctor/createDoctor").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shoudFetchDoctor() throws Exception {
        //Given
        Doctor doctor = new Doctor(1L, "name1", "last_name1", "spec1", null);
        DoctorDto doctorDto = new DoctorDto(1L, "name1", "last_name1", "spec1", null);
        when(databaseService.getDoctor(1L)).thenReturn(Optional.ofNullable(doctor));
        when(doctorMapper.mapToDoctorDto(doctor)).thenReturn(doctorDto);

        //When & Then
        mockMvc.perform(get("/v1/doctor/getDoctor?doctorId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name1")))
                .andExpect(jsonPath("$.last_name", is("last_name1")))
                .andExpect(jsonPath("$.specialization", is("spec1")));
    }

    @Test
    public void shouldFetchDoctorsBySpec() throws Exception {
        //Given
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(1L, "name1", "last_name2", "spec1", null));
        doctors.add(new Doctor(2L, "name2", "last_name2", "spec2", null));
        doctors.add(new Doctor(3L, "name3", "last_name3", "spec1", null));

        List<DoctorDto> doctorDtos = new ArrayList<>();
        doctorDtos.add(new DoctorDto(1L, "name1", "last_name2", "spec1", null));
        doctorDtos.add(new DoctorDto(2L, "name2", "last_name2", "spec2", null));
        doctorDtos.add(new DoctorDto(3L, "name3", "last_name3", "spec1", null));
        when(databaseService.getDoctorsBySpecialization("spec1")).thenReturn(doctors);
        when(doctorMapper.mapToDoctorDtoList(doctors)).thenReturn(doctorDtos);

        //When & Then
        mockMvc.perform(get("/v1/doctor/getDoctorsBySpecialization?specialization=spec2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("name1")))
                .andExpect(jsonPath("$[1].name", is("name3")))
                .andExpect(jsonPath("$[0].last_name", is("last_name1")))
                .andExpect(jsonPath("$[1].last_name", is("last_name3")))
                .andExpect(jsonPath("$.specialization", is("spec1")));
    }

    @Test
    public void shouldFetchDoctors () throws Exception {
        //Given
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(1L, "name1", "last_name1", "spec1", null));
        doctors.add(new Doctor(2L, "name2", "last_name2", "spec2", null));
        doctors.add(new Doctor(3L, "name3", "last_name3", "spec3", null));

        List<DoctorDto> doctorDtos = new ArrayList<>();
        doctorDtos.add((new DoctorDto(1L, "name1", "last_name1", "spec1", null)));
        doctorDtos.add(new DoctorDto(2L, "name2", "last_name2", "spec2", null));
        doctorDtos.add(new DoctorDto(3L, "name3", "last_name3", "spec3", null));

        when(databaseService.getAllDoctors()).thenReturn(doctors);
        when(doctorMapper.mapToDoctorDtoList(doctors)).thenReturn(doctorDtos);

        //When & Then
        mockMvc.perform(get("/v1/doctor/getDoctors").contentType(MediaType.APPLICATION_JSON))
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
                .andExpect(jsonPath("$[0].specialization", is("spec1")))
                .andExpect(jsonPath("$[1].specialization", is("spec2")))
                .andExpect(jsonPath("$[2].specialization", is("spec3")));
    }

    @Test
    public void shouldUpdateDoctor() throws Exception {
        //Given
        Doctor doctor = new Doctor(3l, "name3", "last_name3", "spec3", null);
        DoctorDto doctorDto = new DoctorDto(3L, "name3", "last_name3", "spec3", null);
        when(databaseService.saveDoctor(doctor)).thenReturn(doctor);
        when(doctorMapper.mapToDoctorDto(doctor)).thenReturn(doctorDto);
        when(doctorMapper.mapToDoctor(ArgumentMatchers.any())).thenReturn(doctor);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(doctorDto);

        //When & Then
        mockMvc.perform(put("/v1/doctor/updateDoctor").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("name3")))
                .andExpect(jsonPath("$.last_name", is("last_name3")))
                .andExpect(jsonPath("$.specialization", is("spec3")));
    }

    @Test
    public void shouldDeleteDoctor() throws Exception {
        //Given
        Doctor doctor = new Doctor(2L, "name2", "last_name2", "spec2", null);

        //When & Then
        mockMvc.perform(delete("/v1/doctor/deleteDoctor?doctorId=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
