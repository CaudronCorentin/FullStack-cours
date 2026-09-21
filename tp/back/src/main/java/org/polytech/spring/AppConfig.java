package org.polytech.spring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public PatientStore patientStore(){
        return new PatientDataBase();
    }

    @Bean
    public PatientService patientService(PatientStore store){
        return new PatientService(store);
    }

    @Bean
    @Scope("prototype")
    public TestScope testScope(){
        return new TestScope();
    }
}