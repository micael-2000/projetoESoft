package vista.Prova;

import modelo.DadosAplicacao;
import modelo.Evento;
import modelo.Genero;
import modelo.ProvaPreDefinida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class EcraCriarEditarDetalhesProva extends JDialog{
    private JPanel painelDetalhesEditarProva;
    private JLabel tipoEcra;
    private JTextField nome;
    private JComboBox genero;
    private JTextField categoria;
    private JTextField local;
    private JTextField tipoProva;
    private JTextArea recordesMundialAtual;
    private JTextArea recordistas;
    private JTextArea notas;
    private JTextArea eventosEmQueDecorreu;
    private JButton editarButton;
    private JButton voltarButton;
    private JLabel id;
    private JButton guardarButton;
    private JLabel recordesMundialAtualLabel;
    private JLabel recordistasLabel;
    private JLabel notasLabel;
    private JLabel eventosEmQueDecorreuLabel;

    public EcraCriarEditarDetalhesProva(ProvaPreDefinida prova, String tipoEcra){
        genero.addItem(Genero.MASCULINO);
        genero.addItem(Genero.FEMININO);

        //Criar Prova
        if(tipoEcra.equals("Criar")){
            this.tipoEcra.setText("Criar");
            id.setText(Integer.toString(DadosAplicacao.INSTANCE.getSizeProvasPreDefinidas() + 1));
            editarButton.setVisible(false);
            recordesEventosVisibilidade(false);
        }
        //Detalhes Prova
        else if(tipoEcra.equals("Detalhes")){
            this.tipoEcra.setText("Detalhes");
            carregarDados(prova);
            editavel(false);
            guardarButton.setVisible(false);
        }

        guardarButton.addActionListener(this::btnGuardarActionPerformed);
        editarButton.addActionListener(this::btnEditarActionPerformed);
        voltarButton.addActionListener(this::btnVoltarActionPerformed);
        setContentPane(painelDetalhesEditarProva);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void carregarDados(ProvaPreDefinida prova){
        if (eventosEmQueDecorreu.getLineCount() !=0) {
            eventosEmQueDecorreu.setText("");
        }
        id.setText(Integer.toString(prova.getId()));
        nome.setText(prova.getNome());
        genero.setSelectedItem(prova.getGenero());
        categoria.setText(prova.getCategoria());
        local.setText(prova.getLocal());
        tipoProva.setText(prova.getTipoProva());

        for (String record : prova.getRecordesMundial()){
            recordesMundialAtual.append(record);
            recordesMundialAtual.append("\n");
        }

        for (String nome : prova.getRecordistas()){
            recordistas.append(nome);
            recordistas.append("\n");
        }

        boolean isRepeated = false;

        for (String nome : prova.getEventosEmQueDecorreu()) {

            eventosEmQueDecorreu.append(nome);

            eventosEmQueDecorreu.append("\n");

        }
        System.out.println();
        notas.setText(prova.getNotas());
    }

    public void editavel(boolean bool){
        nome.setEditable(bool);
        genero.setEnabled(bool);
        categoria.setEditable(bool);
        local.setEditable(bool);
        tipoProva.setEditable(bool);
        recordesMundialAtual.setEditable(bool);
        recordistas.setEditable(bool);
        notas.setEditable(bool);
        eventosEmQueDecorreu.setEditable(bool);
    }

    public void recordesEventosVisibilidade(boolean bool){
        recordesMundialAtualLabel.setVisible(bool);
        recordistasLabel.setVisible(bool);
        eventosEmQueDecorreuLabel.setVisible(bool);
        recordistas.setVisible(bool);
        recordesMundialAtual.setVisible(bool);
        eventosEmQueDecorreu.setVisible(bool);
    }

    private void btnEditarActionPerformed(ActionEvent e) {
        this.tipoEcra.setText("Editar");
        editavel(true);

        guardarButton.setVisible(true);
        editarButton.setVisible(false);
    }

    private void btnVoltarActionPerformed(ActionEvent e) {
      setVisible(false);
      eventosEmQueDecorreu.setText("");
      getParent().setVisible(true);
    }

    private void mostrarErro(String mensagem ){
        JOptionPane.showConfirmDialog(null, mensagem, "Erro", JOptionPane.DEFAULT_OPTION);
    }

    private void btnGuardarActionPerformed(ActionEvent e) {
        ProvaPreDefinida prova = null;
        if(nome.getText().isEmpty()){
            mostrarErro("O campo \"Nome\" é obrigatório");
        }
        else{
            if (categoria.getText().isEmpty()) {
                mostrarErro("O campo \"Categoria\" é obrigatório");
            } else {
                if(local.getText().isEmpty()){
                    mostrarErro("O campo \"Local\" é obrigatório");
                }
                else{
                    if(tipoProva.getText().isEmpty()){
                        mostrarErro("O campo \"Tipo Prova\" é obrigatório");
                    }
                    else{
                        if(tipoEcra.getText().equals("Criar")){
                            prova = new ProvaPreDefinida(nome.getText(), categoria.getText(), local.getText(), tipoProva.getText(), Genero.valueOf(genero.getSelectedItem().toString()), notas.getText(), -1,20.0);
                            DadosAplicacao.INSTANCE.addProvaPreDefinida(prova);

                            JOptionPane.showMessageDialog(this,"Foi criado a Prova");
                            recordesEventosVisibilidade(true);
                        } else if(tipoEcra.getText().equals("Editar")){
                            prova = DadosAplicacao.INSTANCE.getProvaPreDefinidas(Integer.parseInt(id.getText()));
                            prova.setNome(nome.getText());
                            prova.setNotas(notas.getText());
                            prova.setLocal(local.getText());
                            prova.setGenero(Genero.valueOf(genero.getSelectedItem().toString()));
                            prova.setCategoria(categoria.getText());
                            //prova.setRecordistas(recordistas.getText());
                            //prova.setRecordesMundialAtual(recordesMundialAtual.getText());

                            JOptionPane.showMessageDialog(this,"Prova editada com sucesso");
                        }

                        editavel(false);
                        tipoEcra.setText("Detalhes");
                        editarButton.setVisible(true);
                        guardarButton.setVisible(false);
                    }
                }
            }
        }
    }
}
