package Eleicoes;

import java.time.LocalDate;
import java.util.*;


/**
 * Esta classe representa um Partido
 * 
 * @author Beatriz Maia & Sophie Dilhon
 * @version 1.0
 * @since 21/03/2021
 */
public class Partido {
	private String nome;
	private String sigla;
	private Votos votos;
	private int numPartido;
	private LinkedList<Candidato> candidatos;
	private int vagas;

	
	/**
	 * Constructor da classe Partido
	 * @param nome - nome deste Partido
	 * @param sigla - sigla deste Partido
	 * @param votosLegenda - total de votos de legenda para este Partido
	 * @param numP - número referente a este Partido
	 */
	public Partido(String nome, String sigla, int votosLegenda, int numP) {
		this.nome = nome;
		this.sigla = sigla;
		this.numPartido = numP;
		this.vagas = 0;

		this.candidatos = new LinkedList<Candidato>();
		this.votos = new Votos(-1, votosLegenda);
	}

	
	/**
	 * Retorna o nome deste Partido
	 * @return nome
	 */
	public String getNome () {
		return this.nome;
	}

	/**
	 * Retorna a sigla deste Partido
	 * @return sigla
	 */
	public String getSigla () {
		return this.sigla;
	}

	/**
	 * Retorna a quantidade de votos de legenda a este Partido
	 * @return votos de legenda
	 */
	public int getVotosLegenda () {
		return votos.getVotosLegenda();
	}

	/**
	 * Retorna a quantidade de votos nominais possuidos por este Partido
	 * @return votos nominais
	 */
	public int getVotosNominais () {
		return votos.getVotosNominais();
	}

	/**
	 * Muda a quantidade de votos nominais possuídos por este Partido
	 * @param votosNominais - novo valor da soma dos votos recebidos
	 * 						  por cada Candidato deste Partido
	 */
	public void setVotosNominais (int votosNominais) {
        this.votos.setVotosNominais(votosNominais);;
    }

	/**
	 * Retorna a quantidade total de votos deste PArtido
	 * @return votos de Legenda + votos nominais
	 */
	public int getVotosTotal () {
		return votos.getVotosTotais();
	}

	/**
	 * Calcula a soma dos votos nominais de todos 
	 * Candidatos deste Partido e então
	 * muda a quantidade de votos nominais deste
	 */
	public void setVotosTotais () {
		int votosCandidatos = 0;

		for (Candidato candidato : this.candidatos) {
			votosCandidatos += candidato.getVotosTotal();
		}

		this.setVotosNominais(votosCandidatos);
	}

	/**
	 * Retorna o número deste Partido
	 * @return numPartido
	 */
	public int getNumPartido () {
		return this.numPartido;
	}

	/**
	 * Retorna a quantidade de Candidatos 
	 * deste Partido que foram eleitos
	 * @return vagas
	 */
	public int getVagas() {
		return this.vagas;
	}
	
	/**
	 * Retorna a quantidade de votos recebidos
	 * pelo primeiro Candidato da lista deste Partido
	 * @return votos ao primeiro Candidato
	 */
	public int getVotosFirstCandidato() {
		return this.candidatos.getFirst().getVotosTotal();
	}
	
	/**
	 * Retorna o numero partidario do primeiro
	 * Candidato da lista de candidatos dete Partido
	 * @return numero partidario do primeiro candidato
	 */
	public int getNumFirstCandidato() {
		return this.candidatos.getFirst().getNumero();
	}
	
	/**
	 * Retorna a lista de candidatos deste Partido
	 * @return lista de Candidato
	 */
	public LinkedList<Candidato> getCandidatos () {
		return candidatos;
	}

	/**
	 * Insere um Candidato na lista de candidatos deste Partido
	 * e verifica se esse foi eleito, caso sim incrementa o número de vagas
	 * @param candidato - candidato que será inserido
	 */
    public void insereCandidato (Candidato candidato) {
        this.candidatos.add(candidato);
		if (candidato.ehEleito())
			vagas++;
    }

    /**
     * Ordena a lista de candidatos deste Partido com base na quantidade
     * votos deles, e no caso de empate com base na idade deles
     */
	public void ordenaCandidatos (LocalDate dataEleicao) {
		Collections.sort(this.candidatos, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                int votos1 = c1.getVotosTotal();
                int votos2 = c2.getVotosTotal();
                if (votos1 > votos2)
                    return -1;
                else if(votos1 == votos2) {
                	if (c1.getIdade(dataEleicao) > c2.getIdade(dataEleicao))
                		return -1;
                }
                return 1;
            }
        });
	}

	/**
	 * Retorna uma string de representação deste Partido
	 */
	@Override
	public String toString () {
		String fraseCandidatoEleito = " candidato eleito";
		String fraseVoto = " voto";
		String fraseNominal = " nominal";

		if (this.getVagas() > 1) 
			fraseCandidatoEleito = " candidatos eleitos";

		if (this.votos.getVotosTotais() > 1)
			fraseVoto = " votos";

		if (this.votos.getVotosNominais() > 1)
			fraseNominal = " nominais";

		return this.sigla + " - " + this.numPartido + ", " + this.votos.getVotosTotais() + fraseVoto + " (" + this.votos.getVotosNominais() + fraseNominal + " e " + this.votos.getVotosLegenda() + " de legenda), " + this.vagas + fraseCandidatoEleito;
	}

	/**
	 * Retorna a quantidade de candidatos com idades
	 * contidas em um intervalo de idade
	 * @param limIdadeMin - menor idade do intervalo
	 * @param limIdadeMax - maior idade do intervalo
	 * @param data - data para calculo da idade
	 * @return quantidade de candidatos no intervalo
	 */
	public int getCandidatosPorIdade (int limIdadeMin, int limIdadeMax, LocalDate data) {
		int idadeCandidatos = 0;

		for (Candidato c: this.candidatos) {
			int idade = c.getIdade(data);
			if (limIdadeMin <= idade && idade < limIdadeMax && c.ehEleito())
				idadeCandidatos++;
		}

		return idadeCandidatos;
	}
}

