package vista.Atleta;

import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EcraCriarInscricao extends JDialog {
    private JPanel contentPane;
    private JButton btnCriar;
    private JButton btnSair;
    private JTextField txtNomeDaProva;
    private JTextField txtMarcaAlcancada;
    private JTextField txtPais;
    private JLabel lblNomeDaProva;
    private JLabel lblMarcaAlcancada;
    private JLabel lblPais;

    public EcraCriarInscricao(Atleta atleta) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnCriar);

        btnCriar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCriarActionPerformed(atleta);
            }
        });

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSairActionPerformed();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                btnSairActionPerformed();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSairActionPerformed();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    private void btnCriarActionPerformed(Atleta atleta) {
        // add your code here

        String nome = txtNomeDaProva.getText();
        if(nome.equals("")) {
            mostrarErro(1);
            return;
        }

        if(nome == null){
            mostrarErro(2);
            return;
        }

        ArrayList<Evento> listaEventosAux = DadosAplicacao.INSTANCE.getListaEventos();
        ArrayList<Prova>  listaProvasAux = null;
        String txtNomeDaProvaAux = txtNomeDaProva.getText();
        boolean auxiliar = false;
        Prova provaInscricao = null;

        for (Evento evento: listaEventosAux) {
            listaProvasAux = evento.getListaProvas();

            for (Prova prova: listaProvasAux) {

                if(prova.getNome().equals(txtNomeDaProvaAux)){
                    provaInscricao = prova;
                    auxiliar = true;
                    break;
                }
            }
        }

        if(!auxiliar){
            mostrarErro(3);
            return;
        }

        String txtMarcaalcancada = txtMarcaAlcancada.getText();

        if(txtMarcaalcancada.equals("")){
            mostrarErro(4);
            return;
        }


        String pais = txtPais.getText();
        if(pais.equals("")) {
            mostrarErro(6);
            return;
        }

        if(pais == null){
            mostrarErro(7);
            return;
        }

        double marca = 0;
        try{
            marca = Double.parseDouble(txtMarcaalcancada);
        }catch (final NumberFormatException e){
            marca = -1;
        }
        boolean provaEValida= isInscricaoValida(marca, provaInscricao);

        if(!provaEValida){
            mostrarErro(8);
            return;
        }

        Inscricao inscricao = new Inscricao(atleta,provaInscricao, marca,pais);
        DadosAplicacao.INSTANCE.addInscricao(inscricao);

        setVisible(false);
    }



    private void btnSairActionPerformed() {
        // add your code here if necessary

        setVisible(false);
    }

    private boolean isInscricaoValida(Double marcaAlcancada, Prova prova) {
        System.out.println("Marac alcancada: " + marcaAlcancada.toString() + " Prova: " +  prova.getMarcaMinima());

        if(marcaAlcancada  < prova.getMarcaMinima()){
            return false;
        }
        return true;
    }

    private void mostrarErro(int codigo) {

        switch (codigo){
            case 1 :
                JOptionPane.showMessageDialog(null, "O Campo \"Nome da Prova\" é mandatório.");
                break;
            case 2 :
                JOptionPane.showMessageDialog(null, "O nome introduzido é inválido.");
                break;
            case 3 :
                JOptionPane.showMessageDialog(null, "Não existe uma prova com o nome indicado.");
                break;
            case 4 :
                JOptionPane.showMessageDialog(null, "O campo \"Marca alcançada\" é mandatório");
                break;
            case 5 :
                JOptionPane.showMessageDialog(null, "A marca alcançada não é válida. ");
                break;
            case 6 :
                JOptionPane.showMessageDialog(null, "O campo \"País\" é Mandatório.");
                break;
            case 7 :
                JOptionPane.showMessageDialog(null, "O país não é válido.");
                break;
            case 8 :
                JOptionPane.showMessageDialog(null, "A marca alcançada pelo atleta não atinge os mínimos definidos para a prova.");
                break;
        }
    }
}
