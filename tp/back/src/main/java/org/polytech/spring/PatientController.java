package org.polytech.spring;

import org.osgi.annotation.bundle.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @GetMapping
    public String test() {
        return "Test";
    }

    @GetMapping("/objet")
    public Patient patient() {
        return new Patient(1,"Corentin");
    }


}
