package com.example.faculty.model.entity;

import com.example.faculty.dao.Identified;

import java.io.Serializable;
import java.util.Objects;

public class Status implements Identified<Integer>, Serializable {

    private Integer id;
    private String name;

    public Status() {
    }

    public Status(String name) {
        this.name = name;
    }

    public Status(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status)) return false;
        Status status = (Status) o;
        return getId() == status.getId() && getName().equals(status.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
