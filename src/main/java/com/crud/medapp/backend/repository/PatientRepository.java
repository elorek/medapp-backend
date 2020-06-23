package com.crud.medapp.backend.repository;

import com.crud.medapp.backend.domain.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    @Override
    List<Patient> findAll();

    @Override
    Optional<Patient> findById(Long id);

    @Override
    Patient save(Patient patient);

    @Override
    void deleteById(Long id);
}
