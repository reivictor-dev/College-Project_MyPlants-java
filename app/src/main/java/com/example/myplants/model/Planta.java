package com.example.myplants.model;

public class Planta {

    private long id;
    private String nome;
    private String especie;
    private int quantidade;
    private double altura;
    private boolean toxica;

    public Planta(long id, String nome, String especie, int quantidade, double altura, boolean toxica) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.quantidade = quantidade;
        this.altura = altura;
        this.toxica = toxica;
    }

    public Planta(String nome, String especie, int quantidade, double altura, boolean toxica) {
        this.nome = nome;
        this.especie = especie;
        this.quantidade = quantidade;
        this.altura = altura;
        this.toxica = toxica;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public boolean isToxica() { return toxica; }
    public void setToxica(boolean toxica) { this.toxica = toxica; }
}
