package com.example.match.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccessDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private String day;
    private Integer dayInteger;
    private boolean access;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   /* public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }*/

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public Integer getDayInteger() {
        return dayInteger;
    }

    public void setDayInteger(Integer dayInteger) {
        this.dayInteger = dayInteger;
    }
}
