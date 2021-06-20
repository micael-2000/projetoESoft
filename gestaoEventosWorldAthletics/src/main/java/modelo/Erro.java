package modelo;

import javax.swing.*;

public class Erro {
    private static String[] errosEvento = {
            "Inicio erros Evento",
            "O campo \"Nome do Evento\" é obrigatório  ",
            "O campo \"data inicio\" é obrigatório  ",
            "A data do inicio tem de estar no formato \"DD-MM-YYYY\"  ",
            "A data inicio é inválida",
            "O campo \"data fim\" é obrigatório  ",
            "A data do fim tem de estar no formato \"DD-MM-YYYY\"",
            "A data fim é inválida",
            "O campo \"Local\" é obrigatório",
            "O campo \"País\" é obrigatório  ",
    };
    private static String[] errosProva ={
            "Inicio erros Prova"
    };

    public static void mostrarErro(String nomeEcra, int posicao){
        String erro = null;
        if(nomeEcra.equals("Evento")){
            erro = errosEvento[posicao];
        } else if(nomeEcra.equals("Prova")){
            erro = errosProva[posicao];
        }

        JOptionPane.showConfirmDialog(null, erro, "Erro", JOptionPane.DEFAULT_OPTION);
    }
}
