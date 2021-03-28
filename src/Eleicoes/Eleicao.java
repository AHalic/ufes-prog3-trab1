package Eleicoes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Eleicao {
	private LocalDate dataEleicao;
    private int votosNominais;
	private int votosLegenda;
	private int votosTotais;
    private int vagas;
	private LinkedList<Candidato> candidatos;
	private LinkedList<Partido> partidos; 

    public Eleicao (LocalDate dataEleicao, LinkedList<Candidato> candidatos, LinkedList<Partido> partidos) {
        this.dataEleicao = dataEleicao;

        this.partidos = partidos;
        this.candidatos = candidatos;
        this.totalVotos();
        this.calculaQtdVagas();
        this.calculaVotosTotaisPartidos();
        this.ordenaPartidosVagas();
        this.ordenaCandidatos();
        this.ordenaPartidoCandidatos();
    }

    public LocalDate getDataEleicao () {
        return dataEleicao;
    }

    public int getVotosNominais () {
        return votosNominais;
    }

    public int getVotosLegenda () {
        return votosLegenda;
    }

    private void setVotosTotais () {
        this.votosTotais = this.votosLegenda + this.votosNominais;
    }
    
    public int getVotosTotais () {
        return this.votosTotais;
    }

    public int getVagas () {
        return this.vagas;
    }
    
    private void totalVotos() {
    	int votosNominais = 0, votosLegenda = 0;
    	
    	for(Partido partido : this.partidos) {
    		votosLegenda += partido.getVotosLegenda();
    		votosNominais += partido.getVotosNominais(); 
    	}
    	
    	this.votosLegenda = votosLegenda;
    	this.votosNominais = votosNominais;
    	this.setVotosTotais();
    }

    public LinkedList<Candidato> getCandidatos () {
        return this.candidatos;
    }

    public LinkedList<Partido> getPartidos () {
        return this.partidos;
    }

    public void insereCandidato (Candidato candidatoEleicao) {
        this.candidatos.add(candidatoEleicao);
    }

    public void inserePartido (Partido partidoEleicao) {
        this.partidos.add(partidoEleicao);
    }

    public int calculaQtdF() {
    	int F = 0;
    	
    	for(Candidato c : this.candidatos) {
    		if(c.ehGeneroF() && c.ehEleito())
    			F++;
    	}
    	
    	return F;
    }
    
     private void calculaQtdVagas () {
        int qtdVagas = 0;

        for (Partido p : this.partidos) {
        	qtdVagas += p.getVagas();
        }
        this.vagas = qtdVagas;
    }
 
    private void calculaVotosTotaisPartidos () {
        int votos;

        for (Partido p: this.partidos) {
            votos = 0;

            for (Candidato candidatoAux : p.getCandidatos()) {
                votos += candidatoAux.getVotosTotal();
            }

            p.setVotosNominais(votos);
        }
    }

    public void ordenaPartidosVagas () {
        Collections.sort(this.partidos, new Comparator<Partido>() {
            @Override
            public int compare(Partido p1, Partido p2) {
                int vagas1 = p1.getVagas();
                int vagas2 = p2.getVagas();
                if (vagas1 > vagas2)
                    return -1;
                else if(vagas1 == vagas2)
                	if (p1.getVotosTotal() > p2.getVotosTotal())
                        return -1;
               return 1;
            }
        });
        
    }
    
    public void ordenaPartidosVotos () {
        Collections.sort(this.partidos, new Comparator<Partido>() {
            @Override
            public int compare(Partido p1, Partido p2) {
            	

            	if(!p1.getCandidatos().isEmpty() && !p2.getCandidatos().isEmpty()) {

	                int votos1 = p1.getFirstCandidato();
	                int votos2 = p2.getFirstCandidato();
	                
	                if (votos1 > votos2)
	                    return -1;
	                else if (votos1 == votos2) {
	                	if(p1.getLastCandidato() > p2.getLastCandidato())
	                		return -1;
	                }
                	return 1;        
            	}
            	return 1;
            }
        });        
    }

    private void ordenaCandidatos () {
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

    private void ordenaPartidoCandidatos () {
        for (Partido p : this.partidos) {
            p.ordenaCandidatos();
        }
    }

    public int getCandidatosPorIdade (int limIdadeMin, int limIdadeMax, LocalDate data) {
		int idadeCandidatos = 0;

		for (Partido p: this.partidos) {
			idadeCandidatos += p.getCandidatosPorIdade(limIdadeMin, limIdadeMax, data);
		}

		return idadeCandidatos;
	}
}
