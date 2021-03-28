package Eleicoes;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Leitura {
    private Partido criaPartido(Scanner linhaScannerP) {
        int numPartido;
        int votosLegenda;
        String nomePartido = null;
        String sigla = null;

        Partido partido = null;
        try {
            numPartido = Integer.parseInt(linhaScannerP.next());
            votosLegenda = Integer.parseInt(linhaScannerP.next());
            nomePartido = linhaScannerP.next();
            sigla = linhaScannerP.next();
        } catch (NoSuchElementException e) {
            return null;
        }

        partido = new Partido(nomePartido, sigla, votosLegenda, numPartido);
        return partido;
    }

    public LinkedList<Partido> lePartido(Scanner s) {
        LinkedList<Partido> partidos = new LinkedList<Partido>();

        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScannerP = new Scanner(linha);
            linhaScannerP.useDelimiter(",");


            Partido partido = criaPartido(linhaScannerP);

            if (partido != null)
                partidos.add(partido);

            linhaScannerP.close();
        }

        return partidos;
    }

    public LinkedList<Partido> abrePartidos(String caminhoPartidos, FileInputStream arquivoP) {
            Scanner s = new Scanner(arquivoP, "UTF-8");
            Leitura leituraPartido = new Leitura();
            s.nextLine();
            LinkedList<Partido> partidos = leituraPartido.lePartido(s);
            s.close();
       
        return partidos;
    }

    public Candidato criaCandidato(Scanner linhaScanner, DateTimeFormatter formatoData, LinkedList<Partido> partidos) {
    	int numero, votosNominais, numPartido;
        String situacao = null, nome = null;
        String nomeUrna = null, genero = null;
        String aniversarioStr = null, destVoto = null;
        LocalDate aniversario;

        Candidato candidato = null;

        // Faz leitura do candidato e verifica se está de acordo com a formatação
        try {
	        numero = Integer.parseInt(linhaScanner.next());
	        votosNominais = Integer.parseInt(linhaScanner.next());
	        situacao = linhaScanner.next();
	        nome = linhaScanner.next();
	        nomeUrna = linhaScanner.next();
	        genero = linhaScanner.next();
	        aniversarioStr = linhaScanner.next();
	        destVoto = linhaScanner.next();
	        numPartido = Integer.parseInt(linhaScanner.next());
    	} catch(NoSuchElementException e) {
        	return null;
        } catch(NumberFormatException e) {
            return null;
        }
        
        aniversario = LocalDate.parse(aniversarioStr, formatoData);

        for(Partido p: partidos) {
        	// Verifica se existe o partido com mesmo número do presente no candidato
            if(p.getNumPartido() == numPartido) {
                candidato = new Candidato(nome, genero, aniversario, situacao, nomeUrna, votosNominais, numero,
                destVoto, p);
                
                // Apenas serão considerados os candidatos Válidos
                if(candidato.ehValido())
                	p.insereCandidato(candidato);
            }
        }

        
        return candidato;
    }

    public LinkedList<Candidato> leCandidato(Scanner s, DateTimeFormatter formatoData, LinkedList<Partido> partidos) {
        LinkedList<Candidato> candidatos = new LinkedList<Candidato>();

        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScanner = new Scanner(linha);
            linhaScanner.useDelimiter(",");

            Candidato candidato = criaCandidato(linhaScanner, formatoData, partidos);
            
            if(candidato != null)
            	if(candidato.ehValido())        
            		// Apenas serão considerados os candidatos Válidos
            		candidatos.add(candidato);
            linhaScanner.close();
        }

        return candidatos;
    }

    public LinkedList<Candidato> abreCandidato(String caminhoCandidatos, FileInputStream arquivo, DateTimeFormatter formatoData, LinkedList<Partido> partidos) {
            Scanner s = new Scanner(arquivo, "UTF-8");
            s.nextLine();
            LinkedList<Candidato> candidatos = leCandidato(s, formatoData, partidos);
            s.close();

        return candidatos;
    }
}
