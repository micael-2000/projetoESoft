package vista.Evento.DetalhesEvento;

import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EcraProgramaEvento extends JDialog{
    private JPanel painelProgramaEvento;
    private JButton tabelaDeMedalhasButton;
    private JButton vencedoresButton;
    private JButton voltarButton;
    private JPanel painelDias;
    private JPanel painelTabelas;
    private JTable tableProvasPorDia;
    private String[] arrayButtonsDias;
    private JButton buttons[];
    private Evento evento;

    public EcraProgramaEvento(Evento evento){
        this.evento = evento;

        GridLayout gl =new GridLayout(1,10);
        painelDias.setLayout(gl);

        GridLayout gl2 =new GridLayout(1,10);
        painelTabelas.setLayout(gl2);

        painelTabelas.setSize(new Dimension(200,200));

        criarBotoesECalendario(evento, painelDias);

        ArrayList<Prova> provas = evento.getListaProvas();

        criarTabelasRondas(provas);

        voltarButton.addActionListener(this::btnVoltarActionPerformed);

        tabelaDeMedalhasButton.addActionListener(this::btnTabelaMedalhasActionPeformed);
        vencedoresButton.addActionListener(this::btnVencedoresActionPeformed);

        painelProgramaEvento.add(voltarButton, "SOUTH");
        painelProgramaEvento.add(tabelaDeMedalhasButton, "SOUTH");
        painelProgramaEvento.add(vencedoresButton, "SOUTH");

        setContentPane(painelProgramaEvento);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void criarTabelasRondas(ArrayList<Prova> provas){

        String[][] rows = new String[4][9];
        //prova = new ProvaDadosPreDefinidos("Prova2", "Corrida", "Pista exterior", "Eliminatórias", Genero.MASCULINO, " ", -1);
        int numCol=0;

        //criar uma lista de rondas onde cada prova terá um numero de rondas dependentes no numero de inscritos, depois cada ronda tera um id
        //e a tabela será feita com a lista de rondas.
        //as provas tem de ter um id para associar as rondas

        for (int i = 0; i < 2; i++) {
            rows[i][numCol] = "A definir";
            rows[i][numCol+1] = ""+provas.get(i).getNome()+"";
            rows[i][numCol+2] = ""+provas.get(i).getTipoProva()+"";
            rows[i][numCol+3] = "A definir";
            rows[i][numCol+4] = ""+provas.get(i).getLocal()+"";
            rows[i][numCol+5] = ""+provas.get(i).getGenero()+"";
            rows[i][numCol+6] = "LISTA";
            rows[i][numCol+7] = "VER";
            rows[i][numCol+8] = "ACOMPANHAR";
        }

        String[] columnNames = { "Hora", "Nome", "TipoProva","Ronda", "Local", "Género", "Inscritos", "Resultados", "Progresso" };

        tableProvasPorDia = new JTable(rows, columnNames);
        tableProvasPorDia.setDefaultEditor(Object.class, null);

        JScrollPane sp = new JScrollPane(tableProvasPorDia);

        painelTabelas.add(sp);

        tableProvasPorDia.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    //Inscritos
                    if(column == 6){
                        btnConsultarInscritosActionPerformed(evento.getListaProvas().get(row));
                    }
                    //Resultados
                    if(column == 7){
                        btnResultadosRondaProvaActionPerformed(evento.getListaProvas().get(row));
                    }
                    //Progresso
                    else if(column == 8){
                        btnAcompanharProvaEventoActionPerformed(evento.getListaProvas().get(row));
                    }
                }
            }
        });
    }


    private void btnConsultarInscritosActionPerformed(Prova prova) {

        new EcraInscritosEvento(evento, prova);
    }

    private void btnResultadosRondaProvaActionPerformed(Prova prova){

        new EcraResultadosRondaProva(evento, prova);
    }

    private void btnAcompanharProvaEventoActionPerformed(Prova prova){

        new EcraAcompanharProvaEvento(evento, prova);
    }

    public void btnTabelaMedalhasActionPeformed(ActionEvent e) {
        setVisible(false);
        new EcraEventoMedalhas(evento);
    }

    public void btnVencedoresActionPeformed(ActionEvent e) {
        setVisible(false);
        new EcraEventoVencedores(evento);
    }

    public void btnVoltarActionPerformed(ActionEvent e){
        setVisible(false);
    }

    public void criarBotoesECalendario(Evento evento, JPanel zone){

        int diaInicio = evento.getDataInicio().getDia();
        int diaFim = evento.getDataFim().getDia();

        //modificar para os casos em que o mes muda

        int diff = (diaFim - diaInicio)+1;
        int diasParaOArray = diaInicio;
        arrayButtonsDias = new String[diff];
        buttons = new JButton[diff];

        for (int i = 0; i < diff; i++) {
            arrayButtonsDias[i] = Integer.toString(diasParaOArray) ;
            System.out.println(arrayButtonsDias[i]);
            diasParaOArray++;
        }

        for(int i = 0; i < diff; i++) {
            buttons[i] = new JButton(arrayButtonsDias[i]);
        }

        GridLayout gl =new GridLayout(10,10);
        painelProgramaEvento.setLayout(gl);

        for(int i = 0; i < diff; i++) {
            zone.add(buttons[i]);
        }

    }

    public void filtrarGenero(){
        return;
    }



}
