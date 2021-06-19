package vista.Prova;

import com.opencsv.CSVWriter;
import modelo.DadosAplicacao;
import modelo.Data;
import modelo.Evento;
import modelo.ProvaDadosPreDefinidos;
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
    DefaultTableModel model;

    public EcraProvas(){
        criarProvaButton.addActionListener(this::btnCriarProvaActionPerformed);
        importarProvasButton.addActionListener(this::btnImportarProvasActionPerformed);

        atualizarTabela();
        TableColumnModel tcm = tabelaProvasPreDefinidas.getColumnModel();
        tcm.getColumn(1).setPreferredWidth(300);

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
                        new EcraDetalhesEditarProva(DadosAplicacao.INSTANCE.getProvaDadosPreDefinidos(tabelaProvasPreDefinidas.getValueAt(row, 1).toString()), "Detalhes");
                    }
                    //Eliminar prova
                    else if(column == 3){
                        String nomeProva = tabelaProvasPreDefinidas.getValueAt(row, 1).toString();

                        int input = JOptionPane.showConfirmDialog(null, "Confirma a eliminação da Prova: " + nomeProva);

                        if(input == 0){
                            DadosAplicacao.INSTANCE.removeProvaDadosPreDefinidos(nomeProva);
                            JOptionPane.showMessageDialog(null,"Foi eliminado a Prova");
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
        new EcraDetalhesEditarProva(null, "Criar");
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

                line = br.readLine();
                tempArr = line.split(delimiter);

                if(tempArr.length != 5){
                    //mostrar erro
                }
                else{
                    //DataInicio
                    Data dataInicio = Data.parse(tempArr[0]);
                    if(dataInicio.isValida() == false){
                        //mostrar erro
                    }else{
                        //DataFim
                        Data dataFim = Data.parse(tempArr[1]);
                        if(dataFim.isValida() == false){
                            //mostrar erro
                        }
                        else{
                            //tempArr[2] - nomeEvento
                            //tempArr[3] - local
                            //tempArr[4] - pais
                            Evento evento = new Evento(null, dataInicio, dataFim, tempArr[2], tempArr[3], tempArr[4]);
                            DadosAplicacao.INSTANCE.addEvento(evento);
                            new EcraCriarEditarEvento(evento, DadosAplicacao.INSTANCE.getListaEventos().indexOf(evento));
                        }
                    }
                }

                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void btnExportarProvasActionPerformed(int posicaoEvento) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            //https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/

            File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".csv");

            try {
                FileWriter outputfile = new FileWriter(file);

                CSVWriter writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER,  CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

                Evento evento = DadosAplicacao.INSTANCE.getEvento(posicaoEvento);
                //DataInicio, DataFim, local, pais, nomeEvento
                String[] data = {evento.getDataInicio().toString(), evento.getDataFim().toString(), evento.getLocal(), evento.getPais(), evento.getNomeEvento()};
                writer.writeNext(data);

                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
