package vista.Evento.DetalhesEvento;

import modelo.DadosAplicacao;
import modelo.Evento;
import modelo.Prova;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;

public class EcraEventoVencedores extends JFrame{
    private JPanel painelVencedores;
    private JButton voltarButton;
    private JButton programaButton;
    private JButton tabelaDeMedalhasButton;
    private JLabel nomeEvento;
    private JTable tabelaVencedores;
    private JScrollPane scrollVencedores;
    private DefaultTableModel model;
    private Evento evento;

    public EcraEventoVencedores(Evento evento){
        this.evento = evento;
        nomeEvento.setText(evento.getNomeEvento());

        voltarButton.addActionListener(this::btnVoltarActionPeformed);
        programaButton.addActionListener(this::btnProgramaActionPeformed);
        tabelaDeMedalhasButton.addActionListener(this::btnTabelaMedalhasActionPeformed);

        preenchimentoTabela();

        setContentPane(painelVencedores);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void preenchimentoTabela(){
        Object columnNames[] = {"Nome", "Sexo", "País", "Resultado"};

        model = new DefaultTableModel(null, columnNames);
        tabelaVencedores.setModel(model);

        for (Prova prova : evento.getListaProvas()) {
            model.addRow(new Object[]{prova.getNome()});
            for (int i = 0; i < 2; i++) {
                model.addRow(new Object[]{"", "M", "Portugal", "10.23"});
            }
        }

        TableColumnModel tcm = tabelaVencedores.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(200);

        //bloqueia o user de editar as celulas
        tabelaVencedores.setDefaultEditor(Object.class, null);
        scrollVencedores.setViewportView(tabelaVencedores);
    }

    public void btnVoltarActionPeformed(ActionEvent e) {
        setVisible(false);
    }

    public void btnProgramaActionPeformed(ActionEvent e) {
        setVisible(false);
        new EcraProgramaEvento(evento);
    }

    public void btnTabelaMedalhasActionPeformed(ActionEvent e) {
        setVisible(false);
        new EcraEventoMedalhas(evento);
    }
}
