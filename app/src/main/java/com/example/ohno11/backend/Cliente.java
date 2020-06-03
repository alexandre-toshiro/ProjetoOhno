package com.example.ohno11.backend;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {
    private int foreignKeyServicos;
    private int foreignKeyveiculo;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, int foreignKeyEndereco, String telefone, String email, int foreignKeyServicos, int foreignKeyveiculo) {
        super(nome, cpf, foreignKeyEndereco, telefone, email);
        this.foreignKeyServicos = foreignKeyServicos;
        this.foreignKeyveiculo = foreignKeyveiculo;
    }

    public Cliente(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
    }

    public int getForeignKeyveiculo() {
        return foreignKeyveiculo;
    }

    public void setForeignKeyveiculo(int foreignKeyveiculo) {
        this.foreignKeyveiculo = foreignKeyveiculo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "servico=" + foreignKeyServicos +
                ", veiculo=" + foreignKeyveiculo +
                '}';
    }
}
