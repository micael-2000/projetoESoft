package vista.Prova;

import modelo.*;
import vista.Evento.EcraEventos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EcraCriarProva extends JDialog {
    private JPanel painelCriarProva;
    private JLabel id;
    private JTextField nome;
    private JTextField genero;
    private JTextField categoria;
    private JTextField local;
    private JTextField tipoProva;
    private JTextField notas;
    private JButton criarButton;
    private JButton cancelarButton;

    public EcraCriarProva(){
        id.setText(Integer.toString(DadosAplicacao.INSTANCE.getSizeProvasPreDefinidos() + 1));
        criarButton.addActionListener(this::btnCriarActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarActionPerformed);

        setContentPane(painelCriarProva);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void btnCriarActionPerformed(ActionEvent e) {
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
                            ProvaDadosPreDefinidos prova = new ProvaDadosPreDefinidos(nome.getText(), genero.getText(), categoria.getText(), local.getText(), tipoProva.getText(), notas.getText());
                            DadosAplicacao.INSTANCE.addProva(prova);

                            JOptionPane.showMessageDialog(this,"Foi criado a Prova");
                            setVisible(false);
                            this.getParent().setVisible(false);
                            new EcraProvas();
                        }
                    }
                }
            }
        }
    }

    private void btnCancelarActionPerformed(ActionEvent e){
        setVisible(false);
    }
}
