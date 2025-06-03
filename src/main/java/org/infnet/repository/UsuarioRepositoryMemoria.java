package org.infnet.repository;

import org.infnet.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryMemoria implements UsuarioRepository{
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepositoryMemoria() {
        usuarios.add(new Usuario(1,"Joao", "joao", "123456"));
        usuarios.add(new Usuario(2,"Maria", "maria", "123456"));
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    @Override
    public Usuario buscarPorLoginESenha(String login, String senha) {
        return usuarios.stream()
                .filter(usuario -> credenciaisValidas(login, senha, usuario))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Usuario buscarPorLogin(String login) {
       return usuarios.stream()
               .filter(usuario -> usuario.getLogin().equals(login))
               .findFirst()
               .orElse(null);
    }
    private  boolean credenciaisValidas(String login, String senha, Usuario usuario) {
        return usuario.getLogin().equals(login) && usuario.getSenha().equals(senha);
    }
}
