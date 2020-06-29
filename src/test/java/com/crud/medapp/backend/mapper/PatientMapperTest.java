package com.crud.medapp.backend.mapper;

import com.crud.medapp.backend.domain.Patient;
import com.crud.medapp.backend.domain.PatientDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientMapperTest {
    @Autowired
    PatientMapper patientMapper;

    @Test
    public void shouldMapToPatient() {
        //Given
        PatientDto patientDto = new PatientDto(1L, "Tom", "Johnson", "89071626309",
                "Testowa 1 Testowo", null);
        Patient patient = new Patient(1L, "Tom", "Johnson", "89071626309", "Testowa 1 Testowo",
                null);
        //When
        Patient mappedPatient = patientMapper.mapToPatient(patientDto);
        //Then
        Assert.assertEquals(patient, mappedPatient);
    }

    @Test
    public void shouldMapToPatientDto() {
        //Given
        Patient patient = new Patient(2L, "Arnold", "Watson", "67012609524", "Testowa 5 Testowo",
                null);
        PatientDto patientDto = new PatientDto(2L, "Arnold", "Watson", "67012609524",
                "Testowa 5 Testowo", null);
        //When
        PatientDto mappedPatientDto = patientMapper.mapToPatientDto(patient);
        //Then
        Assert.assertEquals(patientDto, mappedPatientDto);
    }

    @Test
    public void shouldMapToPatientDtoList() {
        //Given
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient(1L, "Monica", "Anders", "98052701762", "Testowa 6 Testowo",
                null));
        patientList.add(new Patient(2L, "Arnold", "Watson", "67012609524", "Testowa 5 Testowo",
                null));
        patientList.add(new Patient(3L, "Claudia", "Marcelli", "67091704798",
                "Gruszkowa 1 Gruszkowo", null));

        List<PatientDto> patientDtos = new ArrayList<>();
        patientDtos.add(new PatientDto(1L, "Monica", "Anders", "98052701762", "Testowa 6 Testowo",
                null));
        patientDtos.add(new PatientDto(2L, "Arnold", "Watson", "67012609524", "Testowa 5 Testowo",
                null));
        patientDtos.add(new PatientDto(3L, "Claudia", "Marcelli", "67091704798",
                "Gruszkowa 1 Gruszkowo", null));
        //When
        List<PatientDto> mappedPatientDtoList = patientMapper.mapToPatientDtoList(patientList);
        //Then
        Assert.assertEquals(patientDtos, mappedPatientDtoList);
    }
}
