package com.crud.medapp.backend.mapper;

import com.crud.medapp.backend.domain.Appointment;
import com.crud.medapp.backend.domain.AppointmentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {
    public Appointment mapToAppointment(final AppointmentDto appointmentDto) {
        return new Appointment.AppointmentBuilder().Id(null).Patient_id(0).Doctor_id(0).When(0, 0, 0, 0, 0)
                .Where(null).Room(0).build();
    }

    public AppointmentDto mapToAppointmentDto(final Appointment appointment) {
        return new AppointmentDto.AppointmentDtoBuilder().build();
    }

    public List<AppointmentDto> mapToAppointmentDtoList(final List<Appointment> appointmentList) {
        return appointmentList.stream()
                .map(a -> new AppointmentDto.AppointmentDtoBuilder().build()).collect(Collectors.toList());
    }
}
