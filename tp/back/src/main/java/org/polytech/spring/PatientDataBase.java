package org.polytech.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class PatientDataBase implements PatientStore{

    @Value("${database.url}")
    private String urlDb;

    @Override
    public void savePatient(Patient p) {
        System.out.println("Sauvegarde en base de données: "+p.getname());
    }

    @PostConstruct
    public void init(){
        System.out.println("Initialisation de PatientDataBase " + urlDb);
    }
}