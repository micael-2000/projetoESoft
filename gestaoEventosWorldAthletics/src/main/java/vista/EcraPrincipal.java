package vista;

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

    public static void main(String[] args) {
        new EcraPrincipal("Gestao Eventos").setVisible(true);
    }
}
