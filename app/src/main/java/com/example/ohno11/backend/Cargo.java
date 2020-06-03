package com.example.ohno11.backend;

import java.io.Serializable;

public class Cargo implements Serializable {
    private int id;
    private String Cargo;

    public Cargo() {
    }

    public Cargo(int id, String cargo) {
        this.id = id;
        Cargo = cargo;
    }

    public Cargo(String cargo) {
        Cargo = cargo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", Cargo='" + Cargo + '\'' +
                '}';
    }
}
