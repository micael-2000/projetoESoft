package vista.Evento.DetalhesEvento;

import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class EcraAcompanharProvaEvento extends JDialog{

    private JButton voltarButton;
    private JLabel nomeEvento;
    private JPanel painelAcompanhar;
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JLabel provaNome;
    private JPanel painelTabelas;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JScrollPane jsp;
    private JScrollPane jsp2;
    private JScrollPane jsp3;
    private Prova prova;


    //SO ESTÁ IMPLEMENTADO PARA PROVA COM NUMERO DE INSRCIÇOES <= 8
    public EcraAcompanharProvaEvento(Evento evento, Prova prova, Ronda ronda){
        this.prova = prova;

        nomeEvento.setText(evento.getNomeEvento());
        provaNome.setText(prova.getNome());
        //para cada ronda da prova, apresentar uma tabela com os resultados

        GridLayout gl =new GridLayout(1,10);
        painel1.setLayout(gl);

        GridLayout gl2 =new GridLayout(1,10);
        painel2.setLayout(gl2);

        GridLayout gl3 =new GridLayout(1,10);
        painel3.setLayout(gl3);

        criarTabelaResultados();

        voltarButton.addActionListener(this::btnVoltarActionPerformed);

        painelAcompanhar.setPreferredSize(new Dimension(1200,600));
        setContentPane(painelAcompanhar);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public void btnVoltarActionPerformed(ActionEvent e){
        setVisible(false);
    }

    public void criarTabelaResultados(){

        ArrayList<Resultado> resultadosDeUmaRondaProva = new ArrayList<>();

        resultadosDeUmaRondaProva = DadosAplicacao.INSTANCE.getResultadosRondaDeProva("Meia Final", "Prova1");

        int numRondas = prova.getListaDeRondas().size();

        Object[] columnNames = { "Id", "Nome", "Pais","Pos", "Resultado", "Diferenca", "Qualificado"};


        DefaultTableModel model1 = new DefaultTableModel(columnNames, prova.getRondaPorNome("Meia Final").getListaInscritos().size());
        DefaultTableModel model2;
        DefaultTableModel model3;
        double diferenca=0;

        for (int i = 0; i <  prova.getRondaPorNome("Meia Final").getListaInscritos().size(); i++) {
            if (i == 0){
                diferenca = 0;
            }else{
                diferenca = resultadosDeUmaRondaProva.get(i).getPontuacao()-resultadosDeUmaRondaProva.get(i-1).getPontuacao();
            }

            model1.addRow(new Object[]{ resultadosDeUmaRondaProva.get(i).getId(), resultadosDeUmaRondaProva.get(i).getNome(), resultadosDeUmaRondaProva.get(i).getPais()
                            ,resultadosDeUmaRondaProva.get(i).getPosicao(), resultadosDeUmaRondaProva.get(i).getPontuacao(), diferenca
                           , resultadosDeUmaRondaProva.get(i).isClassificado()});
        }

        table1 = new JTable(model1);
        table1.setDefaultEditor(Object.class, null);

        ArrayList<Resultado> resultadosProximaRonda = DadosAplicacao.INSTANCE.getResultadosRondaDeProva("Final", "Prova1");

        if (resultadosDeUmaRondaProva != null){
            model2 = new DefaultTableModel(columnNames, resultadosProximaRonda.size());
        } else{
            model2 = new DefaultTableModel(columnNames, 0);
            for (int i = 0; i <  prova.getRondaPorNome("Final").getListaInscritos().size(); i++) {
                model2.addRow(new Object[]{resultadosProximaRonda.get(i).getId(), resultadosProximaRonda.get(i).getNome(), resultadosProximaRonda.get(i).getPais()
                        , resultadosProximaRonda.get(i).getPosicao(), resultadosProximaRonda.get(i).getPontuacao(), diferenca
                        , resultadosProximaRonda.get(i).isClassificado()});
            }

        }

        table2 = new JTable(model2);
        table2.setDefaultEditor(Object.class, null);

        model3 = new DefaultTableModel(columnNames, 0);

        table3 = new JTable(model3);
        table3.setDefaultEditor(Object.class, null);

        jsp = new JScrollPane(table1);
        jsp2 = new JScrollPane(table2);
        jsp3 = new JScrollPane(table3);

        painel1.add(jsp);
        painel2.add(jsp2);
        painel3.add(jsp3);

    }

}
