package com.example.ohno11.backend;


import java.io.Serializable;
import java.util.Date;

public class Servico implements Serializable {
    private String descricao;
    private double tempoDuracao;
    private double valor;
    private String placa;

    public Servico() {
    }

    public Servico(String descricao, double tempoDuracao, double valor, String placa) {
        this.descricao = descricao;
        this.tempoDuracao = tempoDuracao;
        this.valor = valor;
        this.placa = placa;
    }

    public Servico(double tempoDuracao, double valor, String descricao) {
        this.descricao = descricao;
        this.tempoDuracao = tempoDuracao;
        this.valor = valor;
        this.placa = placa;
    }

    public Servico(String descricao, double tempoDuracao, double valor) {
        this.descricao = descricao;
        this.tempoDuracao = tempoDuracao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(double tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
