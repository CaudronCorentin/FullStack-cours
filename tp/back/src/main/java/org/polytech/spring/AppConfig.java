package org.polytech.spring;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.polytech.spring")
@PropertySource("classpath:application.properties")
public class AppConfig {

}