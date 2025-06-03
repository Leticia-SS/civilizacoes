package org.infnet.dto;

public class UsuarioDTO {
    private int id;
    private String usuario;
    private String nome;

    public UsuarioDTO(int id,String usuario, String nome) {
        this.usuario = usuario;
        this.nome = nome;
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}
