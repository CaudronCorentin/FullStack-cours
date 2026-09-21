package org.polytech.spring;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class PatientSerialization implements PatientStore{
    @Override
    public void savePatient(Patient patient) {
        System.out.println("Sauvegarde du patient dans un fichier : " + patient.getname());
    }
}
