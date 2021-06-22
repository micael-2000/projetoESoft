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

    public EcraAtletas(String title){
        super(title);

        this.preencherTabelaAtleta();

        btnCriarAtleta.addActionListener(this::btnCriarAtletaActionPerformed);
        btnSair.addActionListener(this::btnSairActionPerformed);

        /*tabelaAtletas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    //eliminar atleta
                    if(column == 5){
                        System.out.println("Eliminar");
                    }else{
                        System.out.println("Na linha");
                    }

                }
            }
        });*/

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
    }



    private void btnCriarAtletaActionPerformed(ActionEvent actionEvent) {
        new EcraCriarAtleta().setVisible(true);
        preencherTabelaAtleta();
    }

    private void btnSairActionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    private void preencherTabelaAtleta() {
        Object columnNames[] = {"Nome", "País", "Género", "Data de nascimento", "Contacto", "Eliminar"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tabelaAtletas.setModel(model);

        for (Atleta atleta: DadosAplicacao.INSTANCE.getListaAtletas()) {
            model.addRow(new Object[]{atleta.getNome(), atleta.getPais(), atleta.getGenero(), atleta.getDataNascimento().toString(), atleta.getContacto(), "Eliminar"});
        }

    }

}
