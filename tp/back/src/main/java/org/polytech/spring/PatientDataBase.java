package org.polytech.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class PatientDataBase implements PatientStore{

    @Override
    public void savePatient(Patient p) {
        System.out.println("Sauvegarde en base de données: "+p.getname());
    }

    @PostConstruct
    public void init(){
        System.out.println("Initialisation de PatientDataBase");
    }
}