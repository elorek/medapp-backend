package com.crud.medapp.backend.controller;

import com.crud.medapp.backend.domain.DoctorDto;
import com.crud.medapp.backend.mapper.DoctorMapper;
import com.crud.medapp.backend.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private DoctorMapper doctorMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createDoctor", consumes = APPLICATION_JSON_VALUE)
    public void createDoctor(@RequestBody DoctorDto doctorDto) {
        databaseService.saveDoctor(doctorMapper.mapToDoctor(doctorDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDoctor")
    public DoctorDto getDoctor(@RequestParam Long doctorId) throws DoctorNotFoundException {
        return doctorMapper.mapToDoctorDto(databaseService.getDoctor(doctorId).orElseThrow(DoctorNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDoctors")
    public List<DoctorDto> getDoctors() {
        return doctorMapper.mapToDoctorDtoList(databaseService.getAllDoctors());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateDoctor")
    public DoctorDto updateDoctor(@RequestBody DoctorDto doctorDto) {
         return doctorMapper.mapToDoctorDto(databaseService.saveDoctor(doctorMapper.mapToDoctor(doctorDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteDoctor")
    public void deleteDoctor(@RequestParam Long doctorId) {
        databaseService.deleteDoctor(doctorId);
    }
    
}
