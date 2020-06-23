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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private int patient_id;
    @Column
    private int doctor_id;
    @Column(name = "time")
    private LocalDateTime when;
    @Column(name = "place")
    private String where;
    @Column
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
