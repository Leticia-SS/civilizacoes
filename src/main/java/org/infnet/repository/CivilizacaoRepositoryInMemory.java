package org.infnet.repository;

import org.infnet.modelo.Civilizacao;

import java.util.ArrayList;
import java.util.List;

public class CivilizacaoRepositoryInMemory implements CivilizacaoRepository  {
    private List<Civilizacao> lista = init();
    private List<Civilizacao> init(){
        List<Civilizacao> objects = new ArrayList<>();
        Civilizacao egito = new Civilizacao();
        egito.id = 1;

        egito.nome = "Egito Antigo";
        egito.localizacao = "Norte da África";
        egito.periodoHistorico = "c. 3100 a.C. – 30 a.C.";
        egito.legado = "Hieróglifos, pirâmides, astronomia";

        Civilizacao maia = new Civilizacao();
        maia.id = 2;
        maia.nome = "Maias";
        maia.localizacao = "Mesoamérica";
        maia.periodoHistorico = "c. 2000 a.C. – 1500 d.C.";
        maia.legado = "Calendário Maia, arquitetura avançada";

        objects.add(egito);
        objects.add(maia);
        return objects;
    }
    public List<Civilizacao> listarTodas(){
        return lista;
    }

    @Override
    public boolean remover(int id) {
        Civilizacao existente = buscarPorId(id);
        if(existente != null){
            return lista.remove(existente);
        }else {
            return false;
        }
    }

    @Override
    public boolean atualizar(int id, Civilizacao nova) {
        Civilizacao antiga = buscarPorId(id);
        if(antiga != null){
            antiga.setLegado(nova.getLegado());
            antiga.setLocalizacao(nova.getLocalizacao());
            antiga.setPeriodoHistorico(nova.getPeriodoHistorico());
            antiga.setNome(nova.getNome());
            return true;
        }

        return false;
    }

    @Override
    public List<Civilizacao> buscarPorLocalizacao(String termo) {
        return lista.stream()
                .filter(civilizacao -> civilizacao.getLocalizacao().toLowerCase().contains(termo.toLowerCase()))
                .toList();
    }

    public Civilizacao buscarPorId(int id){
        return lista.stream().filter(c -> c.id == id)
                .findFirst().orElse(null);
    }
    public void adicionar(Civilizacao civilizacao){
        civilizacao.id = lista.size() + 1;
        lista.add(civilizacao);
    }

}
