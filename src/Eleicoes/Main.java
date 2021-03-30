package Eleicoes;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
	public static void main(String[] args) {
        String caminhoCandidatos, caminhoPartidos, dataEleicaoStr;

		try {
            caminhoCandidatos = args[0];
            caminhoPartidos = args[1];
            dataEleicaoStr = args[2];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Valores de entrada não informados por completo.");
            System.out.println("Mensagem do erro: " + e.getLocalizedMessage() + ".");
            return;
        }
        
        // Data da eleicao
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEleicao = null;

        try {
            dataEleicao =  LocalDate.parse(dataEleicaoStr, formatoData);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data incorreto");
            System.out.println("Mensagem do erro: " + e.getLocalizedMessage() + ".");
            return;
        }
		
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
            System.out.println("Arquivo não encontrado.");
            System.out.println("Mensagem do erro: " + e.getLocalizedMessage() + ".");
            return;
        } catch (IOException e) {
            System.out.println("Problema relacionado a IO.");
            System.out.println("Mensagem do erro: " + e.getLocalizedMessage() + ".");
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
