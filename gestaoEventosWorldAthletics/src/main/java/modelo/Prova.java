package modelo;

public class Prova extends ProvaDadosPreDefinidos{

    public Prova(ProvaDadosPreDefinidos prova){
        this(prova.getNome(), prova.getCategoria(), prova.getLocal(), prova.getTipoProva(), prova.getGenero(), prova.getNotas());
    }
    public Prova(String nome, String categoria, String local, String tipoProva, Genero genero, String notas) {
        super(nome, categoria, local, tipoProva, genero, notas);
    }
}
