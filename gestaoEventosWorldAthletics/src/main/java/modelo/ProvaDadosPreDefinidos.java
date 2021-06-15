package modelo;

public class ProvaDadosPreDefinidos {
    private String nome;

    public ProvaDadosPreDefinidos(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ProvaDadosPreDefinidos{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
