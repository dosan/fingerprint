package com.example.match.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccessHour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer comeHour;
    private Integer comeHourEnd;
    private Integer leaveHour;
    private Integer leaveHourEnd;
    private boolean access;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComeHour() {
        return comeHour;
    }

    public void setComeHour(Integer comeHour) {
        this.comeHour = comeHour;
    }

    public Integer getComeHourEnd() {
        return comeHourEnd;
    }

    public void setComeHourEnd(Integer comeHourEnd) {
        this.comeHourEnd = comeHourEnd;
    }

    public Integer getLeaveHour() {
        return leaveHour;
    }

    public void setLeaveHour(Integer leaveHour) {
        this.leaveHour = leaveHour;
    }

    public Integer getLeaveHourEnd() {
        return leaveHourEnd;
    }

    public void setLeaveHourEnd(Integer leaveHourEnd) {
        this.leaveHourEnd = leaveHourEnd;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
