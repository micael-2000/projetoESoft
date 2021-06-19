package vista.Evento;

import com.opencsv.CSVWriter;
import modelo.*;
import vista.Evento.EcraCriarEditarEvento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.*;

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
        importarEventoButton.addActionListener(this::btnImportarEventoActionPerformed);

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

                    //Editar evento
                    if(column == 4){
                        new EcraCriarEditarEvento(DadosAplicacao.INSTANCE.getListaEventos().get(row), row);
                    }
                    //Eliminar evento
                    else if(column == 6){
                        int input = JOptionPane.showConfirmDialog(null, "Confirma a eliminação do evento: " + DadosAplicacao.INSTANCE.getEvento(row).getNomeEvento());

                        if(input == 0){
                            DadosAplicacao.INSTANCE.removeEvento(row);
                            JOptionPane.showMessageDialog(null,"Foi eliminado o Evento");
                            atualizarTabela();
                        }
                        //Exportar evento
                    } else if(column == 5){
                        btnExportarEventoActionPerformed(row);
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

    private void btnImportarEventoActionPerformed(ActionEvent e) {
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

    private void atualizarTabela(){
        Object columnNames[] = {"Data", "Evento", "Cidade", "País", "", "", ""};

        model = new DefaultTableModel(null, columnNames);
        tabelaEventos.setModel(model);

        for (Evento evento: DadosAplicacao.INSTANCE.getListaEventos()) {
            model.addRow(new Object[]{evento.getDataInicio() + "-" + evento.getDataFim(), evento.getNomeEvento(), evento.getLocal(), evento.getPais(), "Editar", "Exportar Dados", "Eliminar"});
        }
    }

    public void btnExportarEventoActionPerformed(int posicaoEvento) {
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