package modelo;

import java.util.ArrayList;

public class Resultado {
    private int id;
    private String nome;
    private String pais;
    private int posicao;
    private double pontuacao;
    private boolean classificado;
    private String nomeRonda;
    private String nomeProva;

    public Resultado(int id, String nome, String pais, int posicao, double pontuacao, boolean classificado, String nomeRonda, String nomeProva){
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.posicao = posicao;
        this.pontuacao = pontuacao;
        this.classificado = classificado;
        this.nomeRonda = nomeRonda;
        this.nomeProva = nomeProva;
    }

    public String getNomeProva() {
        return nomeProva;
    }

    public String getNomeRonda() {
        return nomeRonda;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
    }

    public int getPosicao() {
        return posicao;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public boolean isClassificado() {
        return classificado;
    }
}
