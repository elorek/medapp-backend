package com.crud.medapp.backend.mapper;

import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.DoctorDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {
    public Doctor mapToDoctor(final DoctorDto doctorDto) {
        return new Doctor(doctorDto.getId(), doctorDto.getName(), doctorDto.getLast_name(), doctorDto.getSpecialization());
    }

    public DoctorDto mapToDoctorDto(final Doctor doctor) {
        return new DoctorDto(doctor.getId(), doctor.getName(), doctor.getLast_name(), doctor.getSpecialization());
    }

    public List<DoctorDto> mapToDoctorDtoList(final List<Doctor> doctorList) {
        return doctorList.stream().map(d -> new DoctorDto(d.getId(), d.getName(), d.getLast_name(), d.getSpecialization()))
                .collect(Collectors.toList());
    }
}
