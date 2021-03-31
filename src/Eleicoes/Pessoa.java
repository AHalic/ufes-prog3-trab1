package Eleicoes;

import java.time.LocalDate;
import java.time.Period;

/**
 * Esta classe representa uma Pessoa
 * 
 * @author Beatriz Maia & Sophie Dilhon
 * @version 1.0
 * @since 20/03/2021
 */
public class Pessoa {
	private String nome;
	private String genero;
	private LocalDate nascimento;
	
	/**
	 * Constructor da classe Pessoa
	 * @param nome - nome desta Pessoa
	 * @param genero - pode ser F ou M
	 * @param nascimento - data de nascimento desta Pessoa 
	 */
	public Pessoa(String nome, String genero, LocalDate nascimento) {
		this.nome = nome;
		this.genero = genero;
		this.nascimento = nascimento;
	}
	
	/**
	 * Retorna o nome desta Pessoa
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna o genero desta Pessoa
	 * @return genero
	 */
	public String getGenero() {
		return genero;
	}
	
	/**
	 * Retorna o nascimento desta Pessoa
	 * @return nascimento
	 */
	public LocalDate getNascimento() {
		return nascimento;
	}
	
	/**
	 * Verifica se o genero desta Pessoa é F 
	 * @return {@code true} se sim, {@code false} se não
	 */
	public boolean ehGeneroF () {
		if (this.genero.equals("F"))
			return true;
		else 
			return false;
	}
	
	/**
	 * Retorna uma string de representação desta Pessoa
	 * @return nome nome desta Pessoa em maiúsculo
	 */
	@Override
	public String toString () {
		return this.nome.toUpperCase();
	}

	/**
	 * Retorna a idade desta Pessoa com base em uma data
	 * @param dia - data para cálculo da idade
	 * @return idade 
	 */
	public int getIdade (LocalDate dia) {
		return Period.between(this.nascimento, dia).getYears();
	}
}
