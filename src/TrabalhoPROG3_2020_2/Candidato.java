package TrabalhoPROG3_2020_2;

public class Candidato extends Pessoa{
	private String situacao;
	private String nomeUrna;
	private int votosTotal;
	private int numero;
	private String destVoto;
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getNomeUrna() {
		return nomeUrna;
	}
	public void setNomeUrna(String nomeUrna) {
		this.nomeUrna = nomeUrna;
	}
	public int getVotosTotal() {
		return votosTotal;
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

}
