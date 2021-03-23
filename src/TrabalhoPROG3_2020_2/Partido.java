package TrabalhoPROG3_2020_2;

import java.util.Set;

public class Partido {
	private String nome;
	private String sigla;
	private int votosLegenda;
	private int votosTotal;
	private int numPartido;
	private Set<Candidato> candidatos; // Seria Set? ou HashSet?
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public int getVotosLegenda() {
		return votosLegenda;
	}
	public void setVotosLegenda(int votosLegenda) {
		this.votosLegenda = votosLegenda;
	}
	public int getVotosTotal() {
		return votosTotal;
	}
	public void setVotosTotal(int votosTotal) {
		this.votosTotal = votosTotal;
	}
	public int getNumPartido() {
		return numPartido;
	}
	public void setNumPartido(int numPartido) {
		this.numPartido = numPartido;
	}
	
	// Esses dois eu mudaria pra poder escolher qual candidato quer
	// e para poder setar adicionando
	public Set<Candidato> getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(Set<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	
	
}
