package vista.Atleta;

import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

        this.preencherTabelaInscricoes();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

    }



    private void btnSairActionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    private void btnImportarActionPerformed(ActionEvent actionEvent) {
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

                        if(tempArr.length != 4){
                            JOptionPane.showMessageDialog(null, "A quantidade de campos para a prova é diferente de 4");
                        }
                        else{
                            Atleta atletaAAdicionar = DadosAplicacao.INSTANCE.getAtletaByNumeroAtleta(Integer.parseInt(tempArr[0]));

                            if(atletaAAdicionar == null){
                                JOptionPane.showMessageDialog(this,"Não existe um atleta com o numero");
                                continue;
                            }

                            Prova prova = DadosAplicacao.INSTANCE.getProvaByName(tempArr[1]);
                            double marcaMinima = Double.parseDouble(tempArr[2]);

                            if(prova.getMarcaMinima() > marcaMinima){
                                JOptionPane.showMessageDialog(this,"A marca não atinge os minimos para a prova");
                                continue;
                            }
                            DadosAplicacao.INSTANCE.addInscricao(new Inscricao(atletaAAdicionar,prova,marcaMinima,tempArr[3]));
                        }
                    }
                    br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            preencherTabelaInscricoes();
        }
    }

    private void btnEliminarActionPerformed(int row) {
        int input = JOptionPane.showConfirmDialog(null, "Tem a certaza que deseja remover a Inscrição?",null,JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION){
            DadosAplicacao.INSTANCE.removeInscricao(row);
            preencherTabelaInscricoes();
            return;
        }
        return;
    }

    private void preencherTabelaInscricoes() {
        Object columnNames[] = {"Nome da Prova", "Nome do atleta", "Marca lcançada", "País","Eliminar"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tabelaInscricoes.setModel(model);

        for (Inscricao inscricao: DadosAplicacao.INSTANCE.getListaInscricoes()) {
            model.addRow(new Object[]{inscricao.getNomeProva(), inscricao.getAtleta().getNome(), inscricao.getMarcaAlcancada(), inscricao.getPais(), "Eliminar"});
        }
    }

}


