package modelo;

public class Atleta {
    private String nome;
    private String pais;
    private String genero;
    private int contacto;
    private Data dataNascimento;

    public Atleta(String nome, String pais, String genero, int contacto, Data dataNascimento) {
        this.nome = nome;
        this.pais = pais;
        this.genero = genero;
        this.contacto = contacto;
        this.dataNascimento = dataNascimento;
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
}
