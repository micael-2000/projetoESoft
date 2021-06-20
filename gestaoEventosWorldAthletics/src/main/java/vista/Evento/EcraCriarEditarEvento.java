package vista.Evento;

import modelo.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
    public EcraCriarEditarEvento(Evento evento, int posicaoEvento){
        this();
        this.posicaoEvento = posicaoEvento;
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
            // mostrar erro
        }
        else{
            if (dataInicio.getText().isEmpty()){
                // mostrar erro
            }
            else{
                Data dataInicioVerificado = Data.parse(dataInicio.getText());

                if (dataInicioVerificado == null){
                    //mostrar erro
                }
                else{
                    if(!dataInicioVerificado.isValida()){
                        //mostrar erro
                    }
                    else {
                        if (dataFim.getText().isEmpty()) {
                            // mostrar erro
                        } else {
                            Data dataFimVerificado = Data.parse(dataFim.getText());

                            if (dataFimVerificado == null) {
                                //mostrar erro
                            } else {
                                if (!dataFimVerificado.isValida()) {
                                    //mostrar erro
                                } else {
                                    if(local.getText().isEmpty()){
                                        // mostrar erro
                                    }
                                    else{
                                        if(pais.getText().isEmpty()){
                                            // mostrar erro
                                        }
                                        else{
                                            ArrayList<Prova> listaProvasDoEvento = new ArrayList<>();
                                            for (int i = 0; i < modelProvasEvento.getSize(); i++) {
                                                int idProva = Integer.parseInt(modelProvasEvento.getElementAt(i).substring(0, 2));
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
                                            this.getParent().setVisible(false);
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
}
