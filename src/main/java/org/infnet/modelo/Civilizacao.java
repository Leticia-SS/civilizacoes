package org.infnet.modelo;

public class Civilizacao {
    public int id;
    public String nome;
    public String localizacao;
    public String periodoHistorico;
    public String legado;

    public Civilizacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getPeriodoHistorico() {
        return periodoHistorico;
    }

    public void setPeriodoHistorico(String periodoHistorico) {
        this.periodoHistorico = periodoHistorico;
    }

    public String getLegado() {
        return legado;
    }

    public void setLegado(String legado) {
        this.legado = legado;
    }
}
