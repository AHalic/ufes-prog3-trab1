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
		
        
        // Lê partidos
        Leitura leitura = new Leitura();

        try {
            FileInputStream arquivoP = new FileInputStream(caminhoPartidos);
            Set<Partido> partidos = leitura.abrePartidos(caminhoPartidos, arquivoP);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo partido não foi encontrado");
        }

        
		// Lê candidatos
		try {
			FileInputStream arquivoC = new FileInputStream(caminhoCandidatos);
			Set<Candidato> candidato = leitura.abreCandidato(caminhoPartidos, arquivoC, formatoData);

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo candidato não foi encontrado");
		}
		
		
	}
}
