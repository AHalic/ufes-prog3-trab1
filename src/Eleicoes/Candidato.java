package Eleicoes;

import java.time.LocalDate;

/**
 * Esta classe representa um Candidato
 * 
 * @author Beatriz Maia & Sophie Dilhon
 * @version 1.0
 * @since 20/03/2021
 */
public class Candidato extends Pessoa{
	private String situacao;
	private String nomeUrna;
	private int votosTotal;
	private int numero;
	private String destVoto;
	Partido partido;

	/** 
	 * Constructor de Candidato
	 * @param nome - Nome do candidato
	 * @param genero - Genero do candidato (M ou F)
	 * @param nascimento - Aniversario do candidato
	 * @param situacao - Situacao do candidato (Eleito, Suplente ou Não eleito)
	 * @param nomeUrna - Nome do candidato na urna
	 * @param votosTotais - !uantidade de votos do candidato
	 * @param numero  - ID do candidato
	 * @param destVoto - Destino do voto do candidato (Valido ou Não Valido)
	 * @param partido - Numero do partido do candidato
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
	 * Retorna a situacao do candidato.
	 * @return Situacao (Eleito, Suplente ou Não Eleito)
	*/
	public String getSituacao() {
		return situacao;
	}

	/**
	 * Retorna o nome de urna do candidato.
	 * @return Nome de urna
	 */
	public String getNomeUrna() {
		return nomeUrna;
	}

	/**
	 * Retorna a quantidade de votos que o candidato obteve
	 * @return Votos totais
	 */
	public int getVotosTotal() {
		return this.votosTotal;
	}
		
	/**
	 * Retorna o numero (id) do candidato
	 * @return Numero do candidato
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Retorna a situacao do destino dos votos do candidato
	 * @return String do destino do voto
	 */
	public String getDestVoto() {
		return destVoto;
	}

	/**
	 * Recebe um candidato e compara o nome dele ao do candidato da classe
	 * @param c2 - Candidato que será usado para comparar nomes
	 * @return {@code true} se os candidatos possuem o mesmo nome, {@code false} caso contrario
	 */
	public int comparaNome(Candidato c2) {
		return this.getNome().compareTo(c2.getNome());
	}

	/** 
	 * Retorna se o candidato foi eleitos
	 * @return {@code true} se eleito, {@code false} se nao
	*/
	public boolean ehEleito () {
		if (this.situacao.equals("Eleito"))
			return true;
		else return false;
	}

	/** 
	 * Retorna se o destino do candidato eh valido
	 * @return {@code true} se Válido, {@code false} se nao
	*/
	public boolean ehValido () {
		if (this.destVoto.equals("Válido"))
			return true;
		else 
			return false;
	}
	
	/**
	 * Representação String do objeto na forma:
	 * Layout: "NOME / Nome na Urna (sigla, votosTotais votos)
	 * @return Representação em String (padrao)
	 */ 
	@Override
	public String toString() {
		return super.toString() + " / " + this.getNomeUrna() + " (" + this.partido.getSigla() + ", " + this.votosTotal + " votos)";
	}
	
	/**
	 * Representação String mais curta do objeto na forma:
	 * Layout: "Nome na urna (numero, votosTotais votos)"
	 * @return Representação em String (curta)
	 */
	public String shortToString() {
		String voto = "voto";

		if (this.votosTotal > 1)
			voto = "votos";

		return this.nomeUrna + " (" + this.numero + ", " + this.votosTotal + " " + voto + ")";
	}
}
