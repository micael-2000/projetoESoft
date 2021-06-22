package vista.Evento.DetalhesEvento;

import modelo.Evento;
import modelo.Prova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class EcraAcompanharProvaEvento extends JDialog{

    private JButton voltarButton;
    private JLabel nomeEvento;
    private JPanel painelAcompanhar;
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JLabel provaNome;
    private JPanel painelTabelas;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JScrollPane jsp;
    private JScrollPane jsp2;
    private JScrollPane jsp3;


    public EcraAcompanharProvaEvento(Evento evento, Prova prova){
        nomeEvento.setText(evento.getNomeEvento());
        provaNome.setText(prova.getNome());
        //para cada ronda da prova, apresentar uma tabela com os resultados

        GridLayout gl =new GridLayout(1,10);
        painel1.setLayout(gl);

        GridLayout gl2 =new GridLayout(1,10);
        painel2.setLayout(gl2);

        GridLayout gl3 =new GridLayout(1,10);
        painel3.setLayout(gl3);

        criarTabelaResultados();

        voltarButton.addActionListener(this::btnVoltarActionPerformed);

        painelAcompanhar.setPreferredSize(new Dimension(1200,600));
        setContentPane(painelAcompanhar);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public void btnVoltarActionPerformed(ActionEvent e){
        setVisible(false);
    }

    public void criarTabelaResultados(){

        //getListaInscritos

        String[][] inscritos = new String[4][7];

        int numCol=0;

        for (int i = 0; i < 3; i++) {
            inscritos[i][numCol] = "1";
            inscritos[i][numCol+1] = "Rúben Carreira";
            inscritos[i][numCol+2] = "Portugal";
            inscritos[i][numCol+3] = String.valueOf (i+1);
            inscritos[i][numCol+4] = "00:02:05";
            inscritos[i][numCol+5] = "--------";
            if (i < 2){
                inscritos[i][numCol+6] = "Q";
            } else{
                inscritos[i][numCol+6] = "";
            }

        }

        ColumnName columnNames[] = { new ColumnName("ID"), new ColumnName("Nome"), new ColumnName("Pais"), new ColumnName("Pos"), new ColumnName("Resultado"),
                new ColumnName("Diferença"), new ColumnName("Qualificado")};

        ColumnName columns[] = {columnNames[0], columnNames[1], columnNames[2], columnNames[3], columnNames[4], columnNames[5], columnNames[6]};

        table1 = new JTable(inscritos, columnNames);
        table1.setDefaultEditor(Object.class, null);

        table2 = new JTable(inscritos, columnNames);
        table2.setDefaultEditor(Object.class, null);

        table3 = new JTable(inscritos, columnNames);
        table3.setDefaultEditor(Object.class, null);

        jsp = new JScrollPane(table1);
        jsp2 = new JScrollPane(table2);
        jsp3 = new JScrollPane(table3);

        painel1.add(jsp);
        painel2.add(jsp2);
        painel3.add(jsp3);

    }

    public class ColumnName {
        String cname;

        public ColumnName(String name) {
            cname = name;
        }

        public String toString() {
            return cname;
        }
    }

}
