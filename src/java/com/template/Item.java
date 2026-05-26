package com.template;

public class Item {
    private int id;
    private String nome;
    private String elemento;
    private String raridade;
    private String efeito;


    public Item(int id, String nome, String elemento, String raridade, String efeito) {
        this.id = id;
        this.nome = nome;
        this.elemento = elemento;
        this.raridade = raridade;
        this.efeito = efeito;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getElemento() { return elemento; }
    public String getRaridade() { return raridade; }
    public String getEfeito() { return efeito; }
}