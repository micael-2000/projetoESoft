package modelo;

import java.util.ArrayList;

public class ProvaDadosPreDefinidos {
    private int id;
    private String nome;
    private String categoria;
    private String local;
    private String tipoProva;
    private String genero;
    private String notas;
    private String recordesMundialAtual;
    private String recordistas;
    private String eventosEmQueDecorreu;

    public ProvaDadosPreDefinidos(String nome, String categoria, String local, String tipoProva, String genero, String notas) {
        this.id = DadosAplicacao.INSTANCE.getSizeProvasPreDefinidos() + 1;
        this.nome = nome;
        this.categoria = categoria;
        this.local = local;
        this.tipoProva = tipoProva;
        this.genero = genero;
        this.notas = notas;
        recordesMundialAtual = null;
        recordistas = null;
        eventosEmQueDecorreu = null;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoProva() {
        return tipoProva;
    }

    public void setTipoProva(String tipoProva) {
        this.tipoProva = tipoProva;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getRecordesMundialAtual() {
        return recordesMundialAtual;
    }

    public void setRecordesMundialAtual(String recordesMundialAtual) {
        this.recordesMundialAtual = recordesMundialAtual;
    }

    public String getRecordistas() {
        return recordistas;
    }

    public void setRecordistas(String recordistas) {
        this.recordistas = recordistas;
    }

    public String getEventosEmQueDecorreu() {
        return eventosEmQueDecorreu;
    }

    public void setEventosEmQueDecorreu(String eventosEmQueDecorreu) {
        this.eventosEmQueDecorreu = eventosEmQueDecorreu;
    }

    @Override
    public String toString() {
        return "ProvaDadosPreDefinidos{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
