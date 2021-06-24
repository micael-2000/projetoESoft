package vista.Evento;

import modelo.DadosAplicacao;
import modelo.Evento;
import modelo.Pais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EcraRankings extends JFrame{
    private JPanel painelRankingsGeral;
    private JButton voltarButton;
    private JTable tabelaRanking;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    public EcraRankings(){
        voltarButton.addActionListener(this::btnVoltarActionPeformed);

        preenchimentoTabela();

        setContentPane(painelRankingsGeral);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void preenchimentoTabela(){
        Object columnNames[] = {"Rank", "País", "Ouro", "Prata", "Bronze", "Total"};

        model = new DefaultTableModel(null, columnNames);
        tabelaRanking.setModel(model);

        //Pais[] paises =  DadosAplicacao.INSTANCE.getListaPais().clone();

        Collections.sort(DadosAplicacao.INSTANCE.getListaPais(), new Comparator<Pais>() {
            @Override
            public int compare(Pais pais1, Pais pais2) {
                return Integer.compare(pais1.getTotalMedalhas(), pais2.getTotalMedalhas());
            }
        });

        int posicao = 1;
        for (Pais pais: DadosAplicacao.INSTANCE.getListaPais()) {
            model.addRow(new Object[]{ posicao, pais.getNome(), pais.getMedalhasOuro(), pais.getMedalhasPrata(), pais.getMedalhasBronze(), pais.getTotalMedalhas()});
            posicao ++;
        }
/*
        for (int i = 0; i < paises.length; i++){
            posicao = i;
            medalhas = paises[i].getTotalMedalhas();
            for (int j = 0; j < paises.length; j++) {
                if (paises[j].getTotalMedalhas() > medalhas){
                    posicao = j;
                    medalhas = paises[j].getTotalMedalhas();
                }
            }
            model.addRow(new Object[]{ i + 1, paises[posicao].getNome(), paises[posicao].getMedalhasOuro(), paises[posicao].getMedalhasPrata(), paises[posicao].getMedalhasBronze(), paises[posicao].getTotalMedalhas()});
            paises[posicao]= new Pais(" ");
        }

 */

        TableColumnModel tcm = tabelaRanking.getColumnModel();
        tcm.getColumn(1).setPreferredWidth(150);

        //bloqueia o user de editar as celulas
        tabelaRanking.setDefaultEditor(Object.class, null);
        scrollPane.setViewportView(tabelaRanking);

    }

    public void btnVoltarActionPeformed(ActionEvent e) {
        setVisible(false);
    }
}
