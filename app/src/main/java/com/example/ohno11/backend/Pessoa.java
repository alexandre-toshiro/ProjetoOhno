package com.example.ohno11.backend;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private String nome;
    private String cpf;
    private int foreignKeyEndereco;
    private String telefone;
    private String email;

    public Pessoa(String nome, String cpf, int foreignKeyEndereco, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.foreignKeyEndereco = foreignKeyEndereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getForeignKeyEndereco() {
        return foreignKeyEndereco;
    }

    public void setForeignKeyEndereco(int foreignKeyEndereco) {
        this.foreignKeyEndereco = foreignKeyEndereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validarCpf(String cpf){
        return false;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + foreignKeyEndereco +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
