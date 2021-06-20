package modelo;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();
    private HashMap<Integer, ProvaDadosPreDefinidos> listaProvasDadosPreDefinidos;
    private ArrayList<Evento> listaEventos;

    public DadosAplicacao() {
        //Provas pre definido
        listaProvasDadosPreDefinidos = new HashMap<>(30);

        //Eventos
        listaEventos = new ArrayList<>();
    }

    public boolean existsProva(){
        return true;
    }

    public void addEvento(Evento evento){
        listaEventos.add(evento);
    }

    public void addProva(ProvaDadosPreDefinidos prova){
        listaProvasDadosPreDefinidos.put(prova.getId(), prova);
    }

    public void removeEvento(int posicao){
        listaEventos.remove(posicao);
    }

    public ArrayList<Evento> getListaEventos() {
        return listaEventos;
    }

    public Evento getEvento(int index) {
        return listaEventos.get(index);
    }

    public int getSizeEventos(){
        return listaEventos.size();
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

}
