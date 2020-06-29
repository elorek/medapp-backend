package com.crud.medapp.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Getter
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
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "appointments")
    private List<Doctor> doctors = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "appointments")
    private List<Patient> patients = new ArrayList<>();

    public Appointment() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }



    public static class AppointmentBuilder {
        private Long id;
        private int patient_id;
        private int doctor_id;
        private LocalDateTime when;
        private String where;
        private int room;

        public AppointmentBuilder Id(Long id) {
            this.id = id;
            return this;
        }

        public AppointmentBuilder Patient_id(int patient_id) {
            this.patient_id = patient_id;
            return this;
        }

        public AppointmentBuilder Doctor_id(int doctor_id) {
            this.doctor_id = doctor_id;
            return this;
        }

        public AppointmentBuilder When(int year, int month, int day, int hour, int minute) {
            this.when = LocalDateTime.of(year, month, day, hour, minute);
            return this;
        }

        public AppointmentBuilder Where(String where) {
            this.where = where;
            return this;
        }

        public AppointmentBuilder Room(int room) {
            this.room = room;
            return this;
        }

        public Appointment build() {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null");
            }
            return new Appointment(id, patient_id, doctor_id, when.getYear(), when.getMonthValue(), when.getDayOfMonth(),
                    when.getHour(), when.getMinute(), where, room);
        }
    }

    private Appointment(Long id, int patient_id, int doctor_id, int year, int month, int day, int hour,
                              int minute, String where, int room) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.when = LocalDateTime.of(year, month, day, hour, minute);
        this.where = where;
        this.room = room;
    }
}
