package vista;

import modelo.*;
import vista.Evento.EcraEventos;
import vista.Prova.EcraProvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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

        ArrayList<Prova> provas = new ArrayList<>();
        ProvaDadosPreDefinidos prova = new ProvaDadosPreDefinidos("Prova1", "Corrida", "Pista exterior", "Eliminatórias", Genero.FEMININO, " ", -1);
        DadosAplicacao.INSTANCE.addProva(prova);
        provas.add(new Prova(prova));
        prova = new ProvaDadosPreDefinidos("Prova2", "Corrida", "Pista exterior", "Eliminatórias", Genero.MASCULINO, " ", -1);
        DadosAplicacao.INSTANCE.addProva(prova);
        provas.add(new Prova(prova));

        DadosAplicacao.INSTANCE.addEvento(new Evento(provas, new Data(), new Data(), "Lisboa", "Portugal", "Evento teste"));
        DadosAplicacao.INSTANCE.addEvento(new Evento(provas, new Data(), new Data(), "Marinha", "Antártida", "Evento teste 2"));

        new EcraPrincipal("Gestao Eventos").setVisible(true);
    }
}
