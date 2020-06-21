package com.crud.medapp.backend.repository;

import com.crud.medapp.backend.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    @Override
    List<Appointment> findAll();
}
