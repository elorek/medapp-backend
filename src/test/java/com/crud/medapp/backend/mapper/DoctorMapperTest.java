package com.crud.medapp.backend.mapper;

import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.DoctorDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorMapperTest {
    @Autowired
    DoctorMapper doctorMapper;

    @Test
    public void shouldMapToDoctor() {
        //Given
        DoctorDto doctorDto = new DoctorDto(1L, "Malcolm", "Johnson", "cardiologist",
        null);
        Doctor doctor = new Doctor(1L, "Malcolm", "Johnson", "cardiologist", null);
        //When
        Doctor mappedDoctor = doctorMapper.mapToDoctor(doctorDto);
        //Then
        Assert.assertEquals(doctor, mappedDoctor);
    }

    @Test
    public void shouldMapToDoctorDto() {
        //Given
        Doctor doctor = new Doctor(2L, "Kate", "Belmond", "general practicioner", null);
        DoctorDto doctorDto = new DoctorDto(2L, "Kate", "Belmond", "general practicioner",
                null);
        //When
        DoctorDto mappedDoctorDto = doctorMapper.mapToDoctorDto(doctor);
        //Then
        Assert.assertEquals(doctorDto, mappedDoctorDto);
    }

    @Test
    public void shouldMapToDoctorDtoList() {
        //Given
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(1L, "Malcolm", "Johnson", "cardiologist", null));
        doctors.add(new Doctor(2L, "Kate", "Belmond", "general practicioner", null));

        List<DoctorDto> doctorDtos = new ArrayList<>();
        doctorDtos.add(new DoctorDto(1L, "Malcolm", "Johnson", "cardiologist", null));
        doctorDtos.add(new DoctorDto(2L, "Kate", "Belmond", "general practicioner", null));
        //When
        List<DoctorDto> mappedDoctorDtoList = doctorMapper.mapToDoctorDtoList(doctors);
        //Then
        Assert.assertEquals(doctorDtos, mappedDoctorDtoList);
    }
}
