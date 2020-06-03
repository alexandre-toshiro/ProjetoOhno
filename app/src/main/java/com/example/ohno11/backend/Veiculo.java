package com.example.ohno11.backend;

import java.io.Serializable;

public class Veiculo implements Serializable {
    private int id;
    private String modelo;
    private int ano;
    private String placa;
    private String marca;
    private String InformacoesAdicionais;
    private String foreignKeyCliente;
    private int notaservico;

    public Veiculo() {
    }

    public Veiculo(int id, String modelo, int ano, String placa, String marca, String informacoesAdicionais, String foreignKeyCliente, int notaservico) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.marca = marca;
        InformacoesAdicionais = informacoesAdicionais;
        this.foreignKeyCliente = foreignKeyCliente;
        this.notaservico = notaservico;
    }

    public Veiculo(String modelo, int ano, String placa, String marca, String informacoesAdicionais, String foreignKeyCliente, int notaservico) {
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.marca = marca;
        InformacoesAdicionais = informacoesAdicionais;
        this.foreignKeyCliente = foreignKeyCliente;
        this.notaservico = notaservico;
    }

    public Veiculo(int ano, String placa, String marca, String modelo) {
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.marca = marca;
    }

    public Veiculo(String modelo, int ano, String placa, String marca, String informacoesAdicionais) {
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.marca = marca;
        InformacoesAdicionais = informacoesAdicionais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNotaservico() {
        return notaservico;
    }

    public void setNotaservico(int notaservico) {
        this.notaservico = notaservico;
    }

    public String getForeignKeyCliente() {
        return foreignKeyCliente;
    }

    public void setForeignKeyCliente(String foreignKeyCliente) {
        this.foreignKeyCliente = foreignKeyCliente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getInformacoesAdicionais() {
        return InformacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        InformacoesAdicionais = informacoesAdicionais;
    }

}
