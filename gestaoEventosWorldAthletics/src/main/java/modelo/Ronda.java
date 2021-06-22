package modelo;

import java.util.ArrayList;

public class Ronda {
    private Data hora;
    ArrayList<Atleta> listaInscritos;
    String nomeRonda;
    public Ronda(ArrayList<Atleta> listaInscritos, Data hora) {
        this.hora = hora;
        this.listaInscritos = listaInscritos;
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
