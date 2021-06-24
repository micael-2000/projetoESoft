package vista.Evento.DetalhesEvento;

import modelo.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EcraResultadosRondaProva extends JDialog {

    private JButton voltarButton;

    private JButton saveButton;
    private JLabel nomeRonda;
    private JLabel nomeEvento;
    private JTable tableResultados2;
    private JPanel painelResultados;
    private JPanel painelTabela;
    private Ronda ronda;
    private Prova prova;
    private JScrollPane jsp2;

    public EcraResultadosRondaProva(Evento evento, Prova prova, Ronda ronda){
        this.ronda = ronda;
        this.prova = prova;
        nomeRonda.setText(prova.getNome());
        nomeEvento.setText(evento.getNomeEvento());

        GridLayout gl =new GridLayout(1,10);
        painelTabela.setLayout(gl);

        criarTabelaResultados();

        voltarButton.addActionListener(this::btnVoltarActionPerformed);

        saveButton.addActionListener(this::btnSaveActionPerformed);
        painelResultados.setPreferredSize(new Dimension(1200,700));
        setContentPane(painelResultados);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void btnVoltarActionPerformed(ActionEvent e){
        setVisible(false);
    }

    public void criarTabelaResultados(){

        Object[] columnNames = { "Id", "Nome", "Pais","Pos", "Resultado", "Diferenca", "Qualificado"};

        DefaultTableModel model2 = new DefaultTableModel(columnNames, ronda.getListaInscritos().size());

        tableResultados2 = new JTable(model2);

        jsp2 = new JScrollPane(tableResultados2);

        painelTabela.add(jsp2);


    }

    public void btnSaveActionPerformed(ActionEvent e){
        int numRes = DadosAplicacao.INSTANCE.getListaResultados().size();

        String[][] dados = new String[ronda.getListaInscritos().size()][7];
        Resultado resultado = null;
        for (int i = 0; i < tableResultados2.getRowCount(); i++) {
            for (int j = 0; j < tableResultados2.getColumnCount(); j++) {
                dados[i][j] = (String) tableResultados2.getModel().getValueAt(i,j);

            }
        }



        int inc=0;
        for (int i = 0; i < dados.length; i++) {
            if (ronda.isAtletaNaRonda(Integer.parseInt(dados[i][inc]), dados[i][inc + 1]) == 0){
                JOptionPane.showMessageDialog(null,"O atleta que registou não se encontra inscrito na ronda");
                break;
            }else {
                resultado = new Resultado(Integer.parseInt(dados[i][inc]), dados[i][inc + 1], dados[i][inc + 2], Integer.parseInt(dados[i][inc + 3]),
                        Double.parseDouble(dados[i][inc + 4]), Boolean.parseBoolean(dados[i][inc + 6]), ronda.getNomeRonda(), prova.getNome());
                DadosAplicacao.INSTANCE.addResultado(resultado);
            }
        }

        if (numRes < DadosAplicacao.INSTANCE.getListaResultados().size()){
            JOptionPane.showMessageDialog(null, "Resultado(s) adicionado(s)!");
        }

    }


}

