package vista.Evento.DetalhesEvento;

import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EcraEventoMedalhas extends JFrame{
    private JPanel painelEventoMedalhas;
    private JButton voltarButton;
    private JButton programaButton;
    private JButton vencedoresButton;
    private JLabel nomeEvento;
    private JTable tabelaPaisesMedalhados;
    private JTable tabelaAtletasMedalhados;
    private JScrollPane scrollTabelaPaises;
    private JScrollPane scrollTabelaAtletas;
    private DefaultTableModel modelPais;
    private DefaultTableModel modelAtleta;
    private Evento evento;
    private ArrayList<Atleta> atletas;
    private ArrayList<Pais> paises;


    public EcraEventoMedalhas(Evento evento){
        this.evento = evento;
        leituraDados();
        nomeEvento.setText(evento.getNomeEvento());

        voltarButton.addActionListener(this::btnVoltarActionPeformed);
        programaButton.addActionListener(this::btnProgramaActionPeformed);
        vencedoresButton.addActionListener(this::btnVencedoresActionPeformed);

        preenchimentoTabela("País", modelPais, tabelaPaisesMedalhados, scrollTabelaPaises);
        preenchimentoTabela("Atleta", modelAtleta, tabelaAtletasMedalhados, scrollTabelaAtletas);

        setContentPane(painelEventoMedalhas);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void leituraDados(){
        atletas = new ArrayList<>();
        paises = new ArrayList<>();
        for (Prova prova: evento.getListaProvas()) {
            atletas.addAll(prova.getListaAtletas());
        }

        //ordenar os atletas pelo o criterio do numero de medalhas
        Collections.sort(atletas, new Comparator<Atleta>() {
            @Override
            public int compare(Atleta atleta1, Atleta atleta2) {
                return Integer.compare(atleta1.getTotalMedalhas(), atleta2.getTotalMedalhas());
            }
        });

        //buscar paises dos atletas
        for (Atleta atleta: atletas) {
            if(!paises.contains(atleta.getPaisRef())){
                paises.add(atleta.getPaisRef());
            }
        }

        //ordenar os paieses pelo o criterio do numero de medalhas
        Collections.sort(paises, new Comparator<Pais>() {
            @Override
            public int compare(Pais pais1, Pais pais2) {
                return Integer.compare(pais1.getTotalMedalhas(), pais2.getTotalMedalhas());
            }
        });
    }


    private void preenchimentoTabela(String nomeTabela, DefaultTableModel model, JTable tabela, JScrollPane scrollPane){
        Object columnNames[] = {"Rank", nomeTabela, "Ouro", "Prata", "Bronze", "Total"};

        model = new DefaultTableModel(null, columnNames);
        tabela.setModel(model);
        int posicao = 1;

        if(nomeTabela.equals("Pais")){
            for (Pais pais: paises) {
                model.addRow(new Object[]{posicao, pais.getNome(), pais.getNome(), pais.getMedalhasOuro(), pais.getMedalhasPrata(), pais.getMedalhasBronze(), pais.getTotalMedalhas()});
                posicao++;
            }
        }
        else if(nomeTabela.equals("Atleta")){
            for (Atleta atleta : atletas) {
                model.addRow(new Object[]{posicao, atleta.getNome(), atleta.getMedalhasOuro(), atleta.getMedalhasPrata(), atleta.getMedalhasBronze(), atleta.getTotalMedalhas()});
                posicao++;
            }
        }

        TableColumnModel tcm = tabela.getColumnModel();
        tcm.getColumn(1).setPreferredWidth(150);

        //bloqueia o user de editar as celulas
        tabela.setDefaultEditor(Object.class, null);
        scrollPane.setViewportView(tabela);
    }

    public void btnVoltarActionPeformed(ActionEvent e) {
        setVisible(false);
    }

    public void btnProgramaActionPeformed(ActionEvent e) {
        setVisible(false);
        new EcraProgramaEvento(evento);
    }

    public void btnVencedoresActionPeformed(ActionEvent e) {
        setVisible(false);
        new EcraEventoVencedores(evento);
    }
}
