package Eleicoes;

import java.time.LocalDate;
import java.util.List;

public class Eleicao {
	private LocalDate dataEleicao;
    private int votosNominais;
	private int votosLegenda;
    private int vagas;
	private List<Candidato> candidatos;
	private List<Partido> partidos; 

    public Eleicao (LocalDate dataEleicao, List<Candidato> candidatos, List<Partido> partidos) {
        this.dataEleicao = dataEleicao;

        this.partidos = partidos;
        this.candidatos = candidatos;
        this.totalVotos();
        this.calculaQtdVagas();
        this.calculaVotosTotaisPartidos();
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

    public List getCandidatos () {
        return this.candidatos;
    }

    public List getPartidos () {
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

}
