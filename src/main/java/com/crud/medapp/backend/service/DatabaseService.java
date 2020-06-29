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

    public List<Appointment> getAppointmentsByDoctor(Long doctor_id) {
        return appointmentRepository.findByDoctorId(doctor_id);
    }

    public List<Appointment> getAppointmentsByPatient(Long patient_id) {
        return appointmentRepository.findByPatientId(patient_id);
    }

    public Optional<Appointment> getAppointment(final Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveAppointment(final Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(final Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getDoctorsBySpecialization(final String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    public Optional<Doctor> getDoctor(final Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveDoctor(final Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(final Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getPatientsByDoctor(Doctor doctor) {
        return patientRepository.findByDoctor(doctor);
    }

    public Optional<Patient> getPatient(final Long id) {
        return patientRepository.findById(id);
    }

    public Patient savePatient(final Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(final Long id) {
        patientRepository.deleteById(id);
    }
}
