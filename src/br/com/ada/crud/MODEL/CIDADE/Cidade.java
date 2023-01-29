package br.com.ada.crud.MODEL.CIDADE;

import java.util.UUID;

public class Cidade {


    private UUID id;
    private String nome;
    private String estado;


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
