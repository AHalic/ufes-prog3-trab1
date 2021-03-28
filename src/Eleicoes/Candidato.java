package Eleicoes;

import java.time.LocalDate;

public class Candidato extends Pessoa{
	private String situacao;
	private String nomeUrna;
	private int votosTotal;
	private int numero;
	private String destVoto;
	Partido partido;

	/** 
	 * Construtor de Candidato
	 * @param nome nome do candidato
	 * @param genero genero do candidato (M ou F)
	 * @param nascimento aniversario do candidato
	 * @param situacao situacao do candidato (Eleito, Suplente ou Não eleito)
	 * @param nomeUrna nome do candidato na urna
	 * @param votosTotais quantidade de votos do candidato
	 * @param numero id do candidato
	 * @param destVoto destino do voto do candidato (Valido ou Não Valido)
	 * @param partido numero do partido do candidato
	*/
	public Candidato(String nome, String genero, LocalDate nascimento, 
			String situacao, String nomeUrna, int vT, int numero, String destVoto, Partido partido) {
		
		super(nome, genero, nascimento);
		this.destVoto = destVoto;
		this.nomeUrna = nomeUrna;
		this.numero = numero;
		this.situacao = situacao;
		this.votosTotal = vT;
		this.partido = partido;
	}
	
	/** 
	 * Retorna situacao do candidato
	 * @return situacao (Eleito, Suplente ou Não Eleito)
	*/
	public String getSituacao() {
		return situacao;
	}

	/** 
	 * Modifica a situacao
	 * @param situacao
	*/
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/** 
	 * Retorna se o candidato foi eleitos
	 * @return true se eleito, false se nao
	*/
	public boolean ehEleito () {
		if (this.situacao.equals("Eleito"))
			return true;
		else return false;
	}

	/** 
	 * Retorna se o destino do candidato eh valido
	 * @return true se valido, false se nao
	*/
	public boolean ehValido () {
		if (this.destVoto.equals("Válido"))
			return true;
		else 
			return false;
	}
	
	public String getNomeUrna() {
		return nomeUrna;
	}

	public void setNomeUrna(String nomeUrna) {
		this.nomeUrna = nomeUrna;
	}

	public int getVotosTotal() {
		return this.votosTotal;
	}
	
	public int comparaNome(Candidato c2) {
		return this.getNome().compareTo(c2.getNome());
	}

	public void setVotosTotal(int votosTotal) {
		this.votosTotal = votosTotal;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDestVoto() {
		return destVoto;
	}
	
	public void setDestVoto(String destVoto) {
		this.destVoto = destVoto;
	}
	
	@Override
	public String toString() {
		return super.toString() + " / " + this.getNomeUrna() + " (" + this.partido.getSigla().toUpperCase() + ", " + this.votosTotal + " votos)";
	}
	
	public String shortToString() {
		String voto = "voto";

		if (this.votosTotal > 1)
			voto = "votos";

		return this.nomeUrna + " (" + this.numero + ", " + this.votosTotal + " " + voto + ")";
	}
}
