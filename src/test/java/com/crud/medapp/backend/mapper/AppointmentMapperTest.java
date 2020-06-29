package com.crud.medapp.backend.mapper;

import com.crud.medapp.backend.domain.Appointment;
import com.crud.medapp.backend.domain.AppointmentDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentMapperTest {
    @Autowired
    AppointmentMapper appointmentMapper;

    @Test
    public void shouldMapToAppointment() {
        //Given
        AppointmentDto appointmentDto = new AppointmentDto.AppointmentDtoBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 06, 14, 11, 30).Where("Testowa 18 Testowo").Room(7).build();

        Appointment appointment = new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(1)
                .When(2020, 06, 14, 11, 30).Where("Testowa 18 Testowo").Room(7).build();
        //When
        Appointment mappedAppointment = appointmentMapper.mapToAppointment(appointmentDto);
        //Then
        Assert.assertEquals(appointment, mappedAppointment);
    }

    @Test
    public void shouldMapToAppointmentDto() {
        //Given
        Appointment appointment = new Appointment.AppointmentBuilder().Id(2L).Patient_id(2).Doctor_id(2)
                .When(2020, 11, 05, 13, 45).Where("Testowa 18 Testowo").Room(11).build();

        AppointmentDto appointmentDto = new AppointmentDto.AppointmentDtoBuilder().Id(2L).Patient_id(2).Doctor_id(2)
                .When(2020, 11, 05, 13, 45).Where("Testowa 18 Testowo").Room(11).build();
        //When
        AppointmentDto mappedAppointmentDto = appointmentMapper.mapToAppointmentDto(appointment);
        //Then
        Assert.assertEquals(appointmentDto, mappedAppointmentDto);
    }

    @Test
    public void shouldMapToAppointmentDtoList() {
        //Given
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(2)
        .When(2020, 10, 15, 11, 15).Where("Zagadkowa 18 Zagadkowo").Room(5).build());
        appointments.add(new Appointment.AppointmentBuilder().Id(2L).Patient_id(4).Doctor_id(5)
        .When(2020, 10, 12, 10, 45).Where("Testowa 10 Testowo").Room(7).build());
        appointments.add(new Appointment.AppointmentBuilder().Id(3L).Patient_id(5).Doctor_id(10)
        .When(2020, 07, 17, 10, 45).Where("Testowa 5 Testowo").Room(12).build());

        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(1L).Patient_id(1).Doctor_id(2)
        .When(2020, 10, 15, 11, 15).Where("Zagadkowa 18 Zagadkowo").Room(5).build());
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(2L).Patient_id(4).Doctor_id(5)
        .When(2020, 10, 12, 10, 45).Where("Testowa 10 Testowo").Room(7).build());
        appointmentDtos.add(new AppointmentDto.AppointmentDtoBuilder().Id(3L).Patient_id(5).Doctor_id(10)
        .When(2020, 07, 17, 10, 45).Where("Testowa 5 Testowo").Room(12).build());
        //When
        List<AppointmentDto> mappedAppointmentDtoList = appointmentMapper.mapToAppointmentDtoList(appointments);
        //Then
        Assert.assertEquals(appointmentDtos, mappedAppointmentDtoList);
    }
}
