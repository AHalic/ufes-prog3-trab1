package Eleicoes;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
	public static void main(String[] args) {
        String caminhoCandidatos, caminhoPartidos, dataEleicaoStr;

        // Tenta acessar os args
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
        LinkedList<Partido> partidos = null;
        LinkedList<Candidato> candidatos = null;
               
        // Tenta abrir os arquivos, se conseguir faz a leitura
        Leitura leitura = new Leitura();

        try {
            FileInputStream arquivoP = new FileInputStream(caminhoPartidos);
            FileInputStream arquivoC = new FileInputStream(caminhoCandidatos);
            partidos = leitura.abrePartidos(arquivoP);
            // Se partidos for null, nao pode ler candidatos
            if (partidos!= null)
                candidatos = leitura.abreCandidato(arquivoC, formatoData, partidos);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            System.out.println("Mensagem do erro: " + e.getLocalizedMessage() + ".");
            return;
        } 

        if (candidatos == null || partidos == null) {
            System.out.println("Informações não possibilitadas de inicializar.");
            return;
        }
            
        // Cria a eleição
		Eleicao eleicao = new Eleicao(dataEleicao, candidatos, partidos);

        // Cria relatorio
        Relatorio relatorio = new Relatorio(eleicao);

        // Faz relatorios
        relatorio.numeroDeVagas();
        relatorio.vereadoresEleitos();
        relatorio.candidatosMaisVotados();      
        int nEleitos = relatorio.naoEleitosMajoritario();
        relatorio.eleitosBeneficiados(nEleitos);
        relatorio.infoPartidos();
        relatorio.primeiroUltimoPartido();
        relatorio.eleitosPorIdade();
        relatorio.eleitosPorGenero();
        relatorio.votosEleicao();
	}
}
