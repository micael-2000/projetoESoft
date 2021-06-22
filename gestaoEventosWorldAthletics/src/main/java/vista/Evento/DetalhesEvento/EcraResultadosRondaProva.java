package vista.Evento.DetalhesEvento;

import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EcraResultadosRondaProva extends JDialog {

    private JButton voltarButton;
    private JButton atualizarResultadosButton;
    private JButton saveButton;
    private JLabel nomeRonda;
    private JLabel nomeEvento;
    private JTable tableResultados;
    private JPanel painelResultados;
    private JPanel painelTabela;
    private JScrollPane jsp;

    public EcraResultadosRondaProva(Evento evento, Prova prova){

        nomeRonda.setText(prova.getNome());
        nomeEvento.setText(evento.getNomeEvento());

        GridLayout gl =new GridLayout(1,10);
        painelTabela.setLayout(gl);

        criarTabelaResultados();

        voltarButton.addActionListener(this::btnVoltarActionPerformed);
        atualizarResultadosButton.addActionListener(this::btnAtualizarResultadosActionPerformed);
        saveButton.setVisible(false);

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

        tableResultados = new JTable(inscritos, columnNames);
        tableResultados.setDefaultEditor(Object.class, null);

        jsp = new JScrollPane(tableResultados);

        painelTabela.add(jsp);

    }

    public void btnAtualizarResultadosActionPerformed(ActionEvent e){
        tableResultados.setDefaultEditor(ColumnName.class, new DefaultCellEditor(new JTextField()));
        saveButton.setVisible(true);
        saveButton.addActionListener(this::btnSaveActionPerformed);
        atualizarResultadosButton.setVisible(false);
    }

    public void btnSaveActionPerformed(ActionEvent e){
        //guardarResultados();
        saveButton.setVisible(false);
        atualizarResultadosButton.setVisible(true);
    }

    //private void guardarResultados(){
      //  DadosAplicacao.INSTANCE.addResultado(tableResultados);
    //}

    /*private void guardarRecordesDisco() {
        ObjectOutputStream oos = null;
        try {
            File f =new File(System.getProperty("user.home")+ File.separator+"rondas.resultados");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(tableResultados);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }*/

    /*private void lerRecordesDoDisco() {
        ObjectInputStream ois = null;
        File f = new File(System.getProperty("user.home")+File.separator+"rondas.resultados");
        if (!f.canRead()) return;

        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            tableResultados = (JTable) ois.readObject();
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }*/

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

