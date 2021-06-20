package vista.Prova;

import modelo.DadosAplicacao;
import modelo.ProvaDadosPreDefinidos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EcraCriarEditarDetalhesProva extends JDialog{
    private JPanel painelDetalhesEditarProva;
    private JLabel tipoEcra;
    private JTextField nome;
    private JTextField genero;
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

    public EcraCriarEditarDetalhesProva(ProvaDadosPreDefinidos prova, String tipoEcra){
        //Criar Prova
        if(tipoEcra.equals("Criar")){
            this.tipoEcra.setText("Criar");
            id.setText(Integer.toString(DadosAplicacao.INSTANCE.getSizeProvasPreDefinidos() + 1));
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

    public void carregarDados(ProvaDadosPreDefinidos prova){
        id.setText(Integer.toString(prova.getId()));
        nome.setText(prova.getNome());
        genero.setText(prova.getGenero());
        categoria.setText(prova.getCategoria());
        local.setText(prova.getLocal());
        tipoProva.setText(prova.getTipoProva());
        recordesMundialAtual.setText(prova.getRecordesMundialAtual());
        recordistas.setText(prova.getRecordistas());
        eventosEmQueDecorreu.setText(prova.getEventosEmQueDecorreu());
        notas.setText(prova.getNotas());
    }

    public void editavel(boolean bool){
        nome.setEditable(bool);
        genero.setEditable(bool);
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
    }

    private void btnGuardarActionPerformed(ActionEvent e) {
        ProvaDadosPreDefinidos prova = null;
        if(nome.getText().isEmpty()){
            // mostrar erro
        }
        else{
            if (genero.getText().isEmpty()){
                // mostrar erro
            }
            else{
                if (categoria.getText().isEmpty()) {
                    // mostrar erro
                } else {
                    if(local.getText().isEmpty()){
                        // mostrar erro
                    }
                    else{
                        if(tipoProva.getText().isEmpty()){
                            // mostrar erro
                        }
                        else{
                            if(tipoEcra.getText().equals("Criar")){
                                prova = new ProvaDadosPreDefinidos(nome.getText(), genero.getText(), categoria.getText(), local.getText(), tipoProva.getText(), notas.getText());
                                DadosAplicacao.INSTANCE.addProva(prova);

                                JOptionPane.showMessageDialog(this,"Foi criado a Prova");
                                recordesEventosVisibilidade(true);
                            } else if(tipoEcra.getText().equals("Editar")){
                                prova = DadosAplicacao.INSTANCE.getProvaDadosPreDefinidos(Integer.parseInt(id.getText()));
                                prova.setNome(nome.getText());
                                prova.setNotas(notas.getText());
                                prova.setLocal(local.getText());
                                prova.setGenero(genero.getText());
                                prova.setCategoria(categoria.getText());
                                prova.setEventosEmQueDecorreu(eventosEmQueDecorreu.getText());
                                prova.setRecordistas(recordistas.getText());
                                prova.setRecordesMundialAtual(recordesMundialAtual.getText());

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
}
