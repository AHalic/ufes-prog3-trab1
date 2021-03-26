package Eleicoes;

import java.util.LinkedList;

public class Partido {
	private String nome;
	private String sigla;
	private int votosLegenda;
	private int votosNominais;
	private int votosTotal;
	private int numPartido;
	private LinkedList<Candidato> candidatos;
	private int vagas;

	public Partido(String nome, String sigla, int vtL, int numP) {
		this.nome = nome;
		this.sigla = sigla;
		this.votosLegenda = vtL;
		this.numPartido = numP;
		this.candidatos = new LinkedList<Candidato>();

		this.votosTotal = -1;
		this.vagas = -1;
		this.votosNominais = -1;
	}

	public String getNome () {
		return nome;
	}

	public void setNome (String nome) {
		this.nome = nome;
	}

	public String getSigla () {
		return sigla;
	}

	public void setSigla (String sigla) {
		this.sigla = sigla;
	}

	public int getVotosLegenda () {
		return votosLegenda;
	}

	public void setVotosLegenda (int votosLegenda) {
		this.votosLegenda = votosLegenda;
	}

	public int getVotosNominais () {
		return votosNominais;
	}

	public void setVotosNominais (int votosNominais) {
		this.votosNominais = votosNominais;
	}

	public int getVotosTotal () {
		return votosTotal;
	}

	public void setVotosTotal (int votosTotal) {
		this.votosTotal = votosTotal;
	}

	public int getNumPartido () {
		return numPartido;
	}

	public void setNumPartido (int numPartido) {
		this.numPartido = numPartido;
	}

	public int getVagas() {
		return this.vagas;
	}
	
	// Esses dois eu mudaria pra poder escolher qual candidato quer
	// e para poder setar adicionando
	public LinkedList<Candidato> getCandidatos () {
		return candidatos;
	}

	public void setCandidatos (LinkedList<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public void getVotosTotais () {
		int votosCandidatos = 0;

		for (Candidato candidato : this.candidatos) {
			votosCandidatos += candidato.getVotosTotal();
		}

		this.votosTotal = votosCandidatos + votosLegenda;
		this.votosNominais = votosCandidatos;
	}

    public void insereCandidato (Candidato candidatoEleicao) {
        this.candidatos.add(candidatoEleicao);
    }

	public void calculaVagas (int vagasTotais) {
		// ERRADO
		this.vagas = this.votosTotal * 100 / vagasTotais; 

		System.out.println(this.votosTotal + "/" + vagasTotais + " = " + this.vagas);
	}

}
