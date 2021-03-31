package Eleicoes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Esta classe representa a Eleicao
 * 
 * @author Beatriz Maia & Sophie Dilhon
 * @version 1.0
 * @since 27/03/2021
 */
public class Eleicao {
	private LocalDate dataEleicao;
    private Votos votos;
    private int vagas;
	private LinkedList<Candidato> candidatos;
	private LinkedList<Partido> partidos; 

	/**
	 * Constructor da classe Eleicao
	 * @param dataEleicao - data em que esta eleição foi realizada
	 * @param candidatos - lista de candidatos participando desta eleição
	 * @param partidos - lista de partidos presentes nesta eleição
	 */
    public Eleicao (LocalDate dataEleicao, LinkedList<Candidato> candidatos, LinkedList<Partido> partidos) {
        this.dataEleicao = dataEleicao;
        this.partidos = partidos;
        this.candidatos = candidatos;
        
        this.calculaVotosTotaisPartidos();
        this.totalVotos();
        this.calculaQtdVagas();
        this.ordenaPartidos();
        this.ordenaCandidatos();
        this.ordenaPartidoCandidatos();
    }

    /**
     * Retorna a data em que a eleição foi realizada
     * @return data da eleição
     */
    public LocalDate getDataEleicao () {
        return dataEleicao;
    }

    /**
     * Retorna a quantidade de votos nominais 
     * obtidas nesta eleição
     * @return votos nominais
     */
    public int getVotosNominais () {
        return votos.getVotosNominais();
    }

    /**
     * Retorna a quantidade de votos de legenda
     * obtidos nesta eleição
     * @return votos de legenda
     */
    public int getVotosLegenda () {
        return votos.getVotosLegenda();
    }

    /**
     * Retorna a quantidade de votos totais
     * obtidas nesta eleição
     * @return votos totais
     */
    public int getVotosTotais () {
        return votos.getVotosTotais();
    }

    /**
     * Retorna a quantidade de candidatos eleitos nesta eleição
     * @return vagas desta eleição
     */
    public int getVagas () {
        return vagas;
    }
    
    /**
     * Modifica a quantidade total de votos obtidos
     * nesta eleição (soma dos votos totais de legenda
     * e votos nominais obtidos pelos partidos)
     */
    private void totalVotos() {
    	int votosNominais = 0, votosLegenda = 0;
    	
    	for(Partido partido : this.partidos) {
    		votosLegenda += partido.getVotosLegenda();
    		votosNominais += partido.getVotosNominais(); 
    	}
    	
        this.votos = new Votos(votosNominais, votosLegenda);
    }

    /**
     * Retorna a lista de candidatos desta Eleição
     * @return lista de candidatos
     */
    public LinkedList<Candidato> getCandidatos () {
        return this.candidatos;
    }

    /**
     * Retorna a lista de partido desta Eleição
     * @return lista de partidos
     */
    public LinkedList<Partido> getPartidos () {
        return this.partidos;
    }

    /**
     * Adiciona um candidato a lista de candidatos desta Eleição
     * @param candidatoEleicao - candidato que será adicionado
     */
    public void insereCandidato (Candidato candidatoEleicao) {
        this.candidatos.add(candidatoEleicao);
    }

    /**
     * Adiciona um partido a lista de partidos desta Eleição
     * @param partidoEleicao - partido que será adicionado
     */
    public void inserePartido (Partido partidoEleicao) {
        this.partidos.add(partidoEleicao);
    }

    /**
     * Calcula a quantidade de candidatos nesta eleição
     * de genero feminino
     * @return quantidade de F
     */
    public int calculaQtdF() {
    	int F = 0;
    	
    	for(Candidato c : this.candidatos) {
    		if(c.ehGeneroF() && c.ehEleito())
    			F++;
    	}
    	
    	return F;
    }
    
    /**
     * Calcula a quantidade de candidatos eleitos
     * e modifica a quantidade de vagas desta Eleição
     */
     private void calculaQtdVagas () {
        int qtdVagas = 0;

        for (Partido p : this.partidos) {
        	qtdVagas += p.getVagas();
        }
        this.vagas = qtdVagas;
    }
 
     /**
      * Calcula e modifica a quantidade total de votos 
      * nominais de cada partido desta eleição
      */
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

    /**
     * Ordena a lista de partidos desta Eleição com base na quantidade
     * total de votos dos partidos, no caso de empate com base no numero deles
     */
    public void ordenaPartidos () {
    	// Verificando se a lista de partidos não é vazia
    	if(!this.partidos.isEmpty()) {
    		
	        Collections.sort(this.partidos, new Comparator<Partido>() {
	            @Override
	            public int compare(Partido p1, Partido p2) {
	                int votos1 = p1.getVotosTotal();
	                int votos2 = p2.getVotosTotal();
	                if (votos1 > votos2)
	                    return -1;
	                else if(votos1 == votos2)
	                	if (p1.getNumPartido() < p2.getNumPartido())
	                        return -1;
	               return 1;
	            }
	        });
    	}
    }
    
    /**
     * Ordena a lista de partidos desta Eleição com base na quatidade de 
     * votos do primeiro candidato de cada partido, e no caso de empate
     * com base no numero do candidato
     */
    public void ordenaPartidosVotosCandidatos () {
    	
    	// Verificando se a lista de partidos não é vazia
    	if(!this.partidos.isEmpty()) {
    		
	        Collections.sort(this.partidos, new Comparator<Partido>() {
	            @Override
	            public int compare(Partido p1, Partido p2) {
	            	
	            	// Verificando se as listas de candidatos dos partidos não são vazias
	            	if(!p1.getCandidatos().isEmpty() && !p2.getCandidatos().isEmpty()) {
	
		                int votos1 = p1.getVotosFirstCandidato();
		                int votos2 = p2.getVotosFirstCandidato();
		                
		                if (votos1 > votos2)
		                    return -1;
		                else if (votos1 == votos2) {
		                	if(p1.getNumFirstCandidato() < p2.getNumFirstCandidato())
		                		return -1;
		                }
	                	return 1;        
	            	}
	            	return 1;
	            }
	        });        
    	}
    }

    /**
     * Ordena a lista de candidatos desta Eleição com base na quantidade
     * votos deles, e no caso de empate com base na idade deles
     */
    private void ordenaCandidatos () {

    	if(!this.candidatos.isEmpty()) {
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
    }

    /**
     * Ordena a lista de candidatos de cada partido 
     * da lista de partidos desta Eleição
     */
    private void ordenaPartidoCandidatos () {
        for (Partido p : this.partidos) {
            p.ordenaCandidatos(this.dataEleicao);
        }
    }

    /**
     * Retorna a quantidade de candidatos com idade em um intervalo definido
     * @param limIdadeMin - menor idade do intervalo
     * @param limIdadeMax - maior idade do intervalo
     * @param data - data para calculo da idade
     * @return quantidade de candidatos no intervalo
     */
    public int getCandidatosPorIdade (int limIdadeMin, int limIdadeMax, LocalDate data) {
		int idadeCandidatos = 0;

		for (Partido p: this.partidos) {
			idadeCandidatos += p.getCandidatosPorIdade(limIdadeMin, limIdadeMax, data);
		}

		return idadeCandidatos;
	}
}
