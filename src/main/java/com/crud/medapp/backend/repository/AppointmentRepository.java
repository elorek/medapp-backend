package com.crud.medapp.backend.repository;

import com.crud.medapp.backend.domain.Appointment;
import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    @Override
    List<Appointment> findAll();

    List<Appointment> findByDoctorId(Long doctor_id);

    List<Appointment> findByPatientId(Long patient_id);

    @Override
    Optional<Appointment> findById(Long id);

    Appointment save(Appointment appointment);

    @Override
    void deleteById(Long id);
}
