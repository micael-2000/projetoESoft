package vista;

import modelo.*;
import vista.Atleta.EcraAtletas;
import vista.Atleta.EcraInscricoes;
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
        atletasButton.addActionListener(this::btnAtletasActionPerformed);
        inscriçõesButton.addActionListener(this::btnInscricoesActionPerformed);

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

    private void btnAtletasActionPerformed(ActionEvent actionEvent) {
        new EcraAtletas("Ecrã Atletas").setVisible(true);
    }
    private void btnInscricoesActionPerformed(ActionEvent actionEvent) {
        new EcraInscricoes("Ecrã Inscrições").setVisible(true);
    }



    public static void main(String[] args) {
        new DadosAplicacao();

        ArrayList<Prova> provas = new ArrayList<>();
        ProvaPreDefinida prova = new ProvaPreDefinida("Prova1", "Corrida", "Pista exterior", "Eliminatórias", Genero.FEMININO, " ", -1);
        DadosAplicacao.INSTANCE.addProvaPreDefinida(prova);
        provas.add(new Prova(prova));
        prova = new ProvaPreDefinida("Prova2", "Corrida", "Pista exterior", "Eliminatórias", Genero.MASCULINO, " ", -1);
        DadosAplicacao.INSTANCE.addProvaPreDefinida(prova);
        provas.add(new Prova(prova));

        DadosAplicacao.INSTANCE.addEvento(new Evento(provas, new Data(10,10,2010), new Data(20,10,2010), "Lisboa", "Portugal", "Evento teste"));
        DadosAplicacao.INSTANCE.addEvento(new Evento(provas, new Data(20,10,2011), new Data(30,10,2011), "Marinha", "Antártida", "Evento teste 2"));

        new EcraPrincipal("Gestao Eventos").setVisible(true);
    }
}
