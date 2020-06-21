package com.crud.medapp.backend.mapper;

import com.crud.medapp.backend.domain.Appointment;
import com.crud.medapp.backend.domain.AppointmentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {
    public Appointment mapToAppointment(final AppointmentDto appointmentDto) {
        return new Appointment(appointmentDto.getId(), appointmentDto.getPatient_id(), appointmentDto.getDoctor_id(),
                appointmentDto.getWhen().getYear(), appointmentDto.getWhen().getDayOfMonth(),
                appointmentDto.getWhen().getDayOfMonth(), appointmentDto.getWhen().getHour(),
                appointmentDto.getWhen().getMinute(), appointmentDto.getWhere(), appointmentDto.getRoom());
    }

    public AppointmentDto mapToAppointmentDto(final Appointment appointment) {
        return new AppointmentDto(appointment.getId(), appointment.getPatient_id(), appointment.getDoctor_id(),
                appointment.getWhen().getYear(), appointment.getWhen().getDayOfMonth(),
                appointment.getWhen().getDayOfMonth(), appointment.getWhen().getHour(),
                appointment.getWhen().getMinute(), appointment.getWhere(), appointment.getRoom());
    }

    public List<AppointmentDto> mapToAppointmentDtoList(final List<Appointment> appointmentList) {
        return appointmentList.stream()
                .map(a -> new AppointmentDto(a.getId(), a.getPatient_id(), a.getDoctor_id(), a.getWhen().getYear(),
                        a.getWhen().getDayOfMonth(), a.getWhen().getDayOfMonth(), a.getWhen().getHour(),
                        a.getWhen().getMinute(), a.getWhere(), a.getRoom())).collect(Collectors.toList());
    }
}
