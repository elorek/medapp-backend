package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.AppointmentDto;
import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.Patient;
import com.crud.medapp.backend.mapper.AppointmentMapper;
import com.crud.medapp.backend.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private AppointmentMapper appointmentMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createAppointment", consumes = APPLICATION_JSON_VALUE)
    public void createAppointment(@RequestBody AppointmentDto appointmentDto) {
        databaseService.saveAppointment(appointmentMapper.mapToAppointment(appointmentDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAppointment")
    public AppointmentDto getAppointment(@RequestParam Long appointmentId) throws AppointmentNotFoundException {
        return appointmentMapper.mapToAppointmentDto(databaseService.getAppointment(appointmentId)
                .orElseThrow(AppointmentNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAppointments")
    public List<AppointmentDto> getAppointments() {
        return appointmentMapper.mapToAppointmentDtoList(databaseService.getAllAppointments());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAppointmentsByDoctor")
    public List<AppointmentDto> getAppointmentsByDoctor(Doctor doctor) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAppointmentsByPatient")
    public List<AppointmentDto> getAppointmentsByPatient(Patient patient) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateAppointment")
    public AppointmentDto updateAppointment(@RequestBody AppointmentDto appointmentDto) {
        return appointmentMapper.mapToAppointmentDto(databaseService.saveAppointment(appointmentMapper.mapToAppointment(appointmentDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteAppointment")
    public void deleteAppointment(@RequestParam Long appointmentId) {
        databaseService.deleteAppointment(appointmentId);
    }
}
