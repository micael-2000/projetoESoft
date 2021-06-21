package vista.Evento;

import modelo.DadosAplicacao;
import modelo.Evento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;

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

        for (Evento evento: DadosAplicacao.INSTANCE.getListaEventos()) {
            model.addRow(new Object[]{"1 ", "2 ", " 3", "4 ", " 5", " 6"});
        }

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
