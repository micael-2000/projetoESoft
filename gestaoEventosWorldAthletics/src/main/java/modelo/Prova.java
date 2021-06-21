package modelo;

public class Prova extends ProvaDadosPreDefinidos{

    public Prova(ProvaDadosPreDefinidos prova){
        super(prova.getNome(), prova.getCategoria(), prova.getLocal(), prova.getTipoProva(), prova.getGenero(), prova.getNotas(), prova.getId());
    }
}
