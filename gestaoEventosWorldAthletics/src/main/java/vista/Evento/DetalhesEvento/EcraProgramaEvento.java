package vista.Evento.DetalhesEvento;

import modelo.Evento;
import vista.Evento.DetalhesEvento.EcraEventoMedalhas;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EcraProgramaEvento extends JDialog{
    private JPanel painelProgramaEvento;
    private JButton tabelaDeMedalhasButton;
    private JButton vencedoresButton;
    private JButton voltarButton;
    private String[] arrayButtonsDias;
    private Evento evento;

    public EcraProgramaEvento(Evento evento){
        this.evento = evento;
        tabelaDeMedalhasButton.addActionListener(this::btnTabelaMedalhasActionPeformed);
        vencedoresButton.addActionListener(this::btnVencedoresActionPeformed);

        setContentPane(painelProgramaEvento);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void btnTabelaMedalhasActionPeformed(ActionEvent e) {
        setVisible(false);
        new EcraEventoMedalhas(evento);
    }

    public void btnVencedoresActionPeformed(ActionEvent e) {
        setVisible(false);
        new EcraEventoVendedores(evento);
    }
}
