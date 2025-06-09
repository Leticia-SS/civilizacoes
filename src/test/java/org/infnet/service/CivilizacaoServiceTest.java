package org.infnet.service;

import org.infnet.exception.CivilizacaoInvalidaException;
import org.infnet.modelo.Civilizacao;
import org.infnet.repository.CivilizacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CivilizacaoServiceTest {
    private CivilizacaoRepository repositoryMock;
    private CivilizacaoService service;
    @BeforeEach
    void setUp(){
        repositoryMock = Mockito.mock(CivilizacaoRepository.class);
        service= new CivilizacaoService(repositoryMock);
    }
    @Test
    void deveLancarExceptionAoAdicionarCivilizacao(){
        Civilizacao civilizacao = new Civilizacao();
        assertThrows(CivilizacaoInvalidaException.class,() ->{
            service.criar(civilizacao);
        });
        verify(repositoryMock, never()).adicionar(any());
    }
}
