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
@Entity(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String last_name;
    @Column
    private String pesel;
    @Column
    private String adress;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PATIENT_APPOINTMENT",
            joinColumns = {@JoinColumn(name = "patient_table", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "appointment_table", referencedColumnName = "patient_id")}
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

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
