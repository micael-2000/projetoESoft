package vista.Evento.DetalhesEvento;

import jdk.nashorn.internal.scripts.JO;
import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

public class EcraProgramaEvento extends JDialog{
    private JPanel painelProgramaEvento;
    private JButton tabelaDeMedalhasButton;
    private JButton vencedoresButton;
    private JButton voltarButton;
    private JPanel painelDias;
    private JPanel painelTabelas;
    private JButton atualizarCalendarioButton;
    private JTable tabelaDoDia;
    private JScrollPane jspane;
    private JTable tableProvasPorDia;
    private String[] arrayButtonsDias;
    private JButton buttons[];
    private Evento evento;
    private int atualizado;


    public EcraProgramaEvento(Evento evento){
        this.evento = evento;
        this.atualizado=0;
        if(evento.getArraysModelos() == null){
            evento.criarRondas();
        }

        GridLayout gl =new GridLayout(1,10);
        painelDias.setLayout(gl);

        GridLayout gl2 =new GridLayout(1,10);
        painelTabelas.setLayout(gl2);

        painelTabelas.setSize(new Dimension(200,200));


        criarBotoesECalendario(evento, painelDias);

        voltarButton.addActionListener(this::btnVoltarActionPerformed);
        atualizarCalendarioButton.addActionListener(this::btnAtualizarCalendarioActionPerfomed);
        tabelaDeMedalhasButton.addActionListener(this::btnTabelaMedalhasActionPeformed);
        vencedoresButton.addActionListener(this::btnVencedoresActionPeformed);

        painelProgramaEvento.add(voltarButton, "SOUTH");
        painelProgramaEvento.add(atualizarCalendarioButton, "SOUTH");
        painelProgramaEvento.add(tabelaDeMedalhasButton, "SOUTH");
        painelProgramaEvento.add(vencedoresButton, "SOUTH");

        setContentPane(painelProgramaEvento);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void btnAtualizarCalendarioActionPerfomed(ActionEvent e){
        this.atualizado=1;
        evento.criarRondas();
        int dias = evento.getDataFim().getDia() - evento.getDataInicio().getDia() +1;

        ArrayList<DefaultTableModel> arrayModels = new ArrayList<>();

        for (int i =0; i < dias; i++){

            Object[] columnNames = { "Hora", "Nome", "TipoProva","Ronda", "Local", "Género", "Inscritos", "Resultados", "Progresso" };

            DefaultTableModel model = new DefaultTableModel(null, columnNames);

            Ronda rondaDia = null;

            for (Prova prova : evento.getListaProvas()) {
                rondaDia = prova.getListaDeRondasDaProvaPorDia(i);
                if (rondaDia != null) {
                    model.addRow(new Object[]{rondaDia.getHora().getHora() + ":" + rondaDia.getHora().getMinutos(), rondaDia.getProvaDaRonda().getNome(), rondaDia.getProvaDaRonda().getTipoProva(),
                            rondaDia.getNomeRonda(), rondaDia.getProvaDaRonda().getLocal(), rondaDia.getProvaDaRonda().getGenero(), "LISTA", "VER", "ACOMPANHAR"});
                }
                else{
                    model.addRow(new Object[]{"Nao existe", "Nao existe","Nao existe","Nao existe","Nao existe","Nao existe","LISTA", "VER", "ACOMPANHAR"});
                }
            }
            arrayModels.add(model);
        }

        evento.setArraysModelos(arrayModels);
    }




    private void btnConsultarInscritosActionPerformed(ArrayList<Atleta> atletas, Prova prova, Ronda ronda) {

        new EcraInscritosEvento(evento, atletas, prova, ronda);
    }

    private void btnResultadosRondaProvaActionPerformed(Prova prova, Ronda ronda){

        new EcraResultadosRondaProva(evento, prova, ronda);
    }

    private void btnAcompanharProvaEventoActionPerformed(Prova prova, Ronda ronda){

        new EcraAcompanharProvaEvento(evento, prova, ronda);
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
            diasParaOArray++;
        }

        for(int i = 0; i < diff; i++) {
            buttons[i] = new JButton(arrayButtonsDias[i]);
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    criarTabelasRondasActionPerformed(finalI, evento);
                    //System.out.println(buttons[finalI].getText());
                }
            });
        }

        GridLayout gl =new GridLayout(10,10);
        painelProgramaEvento.setLayout(gl);

        for(int i = 0; i < diff; i++) {
            zone.add(buttons[i]);
        }

    }


    public void criarTabelasRondasActionPerformed( int dia, Evento evento){

        if (this.atualizado ==0) {
            JOptionPane.showMessageDialog(null, "Antes de aceder ao programa, atualize os dados no botão");
        } else{
            tabelaDoDia.setModel( evento.getModel(dia));
            tabelaDoDia.setDefaultEditor(Object.class, null);
            tabelaDoDia.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        JTable target = (JTable)e.getSource();
                        int row = target.getSelectedRow();
                        int column = target.getSelectedColumn();

                        //Inscritos
                        if(column == 6){
                            btnConsultarInscritosActionPerformed(evento.getListaProvas().get(row).getListaDeRondasDaProvaPorDia(dia).getListaInscritos(), evento.getListaProvas().get(row)
                                    , evento.getListaProvas().get(row).getListaDeRondasDaProvaPorDia(dia));
                        }
                        //Resultados
                        if(column == 7){
                            btnResultadosRondaProvaActionPerformed(evento.getListaProvas().get(row), evento.getListaProvas().get(row).getListaDeRondasDaProvaPorDia(dia));
                        }
                        //Progresso
                        else if(column == 8){
                            btnAcompanharProvaEventoActionPerformed(evento.getListaProvas().get(row), evento.getListaProvas().get(row).getListaDeRondasDaProvaPorDia(dia));
                        }
                    }
                }
            });
        }


    }



    public void filtrarGenero(){
        return;
    }



}
