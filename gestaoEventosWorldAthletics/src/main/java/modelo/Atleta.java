package modelo;

public class Atleta {
    private int numeroAtleta = 0;
    private String nome;
    private String pais;
    private String genero;
    private int contacto;
    private Data dataNascimento;
    private int medalhasBronze;
    private int medalhasPrata;
    private int medalhasOuro;
    private Pais paisRef;
    private int totalMedalhas;

    public Atleta(int numeroAtleta,String nome, String pais, String genero, int contacto, Data dataNascimento) {
        this.medalhasBronze = 0;
        this.medalhasPrata = 0;
        this.medalhasOuro = 0;
        this.numeroAtleta = numeroAtleta;
        this.nome = nome;
        this.pais = pais;
        this.genero = genero;
        this.contacto = contacto;
        this.dataNascimento = dataNascimento;
    }

    public void setPaisRef(Pais pais) {
        this.paisRef = pais;
    }

    public Pais getPaisRef(){
        return paisRef;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getNumeroAtleta() {
        return numeroAtleta;
    }

    public int getMedalhasBronze() {
        return medalhasBronze;
    }

    public int getMedalhasPrata() {
        return medalhasPrata;
    }

    public int getMedalhasOuro() {
        return medalhasOuro;
    }

    public int getTotalMedalhas(){
        return medalhasBronze + medalhasPrata + medalhasOuro;
    }
}
