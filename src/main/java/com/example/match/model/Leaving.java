package com.example.match.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Leaving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date hasLeave;

    private String status;


    @Column(nullable = true)
    private Integer organizationId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Owner owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return hasLeave;
    }

    public void setTimestamp(Date timestamp) {
        this.hasLeave = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
