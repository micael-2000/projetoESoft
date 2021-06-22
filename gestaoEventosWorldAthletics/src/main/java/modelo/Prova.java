package modelo;

import java.util.ArrayList;

public class Prova extends ProvaDadosPreDefinidos{
    private ArrayList<Ronda> listaDeRondas;

    public Prova(ProvaDadosPreDefinidos prova){
        super(prova.getNome(), prova.getCategoria(), prova.getLocal(), prova.getTipoProva(), prova.getGenero(), prova.getNotas(), prova.getId());
        listaDeRondas = new ArrayList<>();
    }
}
