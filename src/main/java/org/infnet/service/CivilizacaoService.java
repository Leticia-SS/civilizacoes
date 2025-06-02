package org.infnet.service;

import org.infnet.exception.CivilizacaoInvalidaException;
import org.infnet.modelo.Civilizacao;
import org.infnet.repository.CivilizacaoRepository;
import org.infnet.repository.CivilizacaoRepositoryInMemory;

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
        validarCivilizacao(civilizacao);
        repository.adicionar(civilizacao);
    }
    public boolean remover(int id){
        return this.repository.remover(id);
    }
    public boolean atualizar(int id, Civilizacao nova){
        return this.repository.atualizar(id,nova);
    }
    public List<Civilizacao> buscarPorLocalizacao(String termo){
        return this.repository.buscarPorLocalizacao(termo);
    }
    private void validarCivilizacao(Civilizacao civilizacao){
        if(civilizacao.getNome() == null || civilizacao.getNome().isBlank()){
            throw new CivilizacaoInvalidaException("Nome não pode ser vazio");
        }
        if(civilizacao.getLocalizacao() == null || civilizacao.getLocalizacao().isBlank()){
            throw new CivilizacaoInvalidaException("Localização não pode ser vazio");
        }

    }
    //Não utilizar!
    private boolean validarCivilizacaoBool(Civilizacao civilizacao) {
        if (civilizacao.getNome() == null || civilizacao.getNome().isBlank()) {
            return false;
        }
        if (civilizacao.getLocalizacao() == null || civilizacao.getLocalizacao().isBlank()) {
            return false;
        }
        return true;
    }
}




