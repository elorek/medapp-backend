package com.crud.medapp.backend.repository;

import com.crud.medapp.backend.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    @Override
    List<Appointment> findAll();

    @Override
    Optional<Appointment> findById(Long id);

    @Override
    Appointment save(Appointment appointment);

    @Override
    void deleteById(Long id);
}
