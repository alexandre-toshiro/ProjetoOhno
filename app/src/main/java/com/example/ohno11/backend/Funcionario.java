package com.example.ohno11.backend;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable {
    private int foreignKeyCargo;
    private double salario;

    public Funcionario(String nome, String cpf, int foreignKeyEndereco, String telefone, String email, int foreignKeyCargo, double salario) {
        super(nome, cpf, foreignKeyEndereco, telefone, email);
        this.foreignKeyCargo = foreignKeyCargo;
        this.salario = salario;
    }

    public Funcionario() {
    }

    public Funcionario(int foreignKeyCargo, double salario) {
        this.foreignKeyCargo = foreignKeyCargo;
        this.salario = salario;
    }

    public int getForeignKeyCargo() {
        return foreignKeyCargo;
    }

    public void setForeignKeyCargo(int foreignKeyCargo) {
        this.foreignKeyCargo = foreignKeyCargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cargo=" + foreignKeyCargo +
                ", salario=" + salario +
                '}';
    }
}
