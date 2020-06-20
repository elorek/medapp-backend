package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.AppointmentDto;
import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.Patient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {
    @RequestMapping(method = RequestMethod.POST, value = "createAppointment")
    public void createAppointment(AppointmentDto appointmentDto) {}

    @RequestMapping(method = RequestMethod.GET, value = "getAppointment")
    public AppointmentDto getAppointment(Long appointmentId) {
        return new AppointmentDto(1L, 12, 5, 2020, 6, 25, 15, 30,
                "Testowa 1, Testowo", 18);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAppointments")
    public List<AppointmentDto> getAppointments() {
        return new ArrayList<>();
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
    public AppointmentDto updateAppointment(AppointmentDto appointmentDto) {
        return new AppointmentDto(1L, 12, 5, 2020, 6, 30, 13, 15,
                "Testowa 1, Testowo", 5);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteAppointment")
    public void deleteAppointment(Long appointmentId) {}
}
