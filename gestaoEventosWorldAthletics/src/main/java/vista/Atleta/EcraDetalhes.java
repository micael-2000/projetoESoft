package vista.Atleta;

import modelo.Atleta;
import modelo.DadosAplicacao;
import modelo.Data;
import modelo.Inscricao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EcraDetalhes extends JDialog {
    private JPanel contentPane;
    private JButton btnGuardar;
    private JButton btnSair;
    private JLabel lblDetalhes;
    private JButton btnRecords;
    private JTabbedPane tabbedPaneDetalhes;
    private JLabel lblNome;
    private JTextField txtNome;
    private JPanel panelInformacoes;
    private JPanel panelInscricoes;
    private JTextField txtPais;
    private JLabel lblPais;
    private JLabel lblGenero;
    private JTextField txtContacto;
    private JLabel lblContacto;
    private JTextField txtDataNascimento;
    private JLabel lblDataNascimento;
    private JTable tableHistorico;
    private JScrollPane scrollPaneHistorico;
    private JComboBox comboBoxGenero;
    private JTable tabelaInscricoes;
    private JButton btnInscreverNumaProva;
    private JButton btnSair2;
    private JScrollPane scrollPaneInscricoes;

    public EcraDetalhes(Atleta atleta) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformed(atleta);
            }
        });

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSairActionPerformed();
            }
        });

        btnSair2.addActionListener(new ActionListener() {
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

        btnRecords.addActionListener(this::btnRecordsActionPerformed);
        //btnInscreverNumaProva.addActionListener(this::btnInscreverNumaProvaActionPerformed);

        btnInscreverNumaProva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnInscreverNumaProvaActionPerformed(atleta);
            }
        });

        comboBoxGenero.setModel(new DefaultComboBoxModel(new String[]{"Masculino", "Feminino"}));

        tableHistorico.setDefaultEditor(Object.class, null);
        scrollPaneHistorico.setViewportView(tableHistorico);
        //TODO

        tabelaInscricoes.setDefaultEditor(Object.class, null);
        scrollPaneInscricoes.setViewportView(tabelaInscricoes);
        tabelaInscricoes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    //eliminar inscricao
                    if(column == 3) {
                        btnEliminarActionPerformed(row);
                    }

                }
            }
        });


        prencherDadosAtleta(atleta);
        preencherHistoricoAtleta(atleta);
        preencherInscricoes();

        pack();
    }

    private void btnGuardarActionPerformed(Atleta atleta) {

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
        if(!data.isValida()){
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

        atleta.setNome(nome);
        atleta.setPais(pais);
        atleta.setGenero(genero);
        atleta.setDataNascimento(data);
        atleta.setContacto(contacto);

        setVisible(false);
    }

    private void btnSairActionPerformed() {
        // add your code here if necessary
        setVisible(false);
    }

    private void btnRecordsActionPerformed(ActionEvent actionEvent) {
        //TODO
    }

    private void btnInscreverNumaProvaActionPerformed(Atleta atleta) {
        new EcraCriarInscricao(atleta).setVisible(true);
        preencherInscricoes();
    }

    private void btnEliminarActionPerformed(int row) {
        int input = JOptionPane.showConfirmDialog(null, "Tem a certaza que deseja remover a inscrição?",null,JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION){
            DadosAplicacao.INSTANCE.removeInscricao(row);
            preencherInscricoes();
            return;
        }
        return;
    }

    private void prencherDadosAtleta(Atleta atleta) {
        txtNome.setText(atleta.getNome());
        txtPais.setText(atleta.getPais());
        if(atleta.getGenero().equals("Masculino")){
            comboBoxGenero.setSelectedIndex(0);
        }
        if(atleta.getGenero().equals("Feminino")){
            comboBoxGenero.setSelectedIndex(1);
        }
        txtContacto.setText(Integer.toString(atleta.getContacto()));
        txtDataNascimento.setText(atleta.getDataNascimento().toString());
    }

    private void preencherHistoricoAtleta(Atleta atleta) {

        Object columnNames[] = {"ID", "Prova", "Resultado", "Evento", "Posicao"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tableHistorico.setModel(model);

        //preencher tabelado com resultados
        /*
        for () {
            //model.addRow(new Object[]{inscricao.getNomeProva(), inscricao.getMarcaAlcancada(), inscricao.getPais(), "Eliminar"});
        }
        */

    }

    private void preencherInscricoes() {
        Object columnNames[] = {"Nome da Prova", "Marca alcancada", "Pais", "Eliminar"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tabelaInscricoes.setModel(model);

        for (Inscricao inscricao: DadosAplicacao.INSTANCE.getListaInscricoes()) {
            model.addRow(new Object[]{inscricao.getNomeProva(), inscricao.getMarcaAlcancada(), inscricao.getPais(), "Eliminar"});
        }
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
                JOptionPane.showMessageDialog(null, "A data de nascimento não está no formato \"DD/MM/YYYY\".");
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
