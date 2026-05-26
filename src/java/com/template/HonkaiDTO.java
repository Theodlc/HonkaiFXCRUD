package com.template;

public class HonkaiDTO {

    private int id;
    private String nome;
    private String elemento;
    private String efeito;
    private double raridade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public double getRaridade() {
        return raridade;
    }

    public void setRaridade(double raridade) {
        this.raridade = raridade;
    }
}