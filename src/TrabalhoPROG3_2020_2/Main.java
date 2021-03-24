package TrabalhoPROG3_2020_2;
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
		
		// Ler partidos antes
		
		

		// Ler candidatos
		try {
			FileInputStream arquivo = new FileInputStream(caminhoCandidatos);
			Scanner s = new Scanner(arquivo, "UTF-8");
			s.nextLine();

			while (s.hasNextLine()) {
				String linha = s.nextLine();

				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(",");

				int numero = Integer.parseInt(linhaScanner.next());
				int votosNominais = Integer.parseInt(linhaScanner.next());
				String situacao = linhaScanner.next();
				String nome = linhaScanner.next();
				String nomeUrna = linhaScanner.next();
				String genero = linhaScanner.next();

				String aniversarioStr = linhaScanner.next();
        		LocalDate aniversario =  LocalDate.parse(aniversarioStr, formatoData);

				String destVoto = linhaScanner.next();
				int numPartido = Integer.parseInt(linhaScanner.next());

				Candidato candidato = new Candidato(nome, genero, aniversario, situacao, nomeUrna, votosNominais, numero, destVoto);
				// System.out.println(candidato);

				linhaScanner.close();
			}

			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nenhum arquivo foi encontrado");
		}
	}
}
