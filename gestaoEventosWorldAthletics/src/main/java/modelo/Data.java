package modelo;

import java.util.Calendar;

public class Data {
    private Calendar calendar;

    private Data() {

    }

    public static Data parse(String raw){
        return new Data();
    }

    public boolean isValida(){
        return true;
    }
}
