package Eleicoes;

public class Voto {
    private int votosNominais;
	private int votosLegenda;
    private int votosTotais;
    
    public Voto (int votosNominais, int votosLegenda) {
        this.votosNominais = votosNominais;
        this.votosLegenda = votosLegenda;
        this.votosTotais = votosLegenda + votosNominais;
    }

    public int getVotosTotais() {
        return votosTotais;
    }

    public int getVotosLegenda() {
        return votosLegenda;
    }

    public void setVotosLegenda(int votosLegenda) {
        this.votosLegenda = votosLegenda;
        this.votosTotais = this.votosLegenda + this.votosNominais;
    }

    public int getVotosNominais() {
        return votosNominais;
    }

    public void setVotosNominais(int votosNominais) {
        this.votosNominais = votosNominais;
        this.votosTotais = this.votosLegenda + this.votosNominais;
    }

    
}
