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
	
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEleicao =  LocalDate.parse(dataEleicaoStr, formatoData);
		
        Set<Partido> partidos;
        Set<Candidato> candidatos;
        
        
        // Tenta abrir os arquivos, se conseguir faz a leitura
        Leitura leitura = new Leitura();

        try {
            FileInputStream arquivoP = new FileInputStream(caminhoPartidos);
            FileInputStream arquivoC = new FileInputStream(caminhoCandidatos);
            partidos = leitura.abrePartidos(caminhoPartidos, arquivoP);
            candidatos = leitura.abreCandidato(caminhoPartidos, arquivoC, formatoData, partidos);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não foi encontrado");
            return;
        }
        
        
        // Conta os votos nominais de cada partido
        for(Partido p : partidos) {
        	p.getVotosTotais();
        }
      
        // Cria a eleição
		Eleicao eleicao = new Eleicao(dataEleicao, candidatos, partidos);
		
	}
}
