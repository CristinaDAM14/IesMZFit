package es.iesmz.dam.pro;

import java.sql.Date;
import java.time.LocalDate;

public class Monitor {
    private int id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String gender;

    public Monitor(String name, String lastName, LocalDate birthDate, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Monitor(int id, String name, String lastName, LocalDate birthDate, String gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return Date.valueOf(birthDate);
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
