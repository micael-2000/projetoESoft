package modelo;

public class Pais {
    private String nome;
    private int medalhasBronze;
    private int medalhasPrata;
    private int medalhasOuro;

    public Pais(String nome){
        this.nome = nome;
        medalhasBronze = 0;
        medalhasPrata = 0;
        medalhasOuro = 0;
    }

    public String getNome() {
        return nome;
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

    public void incrementMedalhasBronze(){
        medalhasBronze++;
    }

    public void incrementMedalhasPrata(){
        medalhasPrata++;
    }

    public void incrementMedalhasOuro(){
        medalhasBronze++;
    }

    public int getTotalMedalhas(){
        return medalhasBronze + medalhasPrata + medalhasOuro;
    }
}
