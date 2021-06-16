package modelo;

import java.util.Calendar;

public class Data {
    private Calendar calendar;

    public Data() {

    }

    public static Data parse(String raw){
        return new Data();
    }

    public boolean isValida(){
        return true;
    }

    @Override
    public String toString() {
        return "dataPorFuncionar";
    }
}
