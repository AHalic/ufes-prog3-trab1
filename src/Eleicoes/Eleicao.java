package Eleicoes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Eleicao {
	private LocalDate dataEleicao;
    private int votosNominais;
	private int votosLegenda;
	private Set<Candidato> candidatos;
	private Set<Partido> partidos; 

    public Eleicao (LocalDate dataEleicao, Set<Candidato> candidatos, Set<Partido> partidos) {
        this.dataEleicao = dataEleicao;

        this.partidos = partidos;
        this.candidatos = candidatos;
        this.totalVotos();
        System.out.println(this.votosLegenda + " " + this.votosNominais + " é isso");
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

    public void totalVotos() {
    	int votosNominais = 0, votosLegenda = 0;
    	
    	for(Partido partido : this.partidos) {
    		votosLegenda += partido.getVotosLegenda();
    		votosNominais += partido.getVotosNominais();
    	}
    	
    	this.votosLegenda = votosLegenda;
    	this.votosNominais = votosNominais;
    }
    
    public void insereCandidato (Candidato candidatoEleicao) {
        this.candidatos.add(candidatoEleicao);
    }

    public void inserePartido (Partido partidoEleicao) {
        this.partidos.add(partidoEleicao);
    }

}
