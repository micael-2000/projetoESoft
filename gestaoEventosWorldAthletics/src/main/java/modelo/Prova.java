package modelo;

import java.util.ArrayList;

public class Prova extends ProvaPreDefinida {
    private ArrayList<Ronda> listaDeRondas;

    public Prova(ProvaPreDefinida prova){
        super(prova.getNome(), prova.getCategoria(), prova.getLocal(), prova.getTipoProva(), prova.getGenero(), prova.getNotas(), prova.getId(),prova.getMarcaMinima());
        listaDeRondas = new ArrayList<>();
    }
}
