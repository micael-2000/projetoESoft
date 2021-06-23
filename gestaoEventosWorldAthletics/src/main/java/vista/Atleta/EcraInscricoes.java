package vista.Atleta;

import modelo.Atleta;
import modelo.DadosAplicacao;
import modelo.Inscricao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class EcraInscricoes extends JFrame {
    private JPanel painelPrincipal;

    private JButton btnSair;
    private JButton btnImportarInscricoes;
    private JLabel lblInscricoes;
    private JTable tabelaInscricoes;
    private JScrollPane scrollPaneInscricoes;

    public EcraInscricoes(String title) {
        super(title);
        setContentPane(painelPrincipal);

        btnImportarInscricoes.addActionListener(this::btnImportarActionPerformed);
        btnSair.addActionListener(this::btnSairActionPerformed);

        //bloqueia o user de editar as celulas
        tabelaInscricoes.setDefaultEditor(Object.class, null);
        scrollPaneInscricoes.setViewportView(tabelaInscricoes);

        tabelaInscricoes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    //eliminar atleta
                    if(column == 4) {
                        btnEliminarActionPerformed(row);
                    }
                }
            }
        });

        this.preencherTabelaInscrocoes();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

    }



    private void btnSairActionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    private void btnImportarActionPerformed(ActionEvent actionEvent) {
        //TODO
    }

    private void btnEliminarActionPerformed(int row) {
        //TODO
    }

    private void preencherTabelaInscrocoes() {
        Object columnNames[] = {"Nome da Prova", "Nome do atleta", "Marca lcançada", "País","Eliminar"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tabelaInscricoes.setModel(model);

        for (Inscricao inscricao: DadosAplicacao.INSTANCE.getListaInscricoes()) {
            model.addRow(new Object[]{inscricao.getNomeProva(), inscricao.getAtleta().getNome(), inscricao.getMarcaAlcancada(), inscricao.getPais(), "Eliminar"});
        }
    }

}


