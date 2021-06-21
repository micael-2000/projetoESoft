package vista.Evento;

import modelo.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class EcraCriarEditarEvento extends JDialog {
    private JTextField nomeEvento;
    private JTextField dataInicio;
    private JTextField dataFim;
    private JTextField local;
    private JTextField pais;
    private JList listaProvasEvento;
    private JList listaProvasDisponiveis;
    DefaultListModel<String> modelProvasDisponives;
    DefaultListModel<String> modelProvasEvento;
    private JPanel painelCriarEditarEvento;
    private JButton guardarButton;
    private JButton cancelarButton;
    private int posicaoEvento;

    //E chamado este metodo quando for para editar
    public EcraCriarEditarEvento(Evento evento){
        this(true);
        this.posicaoEvento = DadosAplicacao.INSTANCE.getIndiceEvento(evento);
        nomeEvento.setText(evento.getNomeEvento());
        dataInicio.setText(evento.getDataInicio().toString());
        dataFim.setText(evento.getDataFim().toString());
        local.setText(evento.getLocal());
        pais.setText(evento.getPais());
        adicionarProvasNasListas(evento);

    }

    //E chamado na criacao do evento
    public EcraCriarEditarEvento(boolean metodoChamadoPelaEdicao){
        posicaoEvento = -1;

        if(metodoChamadoPelaEdicao == false){
            adicionarProvasNasListas(null);
        }
        listaProvasDisponiveis.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                gestaoListas(modelProvasDisponives, modelProvasEvento, listaProvasDisponiveis.getSelectedValue());
            }
        });

        listaProvasEvento.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                gestaoListas(modelProvasEvento, modelProvasDisponives, listaProvasEvento.getSelectedValue());
            }
        });

        guardarButton.addActionListener(this::btnGuardarActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarActionPerformed);

        setContentPane(painelCriarEditarEvento);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void gestaoListas(DefaultListModel modelSelectionHappen, DefaultListModel modelToAddElement, Object element){
        //se o elemento nao estiver na listaProvasEvento, entao adiciona-o e remove o que foi adicionado da listaProvasDisponiveis
        modelToAddElement.addElement(element);
        modelSelectionHappen.removeElement(element);
    }

    private void mostrarErro(String mensagem ){
        JOptionPane.showConfirmDialog(null, mensagem, "Erro", JOptionPane.DEFAULT_OPTION);
    }

    private void adicionarProvasNasListas(Evento evento) {
        modelProvasDisponives = new DefaultListModel<String>();
        listaProvasDisponiveis.setModel(modelProvasDisponives);
        modelProvasEvento = new DefaultListModel<String>();
        listaProvasEvento.setModel(modelProvasEvento);

        ArrayList<Integer> arrayIds = null;

        if (evento != null) {
            arrayIds = evento.getIdProvas();
        }

        //adiciona elementos a lista de provas disponiveis porque e a criacao do evento
        for (ProvaDadosPreDefinidos prova : DadosAplicacao.INSTANCE.getListaProvasDadosPreDefinidos()) {
            if (arrayIds != null && arrayIds.contains(prova.getId())) {
                modelProvasEvento.addElement(prova.getId() + " - " + prova.getNome());
            } else {
                modelProvasDisponives.addElement(prova.getId() + " - " + prova.getNome());
            }
        }
    }

    private void btnGuardarActionPerformed(ActionEvent e) {
        if(nomeEvento.getText().isEmpty()){
            mostrarErro("O campo \"Nome do Evento\" é obrigatório");
        }
        else{
            if (dataInicio.getText().isEmpty()){
                mostrarErro("O campo \"data inicio\" é obrigatório  ");
            }
            else{
                Data dataInicioVerificado = Data.parse(dataInicio.getText());

                if (dataInicioVerificado == null){
                    mostrarErro( "A data do inicio tem de estar no formato \"DD-MM-YYYY\"  ");
                }
                else{
                    if(!dataInicioVerificado.isValida()){
                        mostrarErro( "A data inicio é inválida");
                    }
                    else {
                        if (dataFim.getText().isEmpty()) {
                            mostrarErro("O campo \"data fim\" é obrigatório  ");
                        } else {
                            Data dataFimVerificado = Data.parse(dataFim.getText());

                            if (dataFimVerificado == null) {
                                mostrarErro("A data do fim tem de estar no formato \"DD-MM-YYYY\"");
                            } else {
                                if (!dataFimVerificado.isValida()) {
                                    mostrarErro( "A data fim é inválida");
                                } else {
                                    if(local.getText().isEmpty()){
                                        mostrarErro( "O campo \"Local\" é obrigatório");
                                    }
                                    else{
                                        if(pais.getText().isEmpty()){
                                            mostrarErro("O campo \"País\" é obrigatório  ");
                                        }
                                        else{
                                            ArrayList<Prova> listaProvasDoEvento = new ArrayList<>();
                                            for (int i = 0; i < modelProvasEvento.getSize(); i++) {
                                                if(modelProvasEvento.getElementAt(i) != null){
                                                    String[] tempArr = modelProvasEvento.getElementAt(i).split(" ");
                                                    int idProva = Integer.parseInt(tempArr[0]);
                                                    ProvaDadosPreDefinidos prova = DadosAplicacao.INSTANCE.getProvaDadosPreDefinidos(idProva);
                                                    listaProvasDoEvento.add(new Prova(prova));
                                                }
                                            }
                                            //editar evento
                                            if(posicaoEvento != -1){
                                                Evento evento = DadosAplicacao.INSTANCE.getEvento(posicaoEvento);
                                                evento.setNomeEvento(nomeEvento.getText());
                                                evento.setDataInicio(dataInicioVerificado);
                                                evento.setDataFim(dataFimVerificado);
                                                evento.setLocal(local.getText());
                                                evento.setPais(pais.getText());
                                                evento.setListaProvas(listaProvasDoEvento);
                                                JOptionPane.showMessageDialog(this,"Foi alterado o Evento");
                                            }
                                            else{ //criar evento
                                                Evento evento = new Evento(listaProvasDoEvento, dataInicioVerificado, dataFimVerificado, local.getText(), pais.getText(), nomeEvento.getText());
                                                DadosAplicacao.INSTANCE.addEvento(evento);
                                                JOptionPane.showMessageDialog(this,"Foi criado o Evento");
                                            }

                                            setVisible(false);
                                            new EcraEventos();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void btnCancelarActionPerformed(ActionEvent e){
        setVisible(false);
        new EcraEventos();
    }
}
