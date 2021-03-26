package Eleicoes;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class Leitura {
    private Partido criaPartido(Scanner linhaScannerP) {
        int numPartido = Integer.parseInt(linhaScannerP.next());
        int votosLegenda = Integer.parseInt(linhaScannerP.next());
        String nomePartido = linhaScannerP.next();
        String sigla = linhaScannerP.next();
        Partido partido = new Partido(nomePartido, sigla, votosLegenda, numPartido);
//        System.out.println(nomePartido);
        return partido;
    }

    public LinkedList<Partido> lePartido(Scanner s) {
        LinkedList<Partido> partidos = new LinkedList<Partido>();
        s.nextLine();

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

            Scanner sP = new Scanner(arquivoP, "UTF-8");
            Leitura leituraPartido = new Leitura();
            LinkedList<Partido> partidos = leituraPartido.lePartido(sP);
            sP.close();
       
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
                    p.insereCandidato(candidato);
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Partido " + numPartido + " não existe!"); 
        }
        
        return candidato;
    }

    public Set<Candidato> leCandidato(Scanner s, DateTimeFormatter formatoData, LinkedList<Partido> partidos) {
        Set<Candidato> candidatos = new HashSet<Candidato>();
        s.nextLine();

        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScanner = new Scanner(linha);
            linhaScanner.useDelimiter(",");

            Candidato candidato = criaCandidato(linhaScanner, formatoData, partidos);
            candidatos.add(candidato);

            linhaScanner.close();
        }

        return candidatos;
    }

    public Set<Candidato> abreCandidato(String caminhoCandidatos, FileInputStream arquivo, DateTimeFormatter formatoData, LinkedList<Partido> partidos) {

            Scanner s = new Scanner(arquivo, "UTF-8");
            s.nextLine();
            Set<Candidato> candidatos = leCandidato(s, formatoData, partidos);
            s.close();

        return candidatos;
    }
}
