package vista.Atleta;

import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

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
        btnImportarAtletas.addActionListener(this::btnImportarAtletasActionPerformed);

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

    private void btnImportarAtletasActionPerformed(ActionEvent actionEvent) {

        String delimiter = ";";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o ficheiro");

        int userSelection =  fileChooser.showOpenDialog(this);

        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave = fileChooser.getSelectedFile();
            String extension = fileToSave.getAbsolutePath().substring(fileToSave.getAbsolutePath().lastIndexOf(".") + 1, fileToSave.getAbsolutePath().length());
            if (!extension.equals("csv")){
                JOptionPane.showMessageDialog(null, "O formato nao corresponde a *.csv");
            }else{
                try {
                    File file = new File(fileToSave.getAbsolutePath());
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line = "";
                    String[] tempArr;

                    while((line = br.readLine()) != null){
                        tempArr = line.split(delimiter);

                        if(tempArr.length != 5){
                            JOptionPane.showMessageDialog(null, "A quantidade de campos para a prova é diferente de 5");
                        }
                        else{
                            //String nome, String pais, String genero, String dcontacto, String dataNascimento
                            Atleta atletaAAdicionar = new Atleta(Integer.parseInt(tempArr[0]), tempArr[1],tempArr[2],tempArr[3],Integer.parseInt(tempArr[4]), Data.parse(tempArr[5]));
                            DadosAplicacao.INSTANCE.addAtleta(atletaAAdicionar);
                        }
                    }
                    br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            preencherTabelaAtletas();
        }


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
        Object columnNames[] = {"Numero de Atleta","Nome", "País", "Género", "Data de nascimento", "Contacto", "Eliminar"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tabelaAtletas.setModel(model);

        for (Atleta atleta: DadosAplicacao.INSTANCE.getListaAtletas()) {
            model.addRow(new Object[]{atleta.getNumeroAtleta(),atleta.getNome(), atleta.getPais(), atleta.getGenero(), atleta.getDataNascimento().toString(), atleta.getContacto(), "Eliminar"});
        }

    }

}
