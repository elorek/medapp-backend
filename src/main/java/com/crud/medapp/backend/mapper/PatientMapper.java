package com.crud.medapp.backend.mapper;

import com.crud.medapp.backend.domain.Patient;
import com.crud.medapp.backend.domain.PatientDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
    public Patient mapToPatient(final PatientDto patientDto) {
        return new Patient(patientDto.getId(), patientDto.getName(), patientDto.getLast_name(), patientDto.getPesel(),
                patientDto.getAdress());
    }

    public PatientDto mapToPatientDto(final Patient patient) {
        return new PatientDto(patient.getId(), patient.getName(), patient.getLast_name(), patient.getPesel(),
                patient.getAdress());
    }

    public List<PatientDto> mapToPatientDtoList(final List<Patient> patientList) {
        return patientList.stream().map(p -> new PatientDto(p.getId(), p.getName(), p.getLast_name(), p.getPesel(),
                p.getAdress())).collect(Collectors.toList());
    }
}
