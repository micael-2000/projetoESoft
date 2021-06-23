package vista.Evento.DetalhesEvento;

import modelo.Evento;
import modelo.Prova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EcraInscritosEvento  extends JDialog{
    private final Evento evento;
    private JPanel painelInscritosRonda;
    private JButton voltarButton;
    private JTable tableInscritos;
    private JPanel painelTabela;
    private JLabel labelTable;
    //private JScrollPane jsp;

    //ALTERAR PROVA PARA RONDA
    EcraInscritosEvento(Evento evento, Prova prova){
        this.evento = evento;

        GridLayout gl =new GridLayout(1,10);
        painelTabela.setLayout(gl);

        labelTable.setText("Lista de Inscritos "+prova.getNome());

        criarListaInscritos(evento, prova);

        voltarButton.addActionListener(this::btnVoltarActionPerformed);

        setContentPane(painelInscritosRonda);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void btnVoltarActionPerformed(ActionEvent e){
        setVisible(false);
    }

    public void criarListaInscritos(Evento evento, Prova prova){
        //obter numero de inscrito da prova
        //prova.getListaInscritos(); -> método por fazer pelo francisco

        String[][] inscritos = new String[4][4];

        int numCol=0;

        for (int i = 0; i < 2; i++) {
            inscritos[i][numCol] = "1";
            inscritos[i][numCol+1] = "Rúben Carreira";
            inscritos[i][numCol+2] = "Portugal";
        }

        String[] columnNames = { "ID", "Nome", "Pais" };

        tableInscritos = new JTable(inscritos, columnNames);
        tableInscritos.setDefaultEditor(Object.class, null);

        JScrollPane jsp = new JScrollPane(tableInscritos);

        painelTabela.add(jsp);

    }
}
