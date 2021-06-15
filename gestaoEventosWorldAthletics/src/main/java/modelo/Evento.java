package modelo;

import java.util.ArrayList;

public class Evento {
    private ArrayList<Prova> listaProvas;
    private String nomeEvento;
    private Data dataInicio;
    private Data dataFim;
    private String local;
    private String pais;

    public Evento(ArrayList<Prova> listaProvas, Data dataInicio, Data dataFim, String local, String pais, String nomeEvento) {
        this.listaProvas = listaProvas;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.pais = pais;
        this.nomeEvento = nomeEvento;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public String getLocal() {
        return local;
    }

    public String getPais() {
        return pais;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }
}
