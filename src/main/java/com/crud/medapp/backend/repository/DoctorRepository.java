package com.crud.medapp.backend.repository;

import com.crud.medapp.backend.domain.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Override
    List<Doctor> findAll();

    @Override
    Optional<Doctor> findById(Long id);

    List<Doctor> findBySpecialization(String specialization);

    @Override
    Doctor save(Doctor doctor);

    @Override
    void deleteById(Long id);
}
