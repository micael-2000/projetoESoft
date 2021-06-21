package vista.Prova;

import com.opencsv.CSVWriter;
import modelo.*;
import vista.Evento.EcraCriarEditarEvento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class EcraProvas extends JFrame{
    private JPanel painelProvas;
    private JButton exportarProvasButton;
    private JButton criarProvaButton;
    private JButton voltarButton;
    private JTable tabelaProvasPreDefinidas;
    private JButton importarProvasButton;
    private JScrollPane scrollPane;
    DefaultTableModel model;

    public EcraProvas(){
        criarProvaButton.addActionListener(this::btnCriarProvaActionPerformed);
        importarProvasButton.addActionListener(this::btnImportarProvasActionPerformed);
        exportarProvasButton.addActionListener(this::btnExportarProvasActionPerformed);
        voltarButton.addActionListener(this::btnVoltarActionPerformed);

        atualizarTabela();
        TableColumnModel tcm = tabelaProvasPreDefinidas.getColumnModel();
        tcm.getColumn(1).setPreferredWidth(300);
        scrollPane.setViewportView(tabelaProvasPreDefinidas);

        //bloqueia o user de editar as celulas
        tabelaProvasPreDefinidas.setDefaultEditor(Object.class, null);

        tabelaProvasPreDefinidas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    //Detalhes / Editar prova
                    if(column == 1){
                        new EcraCriarEditarDetalhesProva(DadosAplicacao.INSTANCE.getProvaDadosPreDefinidos(Integer.parseInt(tabelaProvasPreDefinidas.getValueAt(row, 0).toString())), "Detalhes");
                    }
                    //Eliminar prova
                    else if(column == 3){
                        int idProva = Integer.parseInt(tabelaProvasPreDefinidas.getValueAt(row, 0).toString());

                        int input = JOptionPane.showConfirmDialog(null, "Confirma a eliminação da Prova: " + tabelaProvasPreDefinidas.getValueAt(row, 1).toString());

                        if(input == 0){
                            DadosAplicacao.INSTANCE.removeProvaDadosPreDefinidos(idProva);
                            JOptionPane.showMessageDialog(null,"Prova eliminada com sucesso");
                            atualizarTabela();
                        }
                    }
                }
            }
        });

        setContentPane(painelProvas);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void atualizarTabela(){
        Object columnNames[] = {"ID", "Nome Prova", "Categoria", ""};

        model = new DefaultTableModel(null, columnNames);
        tabelaProvasPreDefinidas.setModel(model);

        for (ProvaDadosPreDefinidos provaDadosPreDefinidos: DadosAplicacao.INSTANCE.getListaProvasDadosPreDefinidos()) {
            model.addRow(new Object[]{provaDadosPreDefinidos.getId(), provaDadosPreDefinidos.getNome(), provaDadosPreDefinidos.getCategoria(), "Eliminar"});
        }
    }

    private void btnCriarProvaActionPerformed(ActionEvent e) {
        new EcraCriarEditarDetalhesProva(null, "Criar");
    }

    private void btnImportarProvasActionPerformed(ActionEvent e) {
        String delimiter = ";";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o ficheiro");

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                File file = new File(fileToSave.getAbsolutePath());
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                String[] tempArr;

                while((line = br.readLine()) != null){
                    tempArr = line.split(delimiter);

                    if(tempArr.length != 6 && tempArr.length != 5){
                        //mostrar erro
                    }
                    else{
                        //String nome, String categoria, String local, String tipoProva, String genero, String notas
                        ProvaDadosPreDefinidos prova = new ProvaDadosPreDefinidos(tempArr[0], tempArr[1], tempArr[2], tempArr[3], Genero.valueOf(tempArr[4]), tempArr[5], -1);
                        DadosAplicacao.INSTANCE.addProva(prova);
                    }
                }

                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        atualizarTabela();
    }

    public void btnExportarProvasActionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            //https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/

            File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".csv");

            try {
                FileWriter outputfile = new FileWriter(file);

                CSVWriter writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER,  CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

                for (ProvaDadosPreDefinidos prova : DadosAplicacao.INSTANCE.getListaProvasDadosPreDefinidos()) {
                    //String nome, String categoria, String local, String tipoProva, String genero, String notas
                    String[] data = {prova.getNome(), prova.getCategoria(), prova.getLocal(), prova.getTipoProva(), prova.getGenero().toString(), prova.getNotas()};
                    writer.writeNext(data);
                }
                writer.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void btnVoltarActionPerformed(ActionEvent e){
        setVisible(false);
    }
}
