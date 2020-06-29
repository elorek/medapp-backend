package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.*;
import com.crud.medapp.backend.domain.Appointment;
import com.crud.medapp.backend.domain.AppointmentDto;
import com.crud.medapp.backend.mapper.AppointmentMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DatabaseService databaseService;
    @MockBean
    private AppointmentMapper appointmentMapper;

    @Test
    public void shouldCreateAppointment() throws Exception {
        //Given
        Appointment appointment = new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build();
        
        AppointmentDto appointmentDto = new AppointmentDto.AppointmentDtoBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build();
        when(databaseService.saveAppointment(appointment)).thenReturn(appointment);
        when(appointmentMapper.mapToAppointmentDto(appointment)).thenReturn(appointmentDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(appointmentDto);

        //When & Then
        mockMvc.perform(post("/v1/appointment/createAppointment").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shoudFetchAppointment() throws Exception {
        //Given
        Appointment appointment = new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build();
        AppointmentDto appointmentDto = new AppointmentDto.AppointmentDtoBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build();
        when(databaseService.getAppointment(1L)).thenReturn(Optional.ofNullable(appointment));
        when(appointmentMapper.mapToAppointmentDto(appointment)).thenReturn(appointmentDto);

        //When & Then
        mockMvc.perform(get("/v1/appointment/getAppointment?appointmentId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.patient_id", is(1)))
                .andExpect(jsonPath("$.appointment_id", is(1)))
                .andExpect(jsonPath("$.year", is(2020)))
                .andExpect(jsonPath("$.month", is(07)))
                .andExpect(jsonPath("$.day", is(01)))
                .andExpect(jsonPath("$.hour", is(15)))
                .andExpect(jsonPath("$.minute", is(15)))
                .andExpect(jsonPath("$.where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$.room", is(5)));
    }

    @Test
    public void shouldFetchAppointments() throws Exception {
        //Given
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build());
        appointments.add(new Appointment.AppointmentBuilder().Id(2L).Patient_id(3).Doctor_id(5)
                .When(2020, 10, 23, 18, 30).Where("Testowa 1 Testowo").Room(8).build());
        appointments.add(new Appointment.AppointmentBuilder().Id(3L).Patient_id(8).Doctor_id(15)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(3).build());
        
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build());
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(2L).Patient_id(3).Doctor_id(5)
                .When(2020, 10, 23, 18, 30).Where("Testowa 1 Testowo").Room(8).build());
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(3L).Patient_id(8).Doctor_id(15)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(3).build());
        when(databaseService.getAllAppointments()).thenReturn(appointments);
        when(appointmentMapper.mapToAppointmentDtoList(appointments)).thenReturn(appointmentDtos);

        //When & Then
        mockMvc.perform(get("/v1/appointment/getAppointments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].patient_id", is(1)))
                .andExpect(jsonPath("$[1].patient_id", is(3)))
                .andExpect(jsonPath("$[2].patient_id", is(8)))
                .andExpect(jsonPath("$[0].doctor_id", is(1)))
                .andExpect(jsonPath("$[1].doctor_id", is(5)))
                .andExpect(jsonPath("$[2].doctor_id", is(15)))
                .andExpect(jsonPath("$[0].year", is(2020)))
                .andExpect(jsonPath("$[1].year", is(2020)))
                .andExpect(jsonPath("$[2].year", is(2020)))
                .andExpect(jsonPath("$[0].month", is(07)))
                .andExpect(jsonPath("$[1].month", is(10)))
                .andExpect(jsonPath("$[2].month", is(07)))
                .andExpect(jsonPath("$[0].day", is(01)))
                .andExpect(jsonPath("$[1].day", is(23)))
                .andExpect(jsonPath("$[2].day", is(01)))
                .andExpect(jsonPath("$[0].hour", is(15)))
                .andExpect(jsonPath("$[1].hour", is(18)))
                .andExpect(jsonPath("$[2].hour", is(15)))
                .andExpect(jsonPath("$[0].minute", is(15)))
                .andExpect(jsonPath("$[1].minute", is(30)))
                .andExpect(jsonPath("$[2].minute", is(15)))
                .andExpect(jsonPath("$[0].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[1].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[2].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[0].room", is(5)))
                .andExpect(jsonPath("$[1].room", is(8)))
                .andExpect(jsonPath("$[2].room", is(3)));
    }
    
    @Test
    public void shouldFetchAppointmentsByDoctor() throws Exception {
        //Given
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build());
        appointments.add(new Appointment.AppointmentBuilder().Id(2L).Patient_id(3).Doctor_id(5)
                .When(2020, 10, 23, 18, 30).Where("Testowa 1 Testowo").Room(8).build());
        appointments.add(new Appointment.AppointmentBuilder().Id(3L).Patient_id(8).Doctor_id(15)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(3).build());

        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build());
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(2L).Patient_id(3).Doctor_id(5)
                .When(2020, 10, 23, 18, 30).Where("Testowa 1 Testowo").Room(8).build());
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(3L).Patient_id(8).Doctor_id(15)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(3).build());

        Doctor doctor = new Doctor();

        //when(databaseService.getAppointmentsByDoctor(Long doctor_id)).thenReturn(appointments);
        when(appointmentMapper.mapToAppointmentDtoList(appointments)).thenReturn(appointmentDtos);

        //When & Then
        mockMvc.perform(get("/v1/appointment/getAppointmentsByDoctor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].patient_id", is(1)))
                .andExpect(jsonPath("$[1].patient_id", is(3)))
                .andExpect(jsonPath("$[2].patient_id", is(8)))
                .andExpect(jsonPath("$[0].doctor_id", is(1)))
                .andExpect(jsonPath("$[1].doctor_id", is(5)))
                .andExpect(jsonPath("$[2].doctor_id", is(15)))
                .andExpect(jsonPath("$[0].year", is(2020)))
                .andExpect(jsonPath("$[1].year", is(2020)))
                .andExpect(jsonPath("$[2].year", is(2020)))
                .andExpect(jsonPath("$[0].month", is(07)))
                .andExpect(jsonPath("$[1].month", is(10)))
                .andExpect(jsonPath("$[2].month", is(07)))
                .andExpect(jsonPath("$[0].day", is(01)))
                .andExpect(jsonPath("$[1].day", is(23)))
                .andExpect(jsonPath("$[2].day", is(01)))
                .andExpect(jsonPath("$[0].hour", is(15)))
                .andExpect(jsonPath("$[1].hour", is(18)))
                .andExpect(jsonPath("$[2].hour", is(15)))
                .andExpect(jsonPath("$[0].minute", is(15)))
                .andExpect(jsonPath("$[1].minute", is(30)))
                .andExpect(jsonPath("$[2].minute", is(15)))
                .andExpect(jsonPath("$[0].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[1].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[2].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[0].room", is(5)))
                .andExpect(jsonPath("$[1].room", is(8)))
                .andExpect(jsonPath("$[2].room", is(3)));
    }
    
    @Test
    public void shouldFetchAppointmentsByPatient() throws Exception {
        //Given
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build());
        appointments.add(new Appointment.AppointmentBuilder().Id(2L).Patient_id(3).Doctor_id(5)
                .When(2020, 10, 23, 18, 30).Where("Testowa 1 Testowo").Room(8).build());
        appointments.add(new Appointment.AppointmentBuilder().Id(3L).Patient_id(8).Doctor_id(15)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(3).build());

        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build());
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(2L).Patient_id(3).Doctor_id(5)
                .When(2020, 10, 23, 18, 30).Where("Testowa 1 Testowo").Room(8).build());
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(3L).Patient_id(8).Doctor_id(15)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(3).build());

        //Patient patient = new Patient(1L, "Tom", "Johnson", "Bajkowa 1 Bajkowo");

        //when(databaseService.getAppointmentsByPatient(patient_id)).thenReturn(appointments);
        when(appointmentMapper.mapToAppointmentDtoList(appointments)).thenReturn(appointmentDtos);

        //When & Then
        mockMvc.perform(get("/v1/appointment/getAppointmentsByPatient")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].patient_id", is(1)))
                .andExpect(jsonPath("$[1].patient_id", is(3)))
                .andExpect(jsonPath("$[2].patient_id", is(8)))
                .andExpect(jsonPath("$[0].doctor_id", is(1)))
                .andExpect(jsonPath("$[1].doctor_id", is(5)))
                .andExpect(jsonPath("$[2].doctor_id", is(15)))
                .andExpect(jsonPath("$[0].year", is(2020)))
                .andExpect(jsonPath("$[1].year", is(2020)))
                .andExpect(jsonPath("$[2].year", is(2020)))
                .andExpect(jsonPath("$[0].month", is(07)))
                .andExpect(jsonPath("$[1].month", is(10)))
                .andExpect(jsonPath("$[2].month", is(07)))
                .andExpect(jsonPath("$[0].day", is(01)))
                .andExpect(jsonPath("$[1].day", is(23)))
                .andExpect(jsonPath("$[2].day", is(01)))
                .andExpect(jsonPath("$[0].hour", is(15)))
                .andExpect(jsonPath("$[1].hour", is(18)))
                .andExpect(jsonPath("$[2].hour", is(15)))
                .andExpect(jsonPath("$[0].minute", is(15)))
                .andExpect(jsonPath("$[1].minute", is(30)))
                .andExpect(jsonPath("$[2].minute", is(15)))
                .andExpect(jsonPath("$[0].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[1].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[2].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[0].room", is(5)))
                .andExpect(jsonPath("$[1].room", is(8)))
                .andExpect(jsonPath("$[2].room", is(3)));
    }

    @Test
    public void shouldUpdateAppointment() throws Exception {
        //Given
        Appointment appointment = new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build();
        AppointmentDto appointmentDto = new AppointmentDto.AppointmentDtoBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build();
        when(databaseService.saveAppointment(appointment)).thenReturn(appointment);
        when(appointmentMapper.mapToAppointmentDto(appointment)).thenReturn(appointmentDto);
        when(appointmentMapper.mapToAppointment(ArgumentMatchers.any())).thenReturn(appointment);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(appointmentDto);

        //When & Then
        mockMvc.perform(put("/v1/appointment/updateAppointment").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].patient_id", is(1)))
                .andExpect(jsonPath("$[0].doctor_id", is(1)))
                .andExpect(jsonPath("$[0].year", is(2020)))
                .andExpect(jsonPath("$[0].month", is(07)))
                .andExpect(jsonPath("$[0].day", is(01)))
                .andExpect(jsonPath("$[0].hour", is(15)))
                .andExpect(jsonPath("$[0].minute", is(15)))
                .andExpect(jsonPath("$[0].where", is("Testowa 1 Testowo")))
                .andExpect(jsonPath("$[0].room", is(5)));
    }

    @Test
    public void shouldDeleteAppointment() throws Exception {
        //Given
        Appointment appointment = new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 07, 01, 15, 15).Where("Testowa 1 Testowo").Room(5).build();

        //When & Then
        mockMvc.perform(delete("/v1/appointment/deleteAppointment?appointmentId=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
