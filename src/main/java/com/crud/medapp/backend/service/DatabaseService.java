package com.crud.medapp.backend.service;

import com.crud.medapp.backend.domain.Appointment;
import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.Patient;
import com.crud.medapp.backend.repository.AppointmentRepository;
import com.crud.medapp.backend.repository.DoctorRepository;
import com.crud.medapp.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Appointment> getAppointment(final Long id) {
        return appointmentRepository.findById(id);
    }

    public Optional<Doctor> getDoctor(final Long id) {
        return doctorRepository.findById(id);
    }

    public Optional<Patient> getPatient(final Long id) {
        return patientRepository.findById(id);
    }

    public Appointment saveAppointment(final Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Doctor saveDoctor(final Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Patient savePatient(final Patient patient) {
        return patientRepository.save(patient);
    }

    public void deleteAppointment(final Long id) {
        appointmentRepository.deleteById(id);
    }

    public void deleteDoctor(final Long id) {
        doctorRepository.deleteById(id);
    }

    public void deletePatient(final Long id) {
        patientRepository.deleteById(id);
    }
}
