package vista;

import modelo.DadosAplicacao;
import modelo.Evento;
import vista.EcraCriarEditarEvento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public EcraEventos(){
        criarEventoButton.addActionListener(this::btnCriarEventoActionPerformed);

        Object[] columnNames = new Object[]{"Data", "Evento", "Cidade", "País", " dsf", " dsf", "X"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tabelaEventos.setModel(model);

        for (Evento evento: DadosAplicacao.INSTANCE.getListaEventos()) {
            model.addRow(new Object[]{evento.getDataInicio() + "-" + evento.getDataFim(), evento.getNomeEvento(), evento.getLocal(), evento.getPais(), "Editar", "Exportar Dados", "X"});

        }

        //bloqueia o user de editar as celulas
        tabelaEventos.setDefaultEditor(Object.class, null);

        tabelaEventos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
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

    }
}
