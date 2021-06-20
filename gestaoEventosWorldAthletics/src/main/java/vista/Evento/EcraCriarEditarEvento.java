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
        this();
        this.posicaoEvento = DadosAplicacao.INSTANCE.getIndiceEvento(evento);
        nomeEvento.setText(evento.getNomeEvento());
        dataInicio.setText(evento.getDataInicio().toString());
        dataFim.setText(evento.getDataFim().toString());
        local.setText(evento.getLocal());
        pais.setText(evento.getPais());
    }

    //E chamado na criacao do evento
    public EcraCriarEditarEvento(){
        posicaoEvento = -1;

        modelProvasDisponives = new DefaultListModel<String>();
        listaProvasDisponiveis.setModel(modelProvasDisponives);

        modelProvasEvento = new DefaultListModel<String>();
        listaProvasEvento.setModel(modelProvasEvento);

        //adiciona elementos a lista de provas disponiveis porque e a criacao do evento
        for (ProvaDadosPreDefinidos prova : DadosAplicacao.INSTANCE.getListaProvasDadosPreDefinidos()) {
            modelProvasDisponives.addElement(prova.getId() + " - " + prova.getNome());
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

    private void gestaoListas(DefaultListModel modelSelectionHappen, DefaultListModel modelToChange, Object element){
        //se o elemento nao estiver na listaProvasEvento, entao adiciona-o e remove o que foi adicionado da listaProvasDisponiveis
        modelToChange.addElement(element);
        modelSelectionHappen.removeElement(element);
    }

    private void btnGuardarActionPerformed(ActionEvent e) {
        if(nomeEvento.getText().isEmpty()){
            Erro.mostrarErro("Evento", 1);
        }
        else{
            if (dataInicio.getText().isEmpty()){
                Erro.mostrarErro("Evento", 2);
            }
            else{
                Data dataInicioVerificado = Data.parse(dataInicio.getText());

                if (dataInicioVerificado == null){
                    Erro.mostrarErro("Evento", 3);
                }
                else{
                    if(!dataInicioVerificado.isValida()){
                        Erro.mostrarErro("Evento", 4);
                    }
                    else {
                        if (dataFim.getText().isEmpty()) {
                            Erro.mostrarErro("Evento", 5);
                        } else {
                            Data dataFimVerificado = Data.parse(dataFim.getText());

                            if (dataFimVerificado == null) {
                                Erro.mostrarErro("Evento", 6);
                            } else {
                                if (!dataFimVerificado.isValida()) {
                                    Erro.mostrarErro("Evento", 7);
                                } else {
                                    if(local.getText().isEmpty()){
                                        Erro.mostrarErro("Evento", 8);
                                    }
                                    else{
                                        if(pais.getText().isEmpty()){
                                            Erro.mostrarErro("Evento", 9);
                                        }
                                        else{
                                            ArrayList<Prova> listaProvasDoEvento = new ArrayList<>();
                                            for (int i = 0; i < modelProvasEvento.getSize(); i++) {
                                                Scanner scanner = new Scanner(modelProvasEvento.getElementAt(i));
                                                int idProva = scanner.nextInt();
                                                ProvaDadosPreDefinidos prova = DadosAplicacao.INSTANCE.getProvaDadosPreDefinidos(idProva);
                                                if(prova != null) {
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
    }
}
