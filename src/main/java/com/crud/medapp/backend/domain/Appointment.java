package com.crud.medapp.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "appointments")
public class Appointment {
    @Id
    private Long id;
    @Column(name = "patient_id")
    private int patient_id;
    @Column(name = "doctor_id")
    private int doctor_id;
    @Column(name = "time")
    private LocalDateTime when;
    @Column(name = "place")
    private String where;
    @Column(name = "room")
    private int room;

    public Appointment(Long id, int patient_id, int doctor_id, int year, int month, int day, int hour, int minute,
                          String place, int room) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.when = LocalDateTime.of(year, month, day, hour, minute);
        this.where = place;
        this.room = room;
    }
}
