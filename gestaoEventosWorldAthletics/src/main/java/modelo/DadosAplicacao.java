package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();
    private HashMap<Integer, ProvaPreDefinida> listaProvasPreDefinidas;
    private ArrayList<Evento> listaEventos;
    private ArrayList<Atleta> listaAtletas;
    private ArrayList<Inscricao> listaInscricoes;
    private ArrayList<Pais> listaPais;
    private ArrayList<Resultado> listaResultados;

    //private ArrayList<JTable> listaResultados;
    public DadosAplicacao() {
        //Provas pre definido
        listaProvasPreDefinidas = new HashMap<>(30);
      //  listaResultados = new ArrayList<>();
        //Eventos
        listaEventos = new ArrayList<>();
        listaAtletas = new ArrayList<>();
        listaInscricoes = new ArrayList<>();
        listaPais = new ArrayList<>();
        listaResultados = new ArrayList<>();
    }

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

    public Prova getProvaByName(String nome) {

        ArrayList<Prova> provas = null;
        for (Evento evento : listaEventos) {
            provas = evento.getListaProvas();
            for (Prova prova: provas) {
                if (prova.getNome().equals(nome)){
                    return prova;
                }
            }
        }
        return null;
    }



    //Atletas
    public ArrayList<Atleta> getListaAtletas() {
        return listaAtletas;
    }

    public void addAtleta(Atleta atleta){
        listaAtletas.add(atleta);
        int achado = -1;

        for (Pais pais: listaPais) {
            if(pais.getNome().equals(atleta.getPais())){
                atleta.setPaisRef(pais);
                achado = 1;
                break;
            }
        }

        if(achado == -1){
            Pais pais = new Pais(atleta.getPais());
            listaPais.add(pais);
            atleta.setPaisRef(pais);
        }
    }

    public void removeAtleta(int posicao) {
        listaAtletas.remove(posicao);
    }

    public Atleta getAtleta(int index) {
        return listaAtletas.get(index);
    }

    public Atleta getAtletaByNumeroAtleta(int numero) {
        for(Atleta atleta : listaAtletas){
            if(atleta.getNumeroAtleta() == numero){
                return atleta;
            }
        }
        return null;
    }

    //Inscricoes
    public ArrayList<Inscricao> getListaInscricoes() {
        return listaInscricoes;
    }

    public ArrayList<Inscricao> getListaInscricoesPorProva(Prova prova) {
        ArrayList<Inscricao> listaInscricoesDaProva = new ArrayList<>();

        for (Inscricao insc : listaInscricoes){
            if (insc.getNomeProva().equals(prova.getNome())){
                listaInscricoesDaProva.add(insc);
            }
        }

        return listaInscricoesDaProva;
    }

    public ArrayList<Atleta> getAtletasInscritosNumaProva(Prova prova){
        ArrayList<Atleta> listaAtletasInscritosNaProva = new ArrayList<>();
        for (Inscricao insc : listaInscricoes){
            if (insc.getNomeProva().equals(prova.getNome())){
                listaAtletasInscritosNaProva.add(insc.getAtleta());
            }
        }

        prova.setListaAtletas(listaAtletasInscritosNaProva);

        return listaAtletasInscritosNaProva;

    }

    public void addInscricao(Inscricao inscricao){listaInscricoes.add(inscricao);}

    public void removeInscricao(int posicao) {
            listaInscricoes.remove(posicao);
    }

    //Pais

    public ArrayList<Pais> getListaPais(){
        return listaPais;
    }

    //Resultados


    public ArrayList<Resultado> getListaResultados() {
        return listaResultados;
    }

    public void addResultado(Resultado resultado){
        listaResultados.add(resultado);
    }

    public ArrayList<Resultado> getResultadosRondaDeProva(String nomeRonda, String nomeProva ){
        ArrayList<Resultado> resultados = new ArrayList<>();
        for (Resultado res : listaResultados){
            if (res.getNomeProva().equals(nomeProva) && res.getNomeRonda().equals(nomeRonda)){
                resultados.add(res);
            }
        }

        return resultados;
    }
}
