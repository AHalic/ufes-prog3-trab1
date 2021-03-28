package Eleicoes;

import java.time.LocalDate;
import java.util.*;

public class Partido {
	private String nome;
	private String sigla;
	private Votos votos;
	private int numPartido;
	private LinkedList<Candidato> candidatos;
	private int vagas;

	public Partido(String nome, String sigla, int votosLegenda, int numP) {
		this.nome = nome;
		this.sigla = sigla;
		this.numPartido = numP;
		this.vagas = 0;

		this.candidatos = new LinkedList<Candidato>();
		this.votos = new Votos(-1, votosLegenda);
	}

	public String getNome () {
		return nome;
	}

	public String getSigla () {
		return sigla;
	}

	public int getVotosLegenda () {
		return votos.getVotosLegenda();
	}

	public int getVotosNominais () {
		return votos.getVotosNominais();
	}

	public void setVotosNominais (int votosNominais) {
        this.votos.setVotosNominais(votosNominais);;
    }

	public int getVotosTotal () {
		return votos.getVotosTotais();
	}

	public void setVotosTotais () {
		int votosCandidatos = 0;

		for (Candidato candidato : this.candidatos) {
			votosCandidatos += candidato.getVotosTotal();
		}

		this.setVotosNominais(votosCandidatos);
	}

	public int getNumPartido () {
		return numPartido;
	}

	public int getVagas() {
		return this.vagas;
	}
	
	public int getFirstCandidato() {
		return this.candidatos.getFirst().getVotosTotal();
	}
	
	public int getLastCandidato() {
		return this.candidatos.getLast().getVotosTotal();
	}
	
	public LinkedList<Candidato> getCandidatos () {
		return candidatos;
	}

    public void insereCandidato (Candidato candidato) {
        this.candidatos.add(candidato);
		if (candidato.ehEleito())
			vagas++;
    }

	public void calculaVagas () {
		int vagas = 0;

		for (Candidato c: this.candidatos) {
			if (c.ehEleito())
				vagas++;
		}

		this.vagas = vagas;
	}

	public void ordenaCandidatos () {
		Collections.sort(this.candidatos, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                int votos1 = c1.getVotosTotal();
                int votos2 = c2.getVotosTotal();
                if (votos1 > votos2)
                    return -1;
                else if(votos1 == votos2) {                	
                	return c1.comparaNome(c2); 
                }
                return 1;
            }
        });
	}

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

