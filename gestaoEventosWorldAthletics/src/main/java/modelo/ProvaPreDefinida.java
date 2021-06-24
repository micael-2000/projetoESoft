package modelo;

import java.util.ArrayList;
import java.util.HashSet;

public class ProvaPreDefinida {
    private int id;
    private String nome;
    private String categoria;
    private String local;
    private String tipoProva;
    private Genero genero;
    private String notas;
    private ArrayList<String> recordesMundialAtual;
    private ArrayList<String> recordistas;
    private ArrayList<String> eventosEmQueDecorreu;
    private ArrayList<Ronda> listaDeRondas;
    private double marcaMinima;

    public ProvaPreDefinida(String nome, String categoria, String local, String tipoProva, Genero genero, String notas, Integer id, double marcaMinima) {
        //Esta verificação e para quando queremos criar uma provadadospredefinidos ou uma prova, para nao tar a mudar de id
        if(id == -1){
            this.id = DadosAplicacao.INSTANCE.getSizeProvasPreDefinidas() + 1;
        }
        else{
            this.id = id;
        }
        this.nome = nome;
        this.categoria = categoria;
        this.local = local;
        this.tipoProva = tipoProva;
        this.genero = genero;
        this.notas = notas;
        recordesMundialAtual = new ArrayList<>();
        recordistas = new ArrayList<>();
        eventosEmQueDecorreu = new ArrayList<>();
        this.marcaMinima = marcaMinima;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoProva() {
        return tipoProva;
    }

    public void setTipoProva(String tipoProva) {
        this.tipoProva = tipoProva;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public ArrayList<String> getRecordesMundial() {

        ArrayList<Resultado> resultados = DadosAplicacao.INSTANCE.getListaResultados();
        recordesMundialAtual = new ArrayList<>();
        recordistas = new ArrayList<>();
        double maior =0;
        double pontuacaoAtual=0;
        for(Resultado res : resultados){
            if (res.getNomeProva().equals(this.nome)){
                pontuacaoAtual = res.getPontuacao();
                if (pontuacaoAtual > maior){
                    maior = pontuacaoAtual;
                    if (recordesMundialAtual.size() <= 3 ){
                        recordesMundialAtual.add(String.valueOf(maior));
                        recordistas.add(res.getNome());
                    } else{
                        for (String record : recordesMundialAtual){
                            if (maior > Integer.parseInt(record)){
                                recordesMundialAtual.remove(record);
                                recordesMundialAtual.add(String.valueOf(maior));

                                recordistas.add(res.getNome());
                            }
                        }
                    }
                }
            }
        }

        return recordesMundialAtual;
    }


    public ArrayList<String> getRecordistas() {
        return recordistas;
    }

    public double getMarcaMinima() {
        return marcaMinima;
    }

    public ArrayList<String> getEventosEmQueDecorreu() {
        eventosEmQueDecorreu = new ArrayList<>();
        ArrayList<Evento> eventos = DadosAplicacao.INSTANCE.getListaEventos();
        ArrayList<Prova> provas = null;

        for (Evento evento : eventos) {
            provas = evento.getListaProvas();

            for (Prova prova: provas) {
                if (prova.getNome().equals(this.nome)){
                    eventosEmQueDecorreu.add(evento.getNomeEvento());

                    break;
                }
            }
        }

        return eventosEmQueDecorreu;
    }

}
