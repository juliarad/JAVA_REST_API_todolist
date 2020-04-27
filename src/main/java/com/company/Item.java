package com.company;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="item")
public class Item implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="status")
    @NotNull
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
