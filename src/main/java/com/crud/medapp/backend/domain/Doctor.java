package com.crud.medapp.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String last_name;
    @Column
    private String specialization;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_DOCTOR_APPOINTMENT",
            joinColumns = {@JoinColumn(name = "doctor_table", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "appointment_table", referencedColumnName = "doctor_id")}
    )
    private List<Appointment> appointments = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
