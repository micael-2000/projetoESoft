package vista.Evento.DetalhesEvento;

import modelo.*;

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
    public EcraInscritosEvento(Evento evento, ArrayList<Atleta> atletas, Prova prova, Ronda ronda){
        this.evento = evento;

        GridLayout gl =new GridLayout(1,10);
        painelTabela.setLayout(gl);

        labelTable.setText("Lista de Inscritos "+prova.getNome()+" - "+ronda.getNomeRonda()+"");

        criarListaInscritos(ronda);

        voltarButton.addActionListener(this::btnVoltarActionPerformed);

        setContentPane(painelInscritosRonda);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void btnVoltarActionPerformed(ActionEvent e){
        setVisible(false);
    }

    public void criarListaInscritos(Ronda ronda){
        //ArrayList<Inscricao> listaInscricoes = DadosAplicacao.INSTANCE.getListaInscricoesPorProva(prova);
        ArrayList<Atleta> listaInscricoes = ronda.getListaInscritos();

        String[][] inscritos = new String[4][4];

        int numCol=0;

        for (int i = 0; i < listaInscricoes.size(); i++) {
            inscritos[i][numCol] = ""+listaInscricoes.get(i).getNumeroAtleta()+"";
            inscritos[i][numCol+1] = ""+listaInscricoes.get(i).getNome()+"";
            inscritos[i][numCol+2] = ""+listaInscricoes.get(i).getPais()+"";
        }

        String[] columnNames = { "ID", "Nome", "Pais" };

        tableInscritos = new JTable(inscritos, columnNames);
        tableInscritos.setDefaultEditor(Object.class, null);

        JScrollPane jsp = new JScrollPane(tableInscritos);

        painelTabela.add(jsp);

    }
}
