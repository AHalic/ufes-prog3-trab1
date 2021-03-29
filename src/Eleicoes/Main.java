package Eleicoes;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		String caminhoCandidatos = new String();
		caminhoCandidatos = args[0];

		String caminhoPartidos = new String();
		caminhoPartidos = args[1];

		String dataEleicaoStr = args[2];
	
        // Data da eleicao
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEleicao =  LocalDate.parse(dataEleicaoStr, formatoData);
		
        // Listas de partido e candidatos
        LinkedList<Partido> partidos;
        LinkedList<Candidato> candidatos;
               
        // Tenta abrir os arquivos, se conseguir faz a leitura
        Leitura leitura = new Leitura();

        try {
            FileInputStream arquivoP = new FileInputStream(caminhoPartidos);
            FileInputStream arquivoC = new FileInputStream(caminhoCandidatos);
            partidos = leitura.abrePartidos(arquivoP);
            candidatos = leitura.abreCandidato(arquivoC, formatoData, partidos);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não foi encontrado");
            return;
        }
        
        // Conta os votos nominais de cada partido
        for(Partido p : partidos) {
        	p.setVotosTotais();
        }
      
        // Cria a eleição
		Eleicao eleicao = new Eleicao(dataEleicao, candidatos, partidos);

        // Cria relatorio
        Relatorio relatorio = new Relatorio(eleicao);

        // Faz relatorios
        relatorio.numeroDeVagas();
        relatorio.vereadoresEleitos();
        relatorio.candidatosMaisVotados();
        relatorio.naoEleitosMajoritario();
        relatorio.eleitosBeneficiados();
        relatorio.infoPartidos();
        relatorio.primeiroUltimoPartido();
        relatorio.eleitosPorIdade();
        relatorio.eleitosPorGenero();
        relatorio.votosEleicao();
	}
}
