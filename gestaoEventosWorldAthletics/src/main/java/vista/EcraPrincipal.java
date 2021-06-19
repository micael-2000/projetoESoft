package vista;

import modelo.DadosAplicacao;
import vista.Evento.EcraEventos;
import vista.Prova.EcraProvas;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EcraPrincipal extends JFrame{
    private JButton eventosButton;
    private JButton provasButton;
    private JButton atletasButton;
    private JButton inscriçõesButton;
    private JButton sairButton;
    private JPanel painelPrincipal;

    public EcraPrincipal(String title){
        super(title);

        sairButton.addActionListener(this::btnSairActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        provasButton.addActionListener(this::btnProvasActionPerformed);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnEventosActionPerformed(ActionEvent e) {
        new EcraEventos();
    }

    private void btnProvasActionPerformed(ActionEvent e) {
        new EcraProvas();
    }

    public static void main(String[] args) {
        new DadosAplicacao();
        new EcraPrincipal("Gestao Eventos").setVisible(true);
    }
}
