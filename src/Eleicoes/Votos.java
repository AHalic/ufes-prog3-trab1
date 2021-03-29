package Eleicoes;

/**
 * Esta classe representa os Votos
 * 
 * @author Beatriz Maia & Sophie Dilhon
 * @version 1.0
 * @since 27/03/2021
 */
public class Votos {
    private int votosNominais;
	private int votosLegenda;
    private int votosTotais;
    
    /**
     * Constructor da classe Voto
     * @param votosNominais - quantidade de votos nominais recebidos
     * @param votosLegenda - quantidade de votos de legenda recebidos
     */
    public Votos (int votosNominais, int votosLegenda) {
        this.votosNominais = votosNominais;
        this.votosLegenda = votosLegenda;
        this.votosTotais = votosLegenda + votosNominais;
    }

    /**
     * Retorna a quantidade de votos totais
     * @return votos totais
     */
    public int getVotosTotais() {
        return votosTotais;
    }

    /**
     * Retorna a quantidade total de votos de legenda
     * @return votos de legenda
     */
    public int getVotosLegenda() {
        return votosLegenda;
    }

    /**
     * Modifica a quantidade de votos de legenda
     * e a quantidade de votos totais
     * @param votosLegenda - quantidade de votos de legenda
     */
    public void setVotosLegenda(int votosLegenda) {
        this.votosLegenda = votosLegenda;
        this.votosTotais = this.votosLegenda + this.votosNominais;
    }

    /**
     * Retorna a quantidade de votos nominais
     * @return votos nominais
     */
    public int getVotosNominais() {
        return votosNominais;
    }

    /**
     * Modifica a quantidade de votos nominais
     * e a quantidade de votos totais
     * @param votosNominais - quantidade de votos nominais
     */
    public void setVotosNominais(int votosNominais) {
        this.votosNominais = votosNominais;
        this.votosTotais = this.votosLegenda + this.votosNominais;
    }   
}
