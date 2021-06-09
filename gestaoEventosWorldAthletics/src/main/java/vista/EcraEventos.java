package vista;

import vista.EcraCriarEditarEvento;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EcraEventos extends JFrame{
    private JPanel painelEventos;
    private JButton rankingsButton;
    private JButton importarEventoButton;
    private JButton criarEventoButton;
    private JButton voltarButton;

    public EcraEventos(){
        criarEventoButton.addActionListener(this::btnCriarEventoActionPerformed);

        setContentPane(painelEventos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void btnCriarEventoActionPerformed(ActionEvent e) {
        new EcraCriarEditarEvento();
    }
}
