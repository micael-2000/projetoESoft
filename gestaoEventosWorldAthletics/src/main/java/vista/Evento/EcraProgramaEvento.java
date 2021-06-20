package vista.Evento;

import modelo.*;
import vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EcraProgramaEvento extends JDialog{
    private JPanel painelProgramaEvento;
    private String[] arrayButtonsDias;

    public EcraProgramaEvento(Evento evento){

        String diaInicio = evento.getDataInicio().toString();
        String diaFim = evento.getDataFim().toString();

        //modificar para os casos em que o mes muda
        int diff = Integer.parseInt(diaFim) - Integer.parseInt(diaInicio);
        int diasParaOArray = Integer.parseInt(diaInicio);
        arrayButtonsDias = new String[diff];

        for (int i = 0; i < diff; i++) {
            arrayButtonsDias[i] = StringParse(diasParaOArray);
            diasParaOArray++;
        }


        setContentPane(painelProgramaEvento);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        //voltarButton.addActionListener(this::btnCancelarActionPerformed);

    }

    private void btnvoltarActionPerformed(ActionEvent e){
        setVisible(false);
    }
}
