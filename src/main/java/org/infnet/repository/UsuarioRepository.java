package org.infnet.repository;

import org.infnet.modelo.Usuario;

import java.util.List;

public interface UsuarioRepository {
    List<Usuario> listarTodos();
    Usuario buscarPorLoginESenha(String login,String senha);
    Usuario buscarPorLogin(String login);

}
