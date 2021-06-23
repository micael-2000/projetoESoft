package vista.Atleta;

import modelo.Atleta;
import modelo.DadosAplicacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcraAtletas extends JFrame{
    private JPanel painelPrincipal;
    private JTable tabelaAtletas;
    private JButton btnImportarAtletas;
    private JButton btnSuspenderAtleta;
    private JButton btnCriarAtleta;
    private JButton btnSair;
    private JScrollPane scrollPaneTabelaAtletas;

    public EcraAtletas(String title){
        super(title);

        btnCriarAtleta.addActionListener(this::btnCriarAtletaActionPerformed);
        btnSair.addActionListener(this::btnSairActionPerformed);

        //bloqueia o user de editar as celulas
        tabelaAtletas.setDefaultEditor(Object.class, null);
        scrollPaneTabelaAtletas.setViewportView(tabelaAtletas);

        tabelaAtletas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    //eliminar atleta
                    if(column == 5){
                        btnEliminarActionPerformed(row);
                    }else{
                        doubleClickJTableRowPerformed(row);
                    }

                }
            }
        });

        this.preencherTabelaAtletas();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
    }


    private void btnCriarAtletaActionPerformed(ActionEvent actionEvent) {
        new EcraCriarAtleta().setVisible(true);
        preencherTabelaAtletas();
    }

    private void btnSairActionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    private void btnEliminarActionPerformed(int row) {
        int input = JOptionPane.showConfirmDialog(null, "Tem a certaza que deseja remover o Atleta?",null,JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION){
            DadosAplicacao.INSTANCE.removeAtleta(row);
            preencherTabelaAtletas();
            return;
        }
        return;
    }

    private void doubleClickJTableRowPerformed(int row) {
        new EcraDetalhes(DadosAplicacao.INSTANCE.getAtleta(row)).setVisible(true);
        preencherTabelaAtletas();
    }

    private void preencherTabelaAtletas() {
        Object columnNames[] = {"Nome", "País", "Género", "Data de nascimento", "Contacto", "Eliminar"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tabelaAtletas.setModel(model);

        for (Atleta atleta: DadosAplicacao.INSTANCE.getListaAtletas()) {
            model.addRow(new Object[]{atleta.getNome(), atleta.getPais(), atleta.getGenero(), atleta.getDataNascimento().toString(), atleta.getContacto(), "Eliminar"});
        }

    }

}
