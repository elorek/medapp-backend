package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.DoctorDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {
    @RequestMapping(method = RequestMethod.POST, value = "createDoctor")
    public void createDoctor(DoctorDto doctorDto) {}

    @RequestMapping(method = RequestMethod.GET, value = "getDoctor")
    public DoctorDto getDoctor(Long doctorId) {
        return new DoctorDto(1L, "Thomas", "Johnson", "General Practicioner");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDoctors")
    public List<DoctorDto> getDoctors() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateDoctor")
    public DoctorDto updateDoctor(DoctorDto doctorDto) {
        return new DoctorDto(1L, "Thomas", "Johnson", "Surgeon");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteDoctor")
    public void deleteDoctor(Long doctorId) {}
    
}
