package com.crud.medapp.backend.manytomany;

import com.crud.medapp.backend.domain.Appointment;
import com.crud.medapp.backend.domain.Doctor;
import com.crud.medapp.backend.domain.Patient;
import com.crud.medapp.backend.repository.AppointmentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveManyToManyTest {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Test
    public void testSaveManyToMany() {
        //Given
        Doctor doctor1 = new Doctor(1L, "Tom", "Johnson", "cardiologist", null);
        Doctor doctor2 = new Doctor(2L, "Alice", "Watson", "neurologist", null);

        Patient patient1 = new Patient(1L, "Ben", "Green", "87071509812",
                "Grzybowa 1 Grzybowo", null);
        Patient patient2 = new Patient(2L, "Barbara", "Maddison", "98012365417",
                "Grzybowa 5 Grzybowo", null);

        Appointment appointment1 = new Appointment.AppointmentBuilder().Id(1L).Patient_id(1).Doctor_id(2)
                .When(2020, 07, 18, 15, 15).Where("Jablkowa 1, Jablkowo").Room(5).build();

        Appointment appointment2 = new Appointment.AppointmentBuilder().Id(2L).Patient_id(2).Doctor_id(1)
                .When(2020, 07, 01, 11, 45).Where("Jablkowa 221 Jablkowo").Room(7).build();

        doctor1.getAppointments().add(appointment2);
        doctor2.getAppointments().add(appointment1);

        patient1.getAppointments().add(appointment1);
        patient2.getAppointments().add(appointment2);

        appointment1.getPatients().add(patient1);
        appointment1.getDoctors().add(doctor2);
        appointment2.getPatients().add(patient2);
        appointment2.getDoctors().add(doctor1);

        //When
        appointmentRepository.save(appointment1);
        Long appointment1Id = appointment1.getId();
        appointmentRepository.save(appointment2);
        Long appointment2Id = appointment1.getId();

        //Then
        Assert.assertEquals(java.util.Optional.of(0), appointment1Id);
        Assert.assertEquals(java.util.Optional.of(0),  appointment2Id);

        //CleanUp
//        try {
//            appointmentRepository.deleteById(appointment1Id);
//            appointmentRepository.deleteById(appointment2Id);
//        } catch (Exception e) {
//        }
    }
}
