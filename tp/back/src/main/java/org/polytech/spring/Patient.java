package org.polytech.spring;

public class Patient {

    public final int id;
    public final String name;

    public Patient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getname() {
        return name;
    }
}
