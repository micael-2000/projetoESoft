package modelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Hashtable;

public class Evento {
    private ArrayList<Prova> listaProvas;
    private String nomeEvento;
    private Data dataInicio;
    private Data dataFim;
    private String local;
    private String pais;
    private ArrayList<DefaultTableModel> arraysModelos;
    private Pais[] listaPais;

    public Evento(ArrayList<Prova> listaProvas, Data dataInicio, Data dataFim, String local, String pais, String nomeEvento) {
        this.listaProvas = listaProvas;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.pais = pais;
        this.nomeEvento = nomeEvento;
        arraysModelos = new ArrayList<>();
        listaProvas = null;
    }

    public ArrayList<DefaultTableModel> getArraysModelos() {
        return arraysModelos;
    }

    public DefaultTableModel getModel(int dia){
       return arraysModelos.get(dia);
    }

    public void setArraysModelos(ArrayList<DefaultTableModel> arraysModelos) {
        this.arraysModelos = arraysModelos;
    }

    public ArrayList<Prova> getListaProvas() {
        return listaProvas;
    }

    public ArrayList<Integer> getIdProvas(){
        ArrayList<Integer> arrayIds = new ArrayList<>();
        for (Prova prova : listaProvas) {
            arrayIds.add(prova.getId());
        }
        return arrayIds;
    }

    public void criarRondas(){


        ArrayList<Atleta> inscricoesNaProva;
        int diferenciadorHora = 0;
        for (Prova prova : this.getListaProvas()){
            inscricoesNaProva = DadosAplicacao.INSTANCE.getAtletasInscritosNumaProva(prova);

            int numInscricoes = inscricoesNaProva.size();
            System.out.println(numInscricoes);
            if (numInscricoes <=8){
                Ronda ronda1 = new Ronda(prova, inscricoesNaProva, new Data(this.getDataInicio().getDia(),this.getDataInicio().getMes(),this.getDataInicio().getAno(), 14+diferenciadorHora, 0), 2,0);
                ronda1.setNomeRonda("Meia Final");
                prova.addRonda(ronda1);
                Ronda rondaFinal = new Ronda(prova, inscricoesNaProva,new Data((this.getDataFim().getDia()),this.getDataFim().getMes(),+this.getDataFim().getAno(), 14+diferenciadorHora, 0), 1,this.dataFim.getDia()-this.dataInicio.getDia());
                rondaFinal.setNomeRonda("Final");
                prova.addRonda(rondaFinal);

            } else if (numInscricoes < 16){
                Ronda ronda1 = new Ronda(prova, inscricoesNaProva, new Data(this.getDataInicio().getDia(),this.getDataInicio().getMes(),this.getDataInicio().getAno(), 14+diferenciadorHora, 0), 4,0);
                ronda1.setNomeRonda("Quartos-Final");
                prova.addRonda(ronda1);
                Ronda ronda2 = new Ronda(prova, null, new Data((this.getDataInicio().getDia()+2),this.getDataInicio().getMes(),this.getDataInicio().getAno(), 14+diferenciadorHora, 0), 4,2);
                ronda2.setNomeRonda("Quartos-Final");
                prova.addRonda(ronda2);
                Ronda ronda3 = new Ronda(prova, null, new Data((this.getDataInicio().getDia()+4),this.getDataInicio().getMes(),this.getDataInicio().getAno(), 14+diferenciadorHora, 0), 4,4);
                ronda2.setNomeRonda("Meia-Final");
                prova.addRonda(ronda3);
                Ronda rondaFinal = new Ronda(prova,null,new Data((this.getDataFim().getDia()),this.getDataFim().getMes(),+this.getDataFim().getAno(), 14+diferenciadorHora, 0), 1,this.dataFim.getDia()-this.dataInicio.getDia());
                rondaFinal.setNomeRonda("Final");
                prova.addRonda(rondaFinal);

            }else{
                JOptionPane.showMessageDialog(null,"O número de inscritos ultrapassa o máximo estipulado");
            }

            diferenciadorHora++;
        }

    }

    public ArrayList<Inscricao> getInscricoes(){
        ArrayList<Prova> provasEvento = this.getListaProvas();
        ArrayList<Inscricao> inscricoes = new ArrayList<>();
        ArrayList<Inscricao> aux = new ArrayList<>();
        for (Prova prova : provasEvento){
            aux = DadosAplicacao.INSTANCE.getListaInscricoesPorProva(prova);
            inscricoes.addAll(aux);
        }
        return inscricoes;
    }



    public Data getDataInicio() {
        return dataInicio;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public String getLocal() {
        return local;
    }

    public String getPais() {
        return pais;
    }

    public void setListaProvas(ArrayList<Prova> listaProvas) {
        this.listaProvas = listaProvas;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }
}
