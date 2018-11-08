package com.example.souza.mutantes;

public class Mutante {
    private int id;
    private String nome;
    private String habilidade;

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHabilidade() {
        return this.habilidade;
    }
    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    @Override
    public String toString() {
        return nome;
    }
}
