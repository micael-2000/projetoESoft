package modelo;

import java.util.ArrayList;

public class Ronda extends Prova {
    private Data hora;
    ArrayList<Atleta> listaInscritos;
    String nomeRonda;
    int numQualificados;
    Prova prova;
    int diaCompeticao;

    public Ronda(Prova prova, ArrayList<Atleta> listaInscritos, Data hora, int numQualificados, int diaCompeticao) {
        super(prova);
        this.hora = hora;
        this.listaInscritos = listaInscritos;
        this.numQualificados = numQualificados;
        this.prova = prova;
        this.diaCompeticao = diaCompeticao;
    }

    public Prova getProva() {
        return prova;
    }

    public int getDiaCompeticao() {
        return diaCompeticao;
    }

    public int getNumQualificados() {
        return numQualificados;
    }

    public Prova getProvaDaRonda(){
        return this.prova;
    }

    public Data getHora() {
        return hora;
    }

    public void setHora(Data hora) {
        this.hora = hora;
    }

    public ArrayList<Atleta> getListaInscritos() {
        return listaInscritos;
    }

    public int isAtletaNaRonda(int id, String nome){
        for (Atleta atl : listaInscritos){
            if (atl.getNumeroAtleta() == id && atl.getNome().equals(nome)){
                return 1;
            }
        }

        return 0;
    }


    public void setNomeRonda(String nome){
        //verificar numero de rondas, datas e horas para verificar. ex: uma ronda da mesma prova que seja mais tarde
        //que outra nao deve ser quartos final e a outra mais cedo ser final
        switch (nome){
            case "Quartos-Final" : this.nomeRonda = nome;
            break;
            case "Meia Final" : this.nomeRonda = nome;
            break;
            case "Final" : this.nomeRonda = nome;
            break;
            default: throw new IllegalStateException("nome de ronda inválido");
        }

    }

    public String getNomeRonda() {
        return this.nomeRonda;
    }

}
