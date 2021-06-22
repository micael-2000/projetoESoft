package modelo;

import java.util.Calendar;

public class Data {
    //private Calendar calendar;
    int dia;
    int mes;
    int ano;
    int hora;
    int minutos;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano= ano;
    }

    public Data(int dia, int mes, int ano, int hora, int minutos){
        this.dia = dia;
        this.mes = mes;
        this.ano= ano;
        this.hora= hora;
        this.minutos = minutos;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public static Data parse(String raw){
        //return new Data();
        return null;
    }

    public boolean isValida(){
        return true;
    }

    @Override
    public String toString() {
        return ""+this.dia+"/"+this.mes+"/"+this.ano+"";
    }
}
