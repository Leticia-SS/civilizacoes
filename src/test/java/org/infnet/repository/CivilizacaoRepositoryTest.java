package org.infnet.repository;

import org.infnet.modelo.Civilizacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CivilizacaoRepositoryTest {
    CivilizacaoRepository repository;
    @BeforeEach
    public void setUp(){
        repository = new CivilizacaoRepositoryInMemory();
    }
    @Test
    void deveListarTodasAsCivilizacoes(){
        List<Civilizacao> civilizacaos =
                repository.listarTodas();
        assertFalse(civilizacaos.isEmpty());
    }
    @Test
    void deveBuscarPorIdExistente(){
        Civilizacao civilizacao = repository.buscarPorId(1);
        assertNotNull(civilizacao);
        assertEquals("Egito Antigo",civilizacao.getNome());

    }
    @Test
    void deveRetornarNullParaIdInexistente(){
        Civilizacao naoExistente = repository.buscarPorId(500);
        assertNull(naoExistente);
    }
    @Test
    void deveBuscarPorLocalizacao(){
        String termo = "américa";
        List<Civilizacao> filtradas = repository.buscarPorLocalizacao(termo);
        assertFalse(filtradas.isEmpty());
        boolean contains = filtradas.stream()
                .allMatch(c -> c.getLocalizacao().toLowerCase().contains(termo));
        assertTrue(contains);

    }
    @Test
    public void deveAdicionarNovaCivilizacao(){
        int tamanhoInicial = repository.listarTodas().size();
        Civilizacao civilizacao = new Civilizacao();
        civilizacao.setNome("Império Romano");
        civilizacao.setLocalizacao("Europa");
        civilizacao.setPeriodoHistorico("27 a.C - 476 d.C.");
        civilizacao.setLegado("Direito, Engenharia, Lingua latina");
        repository.adicionar(civilizacao);
        int tamanhoFinal = repository.listarTodas().size();
        assertEquals(tamanhoInicial + 1, tamanhoFinal);
    }
    @Test
    public void deveRemoverUmaCivilizacao(){
        int tamanhoInicial = repository.listarTodas().size();
        boolean foiRemovido = repository.remover(1);
        assertTrue(foiRemovido);
        int tamanhoFinal = repository.listarTodas().size();
        assertEquals(tamanhoInicial - 1,tamanhoFinal);
    }
}
