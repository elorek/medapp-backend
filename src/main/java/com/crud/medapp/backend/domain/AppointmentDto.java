package com.crud.medapp.backend.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AppointmentDto {
    private Long id;
    private int patient_id;
    private int doctor_id;
    private LocalDateTime when;
    private String where;
    private int room;

    public AppointmentDto(Long id, int patient_id, int doctor_id, int year, int month, int day, int hour, int minute,
                          String place, int room) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.when = LocalDateTime.of(year, month, day, hour, minute);
        this.where = place;
        this.room = room;
    }
}
