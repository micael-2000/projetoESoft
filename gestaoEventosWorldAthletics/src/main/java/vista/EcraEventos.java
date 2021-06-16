package vista;

import modelo.DadosAplicacao;
import modelo.Evento;
import vista.EcraCriarEditarEvento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcraEventos extends JFrame{
    private JPanel painelEventos;
    private JButton rankingsButton;
    private JButton importarEventoButton;
    private JButton criarEventoButton;
    private JButton voltarButton;
    private JTable tabelaEventos;
    DefaultTableModel model;

    public EcraEventos(){
        criarEventoButton.addActionListener(this::btnCriarEventoActionPerformed);

        atualizarTabela();
        TableColumnModel tcm = tabelaEventos.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(200);
        tcm.getColumn(1).setPreferredWidth(150);
        tcm.getColumn(5).setPreferredWidth(100);

        //bloqueia o user de editar as celulas
        tabelaEventos.setDefaultEditor(Object.class, null);

        tabelaEventos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    if(column == 4){
                        new EcraCriarEditarEvento(DadosAplicacao.INSTANCE.getListaEventos().get(row));
                    }
                    else if(column == 6){
                        int input = JOptionPane.showConfirmDialog(null, "Confirma a eliminação do evento: " + DadosAplicacao.INSTANCE.getEvento(row).getNomeEvento());

                        //Eliminar evento
                        if(input == 0){
                            DadosAplicacao.INSTANCE.removeEvento(row);
                            JOptionPane.showMessageDialog(null,"Foi eliminado o Evento");
                            atualizarTabela();
                        }
                    }
                }
            }
        });

        setContentPane(painelEventos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void btnCriarEventoActionPerformed(ActionEvent e) {
        /*
        if (existsProva() == false)
        {
            mostrar erro
        }
        else{
        */
            new EcraCriarEditarEvento();
    }

    private void atualizarTabela(){
        Object columnNames[] = {"Data", "Evento", "Cidade", "País", "", "", ""};

        model = new DefaultTableModel(null, columnNames);
        tabelaEventos.setModel(model);

        for (Evento evento: DadosAplicacao.INSTANCE.getListaEventos()) {
            model.addRow(new Object[]{evento.getDataInicio() + "-" + evento.getDataFim(), evento.getNomeEvento(), evento.getLocal(), evento.getPais(), "Editar", "Exportar Dados", "Eliminar"});
        }
    }
}
