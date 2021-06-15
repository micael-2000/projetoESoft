package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();
    public HashMap<String, ProvaDadosPreDefinidos> listaProvasDadosPreDefinidos;
    private ArrayList<Evento> listaEventos;

    public DadosAplicacao() {
        listaProvasDadosPreDefinidos = new HashMap<>(30);
        listaProvasDadosPreDefinidos.put("Prova1", new ProvaDadosPreDefinidos("Prova1"));
        listaProvasDadosPreDefinidos.put("Prova2", new ProvaDadosPreDefinidos("Prova2"));

        listaEventos = new ArrayList<>();
    }

    public boolean existsProva(){
        return true;
    }

    public void addEvento(Evento evento){
        listaEventos.add(evento);
    }

    public ArrayList<Evento> getListaEventos() {
        return listaEventos;
    }

    public int getSizeEventos(){
        return listaEventos.size();
    }
}
