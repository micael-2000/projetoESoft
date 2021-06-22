package modelo;

import javafx.util.Pair;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();
    private HashMap<Integer, ProvaDadosPreDefinidos> listaProvasDadosPreDefinidos;
    private ArrayList<Evento> listaEventos;
    private ArrayList<Atleta> atletas;
    //private ArrayList<JTable> listaResultados;
    public DadosAplicacao() {
        //Provas pre definido
        listaProvasDadosPreDefinidos = new HashMap<>(30);
      //  listaResultados = new ArrayList<>();
        //Eventos
        listaEventos = new ArrayList<>();
        atletas = new ArrayList<>();
    }

    public boolean existsProva(){
        return true;
    }

    public void addEvento(Evento evento){
        listaEventos.add(evento);
    }

    //public void addResultado(JTable tabelaResultado){ listaResultados.add(tabelaResultado); }

    public void addProva(ProvaDadosPreDefinidos prova){
        listaProvasDadosPreDefinidos.put(prova.getId(), prova);
    }

    public void removeEvento(int posicao){
        listaEventos.remove(posicao);
    }

    //public void removeResultado(int posicao){
        //listaResultados.remove(posicao);
    //}

    public ArrayList<Evento> getListaEventos() {
        return listaEventos;
    }

    //public ArrayList<JTable> getListaResultados(){ return listaResultados; }

    public Evento getEvento(int index) {
        return listaEventos.get(index);
    }

    //public JTable getResultado(int index){ return listaResultados.get(index); }

    public int getSizeEventos(){
        return listaEventos.size();
    }

    public int getIndiceEvento(Evento evento){
        return listaEventos.indexOf(evento);
    }

    public int getIndiceResultado(JTable table){
        return listaEventos.indexOf(table);
    }

    public Collection<ProvaDadosPreDefinidos> getListaProvasDadosPreDefinidos() {
        return listaProvasDadosPreDefinidos.values();
    }

    public ProvaDadosPreDefinidos getProvaDadosPreDefinidos(Integer idProva) {
        return listaProvasDadosPreDefinidos.get(idProva);
    }

    public void removeProvaDadosPreDefinidos(int idProva){
        listaProvasDadosPreDefinidos.remove(idProva);
    }

    public int getSizeProvasPreDefinidos(){
        if (listaProvasDadosPreDefinidos.isEmpty()){
            return 0;
        }
        return listaProvasDadosPreDefinidos.size();
    }

    public ArrayList<Atleta> getListaAtletas() {
        return atletas;
    }

    public void addAtleta(Atleta atleta){
        atletas.add(atleta);
    }

}
