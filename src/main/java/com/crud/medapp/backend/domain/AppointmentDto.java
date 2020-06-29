package com.crud.medapp.backend.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AppointmentDto {
    private Long id;
    private int patient_id;
    private int doctor_id;
    private LocalDateTime when;
    private String where;
    private int room;
    private List<Patient> patients = new ArrayList<>();

    public static class AppointmentDtoBuilder {
        private Long id;
        private int patient_id;
        private int doctor_id;
        private LocalDateTime when;
        private String where;
        private int room;
        private List<Patient> patients = new ArrayList<>();

        public AppointmentDtoBuilder Id(Long id) {
            this.id = id;
            return this;
        }

        public AppointmentDtoBuilder Patient_id(int patient_id) {
            this.patient_id = patient_id;
            return this;
        }

        public AppointmentDtoBuilder Doctor_id(int doctor_id) {
            this.doctor_id = doctor_id;
            return this;
        }

        public AppointmentDtoBuilder When(int year, int month, int day, int hour, int minute) {
            this.when = LocalDateTime.of(year, month, day, hour, minute);
            return this;
        }

        public AppointmentDtoBuilder Where(String where) {
            this.where = where;
            return this;
        }

        public AppointmentDtoBuilder Room(int room) {
            this.room = room;
            return this;
        }

        public AppointmentDtoBuilder Patient(Patient patient) {
            patients.add(patient);
            return this;
        }

        public AppointmentDto build() {
            return new AppointmentDto(id, patient_id, doctor_id, when.getYear(), when.getMonthValue(), when.getDayOfMonth(),
                    when.getHour(), when.getMinute(), where, room, patients);
        }
    }

    private AppointmentDto(Long id, int patient_id, int doctor_id, int year, int month, int day, int hour,
                        int minute, String where, int room, List<Patient> patients) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.when = LocalDateTime.of(year, month, day, hour, minute);
        this.where = where;
        this.room = room;
        this.patients = patients;
    }
}
