package com.galvanize.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class Person {

    private String name;
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date dateRegistered;
    private String email;
    private String address;


    public Person() {
    }

    public Person(String name, Date dateRegistered, String email, String address) {
        this.name = name;
        this.dateRegistered = dateRegistered;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getAge() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.dateRegistered);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        LocalDate ll = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(ll, now);
        return diff.getYears();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dateRegistered=" + dateRegistered +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", age=" + getAge() +
                '}';
    }

}

