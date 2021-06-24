package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Data {
    //private Calendar calendar;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minutos;

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

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        fmt.setLenient(false);
        try {

            Date parseDate = fmt.parse(raw);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(parseDate);

            /*System.out.println("Dia:" + calendar.get(Calendar.DAY_OF_MONTH));
            System.out.println("Mes:" + calendar.get(Calendar.MONTH) + 1);
            System.out.println("Ano:" + calendar.get(Calendar.YEAR));*/

            return new Data(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean isValida(){
        return true;
    }

    @Override
    public String toString() {
        return ""+this.dia+"/"+this.mes+"/"+this.ano+"";
    }

    public String toStringComHora() {
        return ""+this.dia+"/"+this.mes+"/"+this.ano+" - "+this.hora+":"+this.minutos;
    }
}
