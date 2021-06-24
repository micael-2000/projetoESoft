package modelo;

import java.util.ArrayList;

public class Prova extends ProvaPreDefinida {
    private ArrayList<Ronda> listaDeRondas;
    private ArrayList<Resultado> listaDeResultados;
    private ArrayList<Atleta> listaAtletas;

    public Prova(ProvaPreDefinida prova){
        super(prova.getNome(), prova.getCategoria(), prova.getLocal(), prova.getTipoProva(), prova.getGenero(), prova.getNotas(), prova.getId(),prova.getMarcaMinima());
        listaDeRondas = new ArrayList<>();
        listaAtletas = new ArrayList<>();
    }

    public Ronda getListaDeRondasDaProvaPorDia(int dia){
        Ronda rondaDoDia = null;

        for (Ronda ronda : listaDeRondas){

            if (ronda.getDiaCompeticao() == dia){

                rondaDoDia = ronda;
            }
        }
        return rondaDoDia;
    }

    public void addRonda(Ronda ronda){
        listaDeRondas.add(ronda);
    }

    public ArrayList<Ronda> getListaDeRondas() {
        return listaDeRondas;
    }

    public Ronda getRondaPorNome(String nome){
        for (Ronda ronda : listaDeRondas){
            if (ronda.getNomeRonda().equals(nome)){
                return ronda;
            }
        }
        return null;
    }

    public ArrayList<Resultado> getListaDeResultados() {
        return listaDeResultados;
    }

    public void addResultado(Resultado resultado){
        listaDeResultados.add(resultado);
    }

    public ArrayList<Atleta> getListaAtletas() {
        return listaAtletas;
    }

    public void setListaAtletas(ArrayList<Atleta> listaAtletas) {
        this.listaAtletas = listaAtletas;
    }

}
