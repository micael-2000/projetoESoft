package vista.Atleta;

import modelo.Atleta;
import modelo.DadosAplicacao;
import modelo.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EcraCriarAtleta extends JDialog{
    private JPanel painelPrincipal;
    private JTextField txtNome;
    private JTextField txtPais;
    private JTextField txtDataNascimento;
    private JTextField txtContacto;
    private JLabel lblNome;
    private JButton btnCriar;
    private JButton btnSair;
    private JComboBox comboBoxGenero;

    public EcraCriarAtleta(){

        comboBoxGenero.setModel(new DefaultComboBoxModel(new String[]{"Masculino", "Feminino"}));

        btnCriar.addActionListener(this::btnCriarActionPerformed);
        btnSair.addActionListener(this::btnSairActionPerformed);

        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
    }

    private void btnCriarActionPerformed(ActionEvent actionEvent) {

        String nome = txtNome.getText();
        if(nome.equals("")) {
            mostrarErro(1);
            return;
        }
        if(nome == null){
            mostrarErro(2);
            return;
        }
        String pais = txtPais.getText();
        if(pais.equals("")) {
            mostrarErro(3);
            return;
        }
        if(pais == null){
            mostrarErro(4);
            return;
        }
        String genero = comboBoxGenero.getSelectedItem().toString();
        if(genero == null){
            mostrarErro(5);
            return;
        }

        String txtData = txtDataNascimento.getText();
        if(txtData.equals("")) {
            mostrarErro(6);
            return;
        }
        Data data= Data.parse(txtData);

        if(data == null){
            mostrarErro(7);
            return;
        }
        if(data.isValida() == false){
            mostrarErro(8);
            return;
        }

        String txtContacto = this.txtContacto.getText();
        if(txtContacto.equals("")) {
            mostrarErro(9);
            return;
        }

        int contacto;
        try{
            contacto = Integer.parseInt(txtContacto);
        }catch (final NumberFormatException e){
            contacto = -1;
        }

        if(contacto < 0) {
            mostrarErro(10);
            return;
        }

        Atleta atleta = new Atleta(nome,pais,genero,contacto,data);
        DadosAplicacao.INSTANCE.addAtleta(atleta);
        setVisible(false);

    }

    private void btnSairActionPerformed(ActionEvent actionEvent) {
        setVisible(false);
    }

    private void mostrarErro(int codigo) {

        switch (codigo){
            case 1 :
                JOptionPane.showMessageDialog(null, "O Campo \"Nome\" é mandatório.");
                break;
            case 2 :
                JOptionPane.showMessageDialog(null, "O nome introduzido é inválido.");
                break;
            case 3 :
                JOptionPane.showMessageDialog(null, "O Campo \"País\" é mandatório.");
                break;
            case 4 :
                JOptionPane.showMessageDialog(null, "O país introduzido é inválido.");
                break;
            case 5 :
                JOptionPane.showMessageDialog(null, "O campo género não está selecionado.");
                break;
            case 6 :
                JOptionPane.showMessageDialog(null, "O Campo \"Data de nascimento\" é mandatório.");
                break;
            case 7 :
                JOptionPane.showMessageDialog(null, "A data de nascimento não está no formato \"DD-MM-YYYY\".");
                break;
            case 8 :
                JOptionPane.showMessageDialog(null, "A data de nascimento é inválida.");
                break;
            case 9 :
                JOptionPane.showMessageDialog(null, "O contatcto não está preenchido.");
                break;
            case 10 :
                JOptionPane.showMessageDialog(null, "O contatcto é inválido");
                break;
        }
    }

}
