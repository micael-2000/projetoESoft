package modelo;

public class ProvaDadosPreDefinidos {
    private static int id;
    private String nome;
    private String categoria;
    private String local;
    private String tipoProva;
    private String genero;
    private String notas;

    public ProvaDadosPreDefinidos(String nome, String categoria, String local, String tipoProva, String genero, String notas) {
        this.nome = nome;
        this.categoria = categoria;
        this.local = local;
        this.tipoProva = tipoProva;
        this.genero = genero;
        this.notas = notas;
    }

    public static int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoProva() {
        return tipoProva;
    }

    public void setTipoProva(String tipoProva) {
        this.tipoProva = tipoProva;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "ProvaDadosPreDefinidos{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
