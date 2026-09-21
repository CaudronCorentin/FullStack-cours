package org.polytech.spring;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private PatientStore store;
    public PatientService(PatientStore patientStore){
        this.store = store;
    }
    void savePatient(Patient patient){

    }
}
