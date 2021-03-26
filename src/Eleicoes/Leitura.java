package Eleicoes;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Leitura {
    private Partido criaPartido(Scanner linhaScannerP) {
        int numPartido = Integer.parseInt(linhaScannerP.next());
        int votosLegenda = Integer.parseInt(linhaScannerP.next());
        String nomePartido = linhaScannerP.next();
        String sigla = linhaScannerP.next();
        Partido partido = new Partido(nomePartido, sigla, votosLegenda, numPartido);
        return partido;
    }

    public LinkedList<Partido> lePartido(Scanner s) {
        LinkedList<Partido> partidos = new LinkedList<Partido>();

        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScannerP = new Scanner(linha);
            linhaScannerP.useDelimiter(",");

            Partido partido = criaPartido(linhaScannerP);
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
        int numero = Integer.parseInt(linhaScanner.next());
        int votosNominais = Integer.parseInt(linhaScanner.next());
        String situacao = linhaScanner.next();
        String nome = linhaScanner.next();
        String nomeUrna = linhaScanner.next();
        String genero = linhaScanner.next();

        String aniversarioStr = linhaScanner.next();
        LocalDate aniversario = LocalDate.parse(aniversarioStr, formatoData);

        String destVoto = linhaScanner.next();
        int numPartido = Integer.parseInt(linhaScanner.next());

        Candidato candidato = null;
        try { 
            for(Partido p: partidos) {
                if(p.getNumPartido() == numPartido) {
                    candidato = new Candidato(nome, genero, aniversario, situacao, nomeUrna, votosNominais, numero,
                    destVoto, p);
                    
                    // Apenas serão considerados os candidatos Válidos
                    if(candidato.ehValido())
                    	p.insereCandidato(candidato);
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Partido " + numPartido + " não existe!"); 
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
            
            // Apenas serão considerados os candidatos Válidos
            if(candidato.ehValido())        
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
