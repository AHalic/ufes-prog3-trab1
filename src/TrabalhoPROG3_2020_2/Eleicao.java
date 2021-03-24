package TrabalhoPROG3_2020_2;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Eleicao {
	private LocalDate dataEleicao;
    private int votosNominais;
	private int votosLegenda;
	private Set<Candidato> candidatos;
	private Set<Partido> partidos; 

    public Eleicao (LocalDate dataEleicao, int votosNominais, int votosLegenda) {
        this.dataEleicao = dataEleicao;
        this.votosNominais = votosNominais;
        this.votosLegenda = votosLegenda;

        this.partidos = new HashSet<Partido>();
        this.candidatos = new HashSet<Candidato>();
    }

    public LocalDate getDataEleicao () {
        return dataEleicao;
    }

    public void setDataEleicao (LocalDate dataEleicao) {
        this.dataEleicao = dataEleicao;
    }

    public int getVotosNominais () {
        return votosNominais;
    }

    public void setVotosNominais (int votosNominais) {
        this.votosNominais = votosNominais;
    }

    public int getVotosLegenda () {
        return votosLegenda;
    }

    public void setVotosLegenda (int votosLegenda) {
        this.votosLegenda = votosLegenda;
    }

    public int getVotosTotal () {
        return this.votosLegenda + this.votosNominais;
    }

    public void insereCandidato (Candidato candidatoEleicao) {
        this.candidatos.add(candidatoEleicao);
    }

    public void inserePartido (Partido partidoEleicao) {
        this.partidos.add(partidoEleicao);
    }
}
