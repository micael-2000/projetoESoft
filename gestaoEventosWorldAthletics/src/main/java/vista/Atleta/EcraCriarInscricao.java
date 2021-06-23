package vista.Atleta;

import modelo.DadosAplicacao;
import modelo.Inscricao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public EcraCriarInscricao() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnCriar);

        btnCriar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCriarActionPerformed();
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

    private void btnCriarActionPerformed() {
        // add your code here
        //TODO
        Inscricao inscricao = new Inscricao("100 metros", (float) 16.7,"portugal");
        //inscricao.setAtleta();

        DadosAplicacao.INSTANCE.addInscricao(inscricao);
        dispose();
    }

    private void btnSairActionPerformed() {
        // add your code here if necessary

        dispose();
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
