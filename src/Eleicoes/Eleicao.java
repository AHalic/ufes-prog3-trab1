package Eleicoes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;

public class Eleicao {
	private LocalDate dataEleicao;
    private int votosNominais;
	private int votosLegenda;
    private int vagas;
	private Set<Candidato> candidatos;
	private LinkedList<Partido> partidos; 

    public Eleicao (LocalDate dataEleicao, Set<Candidato> candidatos, LinkedList<Partido> partidos) {
        this.dataEleicao = dataEleicao;

        this.partidos = partidos;
        this.candidatos = candidatos;
        this.totalVotos();
        this.calculaQtdVagas();
        this.calculaVotosTotaisPartidos();
        this.calculaVagasPartidos();
        this.ordenaPartidos();
        this.ordenaPartidoCandidatos();
    }

    public LocalDate getDataEleicao () {
        return dataEleicao;
    }

    public int getVotosNominais () {
        return votosNominais;
    }

    public int getVotosLegenda () {
        return votosLegenda;
    }

    public int getVotosTotal () {
        return this.votosLegenda + this.votosNominais;
    }

    public int getVagas () {
        return this.vagas;
    }

    private void totalVotos() {
    	int votosNominais = 0, votosLegenda = 0;
    	
    	for(Partido partido : this.partidos) {
    		votosLegenda += partido.getVotosLegenda();
    		votosNominais += partido.getVotosNominais(); // TODO mudar? me toquei q tem candidatos
    	}
    	
    	this.votosLegenda = votosLegenda;
    	this.votosNominais = votosNominais;
    }

    public Set getCandidatos () {
        return this.candidatos;
    }

    public LinkedList getPartidos () {
        return this.partidos;
    }

    public void insereCandidato (Candidato candidatoEleicao) {
        this.candidatos.add(candidatoEleicao);
    }

    public void inserePartido (Partido partidoEleicao) {
        this.partidos.add(partidoEleicao);
    }

    private void calculaQtdVagas () {
        int qtdVagas = 0;

        for (Candidato candidatoAux : this.candidatos) {
            if (candidatoAux.ehEleito()) {
                qtdVagas++;
            }
        }

        this.vagas = qtdVagas;
    }
 
    private void calculaVotosTotaisPartidos () {
        int votos;

        for (Partido p: this.partidos) {
            votos = 0;

            for (Candidato candidatoAux : p.getCandidatos()) {
                votos += candidatoAux.getVotosTotal();
            }

            p.setVotosNominais(votos);
        }
    }

    private void calculaVagasPartidos () {
        double QCEdb = (double) this.getVotosTotal() / this.vagas;

        int QCE = (int) QCEdb;
        double dif = QCEdb - QCE;

        if (dif > 0.5)
            QCE++;

        for (Partido p: this.partidos)   {
            p.calculaVagas();
        }
    }

    private void ordenaPartidos () {
        Collections.sort(this.partidos, new Comparator<Partido>() {
            @Override
            public int compare(Partido p1, Partido p2) {
                int vagas1 = p1.getVagas();
                int vagas2 = p2.getVagas();
                if (vagas1 > vagas2)
                    return -1;
                else
                    return 1;
            }
        });
    }

    private void ordenaPartidoCandidatos () {
        for (Partido p : this.partidos) {
            p.ordenaCandidatos();
        }
    }
}
