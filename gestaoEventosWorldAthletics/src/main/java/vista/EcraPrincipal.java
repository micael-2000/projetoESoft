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

    }
    private void btnInscricoesActionPerformed(ActionEvent actionEvent) {
        new EcraInscricoes("Ecrã Inscrições").setVisible(true);
    }



    public static void main(String[] args) {
        new DadosAplicacao();

        //Atleta
        DadosAplicacao.INSTANCE.addAtleta(new Atleta(1,"Manuel", "Portugal","Masculino", 9177777, new Data(1,2,1999)  ));
        DadosAplicacao.INSTANCE.addAtleta(new Atleta(2,"Fabio", "França","Masculino", 9199999, new Data(3,3,1999)  ));
        DadosAplicacao.INSTANCE.addAtleta(new Atleta(3,"Maria", "França","Feminino", 9199999, new Data(3,3,1999)  ));
        DadosAplicacao.INSTANCE.addAtleta(new Atleta(4,"Ruben", "Alemanha","Masculino", 9199999, new Data(3,3,1999)  ));

        //Prova pré definidas
        ArrayList<Prova> provas = new ArrayList<>();
        ProvaPreDefinida prova1 = new ProvaPreDefinida("Prova1", "Corrida", "Pista exterior", "Eliminatórias", Genero.FEMININO, " ", -1, 20.0);
        DadosAplicacao.INSTANCE.addProvaPreDefinida(prova1);
        ProvaPreDefinida prova2 = new ProvaPreDefinida("Prova2", "Salto", "Pista exterior", "Eliminatórias", Genero.MASCULINO, " ", -1, 20.0);
        DadosAplicacao.INSTANCE.addProvaPreDefinida(prova2);

        ProvaPreDefinida prova3 = new ProvaPreDefinida("Prova3", "Corrida", "Pista interior", "Eliminatórias", Genero.MASCULINO, " ", -1, 20.0);
        DadosAplicacao.INSTANCE.addProvaPreDefinida(prova3);

        ProvaPreDefinida prova4 = new ProvaPreDefinida("Prova4", "Corrida", "Pista exterior", "Eliminatórias", Genero.MASCULINO, " ", -1, 20.0);
        DadosAplicacao.INSTANCE.addProvaPreDefinida(prova4);

        ProvaPreDefinida prova5 = new ProvaPreDefinida("Prova5", "Corrida", "Pista exterior", "Eliminatórias", Genero.MASCULINO, " ", -1, 20.0);
        DadosAplicacao.INSTANCE.addProvaPreDefinida(prova5);

        //Criar provas
        ArrayList<Prova> provas1 = new ArrayList<>();
        ArrayList<Prova> provas2 = new ArrayList<>();
        ArrayList<Prova> provas3 = new ArrayList<>();

        provas1.add(new Prova(prova2));
        provas1.add(new Prova(prova4));

        provas2.add(new Prova(prova5));
        provas2.add(new Prova(prova1));
        provas2.add(new Prova(prova2));

        provas3.add(new Prova(prova3));

        //Evento
        DadosAplicacao.INSTANCE.addEvento(new Evento(provas1, new Data(10,10,2010), new Data(20,10,2010), "Lisboa", "Portugal", "Evento teste 1"));
        DadosAplicacao.INSTANCE.addEvento(new Evento(provas2, new Data(20,10,2011), new Data(30,10,2011), "Marinha", "Antártida do pacifico", "Evento teste 2"));
        DadosAplicacao.INSTANCE.addEvento(new Evento(provas3, new Data(20,10,2011), new Data(30,10,2011), "Marinha", "Oceando de Lisboa", "Evento teste 3"));

        //Inscrições
        DadosAplicacao.INSTANCE.addInscricao(new Inscricao(DadosAplicacao.INSTANCE.getAtleta(1), DadosAplicacao.INSTANCE.getProvaByName("Prova2"),25, "Portugal"));
        DadosAplicacao.INSTANCE.addInscricao(new Inscricao(DadosAplicacao.INSTANCE.getAtleta(1), DadosAplicacao.INSTANCE.getProvaByName("Prova1"),25, "Portugal"));

        //Resultados
        DadosAplicacao.INSTANCE.addResultado(new Resultado(2, "Fabio", "Portugal",1,25.6,true, "Meia Final", "Prova1"));
        DadosAplicacao.INSTANCE.addResultado(new Resultado(1, "Manuel", "Portugal",2,25.4,true, "Meia Final", "Prova1"));
        DadosAplicacao.INSTANCE.addResultado(new Resultado(1, "Manuel", "Portugal",1,26.4,true, "Final", "Prova1"));
        new EcraPrincipal("Gestao Eventos").setVisible(true);
    }
}
