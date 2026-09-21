package org.polytech.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

public class App {

    public static void main(String[] args) {
        try(var ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            PatientService service = ctx.getBean(PatientService.class);

            service.savePatient(new Patient(1, "Corentin"));

            TestScope scope1 = ctx.getBean(TestScope.class);
            System.out.println("Scope1 : " + ObjectUtils.identityToString(scope1));

            TestScope scope2 = ctx.getBean(TestScope.class);
            System.out.println("Scope2 : " + ObjectUtils.identityToString(scope2));

            PatientService service1 = ctx.getBean(PatientService.class);
            PatientService service2 = ctx.getBean(PatientService.class);

            System.out.println("service1 : " + ObjectUtils.identityToString(service1));
            System.out.println("service2 : " + ObjectUtils.identityToString(service2));

        }
    }
}