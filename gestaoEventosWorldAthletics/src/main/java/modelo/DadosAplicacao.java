package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();
    private HashMap<Integer, ProvaPreDefinida> listaProvasPreDefinidas;
    private ArrayList<Evento> listaEventos;
    private ArrayList<Atleta> listaAtletas;
    private ArrayList<Inscricao> listaInscricoes;
    //private ArrayList<JTable> listaResultados;
    public DadosAplicacao() {
        //Provas pre definido
        listaProvasPreDefinidas = new HashMap<>(30);
      //  listaResultados = new ArrayList<>();
        //Eventos
        listaEventos = new ArrayList<>();
        listaAtletas = new ArrayList<>();
        listaInscricoes = new ArrayList<>();
    }

    //public void addResultado(JTable tabelaResultado){ listaResultados.add(tabelaResultado); }

    //public void removeResultado(int posicao){
        //listaResultados.remove(posicao);
    //}

    //public ArrayList<JTable> getListaResultados(){ return listaResultados; }

    //public JTable getResultado(int index){ return listaResultados.get(index); }

    //public int getIndiceResultado(JTable table){
        //return listaEventos.indexOf(table);
    //}






    //Evento
    public void removeEvento(int posicao){
        listaEventos.remove(posicao);
    }

    public ArrayList<Evento> getListaEventos() {
        return listaEventos;
    }

    public Evento getEvento(int index) {
        return listaEventos.get(index);
    }

    public int getIndiceEvento(Evento evento){
        return listaEventos.indexOf(evento);
    }

    public void addEvento(Evento evento){
        listaEventos.add(evento);
    }

    //Prova
    public Collection<ProvaPreDefinida> getListaProvasPreDefinidas() {
        return listaProvasPreDefinidas.values();
    }

    public ProvaPreDefinida getProvaPreDefinidas(Integer idProva) {
        return listaProvasPreDefinidas.get(idProva);
    }

    public void removeProvaPreDefinidas(int idProva){
        listaProvasPreDefinidas.remove(idProva);
    }

    public int getSizeProvasPreDefinidas(){
        if (listaProvasPreDefinidas.isEmpty()){
            return 0;
        }
        return listaProvasPreDefinidas.size();
    }
    public void addProvaPreDefinida(ProvaPreDefinida prova){
        listaProvasPreDefinidas.put(prova.getId(), prova);
    }


    //Atletas
    public ArrayList<Atleta> getListaAtletas() {
        return listaAtletas;
    }

    public void addAtleta(Atleta atleta){
        listaAtletas.add(atleta);
    }

    public void removeAtleta(int posicao) {
        listaAtletas.remove(posicao);
    }

    public Atleta getAtleta(int index) {
        return listaAtletas.get(index);
    }

    //Inscricoes
    public ArrayList<Inscricao> getListaInscricoes() {
        return listaInscricoes;
    }

    public void addInscricao(Inscricao inscricao){listaInscricoes.add(inscricao);}

    public void removeInscricao(int posicao) {
            listaInscricoes.remove(posicao);
    }
}
