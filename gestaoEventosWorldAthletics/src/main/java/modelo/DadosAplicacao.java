package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();
    public HashMap<String, ProvaDadosPreDefinidos> listaProvasDadosPreDefinidos;
    private ArrayList<Evento> listaEventos;

    public DadosAplicacao() {
        //Provas pre definido
        listaProvasDadosPreDefinidos = new HashMap<>(30);
        listaProvasDadosPreDefinidos.put("Prova1", new ProvaDadosPreDefinidos("Prova1", "Corrida", "Pista exterior", "Eliminatórias", "Feminino", " "));
        listaProvasDadosPreDefinidos.put("Prova2", new ProvaDadosPreDefinidos("Prova2", "Corrida", "Pista exterior", "Eliminatórias", "Feminino", " "));

        //Eventos
        listaEventos = new ArrayList<>();
        listaEventos.add(new Evento(null, new Data(), new Data(), "Lisboa", "Portugal", "Evento teste"));
        listaEventos.add(new Evento(null, new Data(), new Data(), "Marinha", "Antártida", "Evento teste 2"));
    }

    public boolean existsProva(){
        return true;
    }

    public void addEvento(Evento evento){
        listaEventos.add(evento);
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
}
