package TrabalhoPROG3_2020_2;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("oi");

		String caminhoCandidatos = new String();
		caminhoCandidatos = args[0];
		String caminhoPartidos = new String();

		caminhoPartidos = args[1];
		String dataEleicaoStr = args[2];
	
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEleicao =  LocalDate.parse(dataEleicaoStr, formatoData);
		System.out.println(caminhoCandidatos);

		System.out.println(caminhoCandidatos + "\n" + caminhoPartidos + "\n" + dataEleicaoStr + "\n" + dataEleicao);
		
		try {
			FileInputStream arquivo = new FileInputStream(caminhoCandidatos);
			Scanner s = new Scanner(arquivo, "UTF-8");

			int soma = 0;
			int contagem=0;
			while (s.hasNextLine()) {
				String linha = s.nextLine();

				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(";");
				while (linhaScanner.hasNextLine()) {
					System.out.println(linhaScanner.nextLine());
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Nenhum arquivo foi encontrado");
		}
	}
}
