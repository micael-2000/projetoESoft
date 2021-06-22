package vista.Evento.DetalhesEvento;

import modelo.DadosAplicacao;
import modelo.Evento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;

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

    public EcraEventoMedalhas(Evento evento){
        this.evento = evento;
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

    private void preenchimentoTabela(String nomeTabela, DefaultTableModel model, JTable tabela, JScrollPane scrollPane){
        Object columnNames[] = {"Rank", nomeTabela, "Ouro", "Prata", "Bronze", "Total"};

        model = new DefaultTableModel(null, columnNames);
        tabela.setModel(model);

        for (Evento evento: DadosAplicacao.INSTANCE.getListaEventos()) {
            model.addRow(new Object[]{"1 ", "2 ", " 3", "4 ", " 5", " 6"});
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
