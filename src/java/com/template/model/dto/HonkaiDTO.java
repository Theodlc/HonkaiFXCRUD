package com.template.model.dto;

public class HonkaiDTO {

    private int id;
    private String nome;
    private String elemento;
    private String raridade;
    private String efeito;

    public HonkaiDTO() {
    }

    public HonkaiDTO(int id, String nome, String elemento, String raridade, String efeito) {
        this.id = id;
        this.nome = nome;
        this.elemento = elemento;
        this.raridade = raridade;
        this.efeito = efeito;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getElemento() { return elemento; }
    public void setElemento(String elemento) { this.elemento = elemento; }

    public String getRaridade() { return raridade; }
    public void setRaridade(String raridade) { this.raridade = raridade; }

    public String getEfeito() { return efeito; }
    public void setEfeito(String efeito) { this.efeito = efeito; }
}