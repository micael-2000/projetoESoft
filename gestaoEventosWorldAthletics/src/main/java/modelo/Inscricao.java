package modelo;

public class Inscricao {

    private Atleta atleta;
    private Prova prova;
    private double marcaAlcancada;
    private String pais;
    private boolean isValida;

    public Inscricao(Atleta atleta, Prova prova, double marcaAlcancada, String pais) {
        this.atleta = atleta;
        this.prova = prova;
        this.marcaAlcancada = marcaAlcancada;
        this.pais = pais;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public String getNomeProva() {
        return prova.getNome();
    }

    public double getMarcaAlcancada() {
        return marcaAlcancada;
    }

    public void setMarcaAlcancada(float marcaAlcancada) {
        this.marcaAlcancada = marcaAlcancada;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isValida() {
        return isValida;
    }

    public void setValida(boolean valida) {
        isValida = valida;
    }

}
