package org.infnet.repository;

import org.infnet.modelo.Civilizacao;

import java.util.List;

public interface CivilizacaoRepository {
    void adicionar(Civilizacao civilizacao);
    Civilizacao buscarPorId(int id);
    List<Civilizacao> listarTodas();
    boolean remover(int id);
    boolean atualizar(int id , Civilizacao nova);
    List<Civilizacao> buscarPorLocalizacao(String termo);
}
