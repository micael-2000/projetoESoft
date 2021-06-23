package modelo;

public class Inscricao {

    private Atleta atleta;
    private String nomeProva;
    private float marcaAlcancada;
    private String pais;
    private boolean isValida;

    public Inscricao(String nomeProva, float marcaAlcancada, String pais) {
        this.nomeProva = nomeProva;
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
        return nomeProva;
    }

    public void setNomeProva(String nomeProva) {
        this.nomeProva = nomeProva;
    }

    public float getMarcaAlcancada() {
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
