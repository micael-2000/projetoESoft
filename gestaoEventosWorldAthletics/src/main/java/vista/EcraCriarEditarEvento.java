package vista;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

    public EcraCriarEditarEvento(){
        modelProvasDisponives = new DefaultListModel<String>();
        listaProvasDisponiveis.setModel(modelProvasDisponives);

        modelProvasEvento = new DefaultListModel<String>();
        listaProvasEvento.setModel(modelProvasEvento);

        //adiciona elementos a lista de provas disponiveis porque e a criacao do evento
        String[] paises = {"Brasil", "Tuga", "China", "Nao"};
        for (String pais : paises) {
            modelProvasDisponives.addElement(pais);
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

    public static void main(String[] args) {
        new EcraCriarEditarEvento();
    }

}
