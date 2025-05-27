package org.infnet.service;

import org.infnet.modelo.Civilizacao;
import org.infnet.repository.CivilizacaoRepository;

import java.util.List;

public class CivilizacaoService {
    private CivilizacaoRepository repository;

    public CivilizacaoService(CivilizacaoRepository repository) {
        this.repository = repository;
    }
    public List<Civilizacao> buscarTodas(){
       return repository.listarTodas();
    }
    public Civilizacao buscarPorId(int id){
        return repository.buscarPorId(id);
    }
    public void criar(Civilizacao civilizacao) {
        repository.adicionar(civilizacao);
    }
}
