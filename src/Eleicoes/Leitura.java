package Eleicoes;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Esta classe representa a leitura
 * 
 * @author Beatriz Maia & Sophie Dilhon
 * @version 1.0
 * @since 25/03/2021
 */
public class Leitura {
    /**
     * Faz leitura e parsing da linha com informacoes do partido. Se alguma informação lida não eh valida
     * o partido nao eh criado e o metodo retorna null.
     * @param linhaScanner - Scanner da linha, delimitado por uma String (",")
     * @return Partido construido ou null
     */
    static private Partido criaPartido(Scanner linhaScanner, int counter) {
        int numPartido;
        int votosLegenda;
        String nomePartido = null;
        String sigla = null;

        Partido partido = null;
        try {
            numPartido = Integer.parseInt(linhaScanner.next());
            votosLegenda = Integer.parseInt(linhaScanner.next());
            nomePartido = linhaScanner.next();
            sigla = linhaScanner.next();
        } catch (NoSuchElementException e) {
            return null;
        } catch (NumberFormatException e) {
            return null;
        }

        Verificador ver = new Verificador();
        if (ver.ehValidoPartido(votosLegenda, numPartido, nomePartido, sigla))
            partido = new Partido(nomePartido, sigla, votosLegenda, numPartido);
        else {
            System.out.print("Partido não adicionado, alguma informação inválida.\n");
            System.out.println("Verifique o arquivo de partidos na linha " + counter + ".\n");
        }

        return partido;
    }

    /**
     * Le todas as linhas do arquivo de partidos. Cria uma lista encadeada e adiciona os partidos a ela.
     * @param s - Scanner do arquivo
     * @return Lista encadeada de partidos
     */
    static private LinkedList<Partido> lePartido(Scanner s) {
        LinkedList<Partido> partidos = new LinkedList<Partido>();

        int counter = 2;
        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScannerP = new Scanner(linha);
            linhaScannerP.useDelimiter(",");

            Partido partido = criaPartido(linhaScannerP, counter);

            if (partido != null)
                partidos.add(partido);

            linhaScannerP.close();
            counter++;
        }

        return partidos;
    }

    /**
     * Abre o arquivo no Scanner e adiciona todos os partidos a uma lista encadeada. Linha do arquivo
     * com informação incompleta ou inválida não são utilizadas para criação do partido e consequentemente
     * não adicionados a lista de partidos.
     * @param arquivo - FileInputStream do arquivo de partidos
     * @return Lista encadeada de partidos
     */
    public LinkedList<Partido> abrePartidos(FileInputStream arquivo) {
            Scanner s = new Scanner(arquivo, "UTF-8");
            String linhaStr = s.nextLine();

            Verificador ver = new Verificador();
            if (!ver.ehValidoArquivoPartido(linhaStr)) {
                s.close();
                return null;
            }

            LinkedList<Partido> partidos = lePartido(s);
            s.close();
       
        return partidos;
    }

    /**
     * Faz leitura e parsing da linha com informacoes do candidato. Se alguma informação lida não eh valida
     * o candidato nao eh criado e o metodo retorna null. 
     * @param linhaScanner - Scanner da linha, delimitado por uma String (",")
     * @param formatoData - DateTimeFormatter para fazer parsing do aniversario
     * @param partidos - Partidos existentes
     * @return Candidato com as informações preenchida ou null
     */
    static private Candidato criaCandidato(Scanner linhaScanner, DateTimeFormatter formatoData, LinkedList<Partido> partidos, int counter) {
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
        } catch(NumberFormatException e) {
            return null;
        } catch(NoSuchElementException e) {
            return null;
        }
        
        // Verificador para verificar se todas as informacoes lidas sao validas
        Verificador ver = new Verificador();
        if (!ver.ehValidoCandidato(situacao, nome, nomeUrna, genero, aniversarioStr, destVoto, votosNominais, numero)) {
            System.out.println("Candidato não adicionado, alguma informação inválida.");
            System.out.println("Verifique o arquivo de candidatos na linha: " + counter + ".\n");
            return null;
        }

        try {
            aniversario = LocalDate.parse(aniversarioStr, formatoData);
        } catch (DateTimeParseException e) {
            return null;
        }

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

    /**
     * Le todas as linhas do arquivo de candidatos. Cria uma lista encadeada e adiciona os candidatos a ela.
     * @param s - Scanner do arquivo
     * @param formatoData - Formato de data para aniversarios
     * @param partidos - Lista encadeada dos partidos
     * @return Lista encadeada de candidatos
     */
    static private LinkedList<Candidato> leCandidato(Scanner s, DateTimeFormatter formatoData, LinkedList<Partido> partidos) {
        LinkedList<Candidato> candidatos = new LinkedList<Candidato>();

        int counter = 2;
        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScanner = new Scanner(linha);
            linhaScanner.useDelimiter(",");

            Candidato candidato = criaCandidato(linhaScanner, formatoData, partidos, counter);
            
            if(candidato != null)
            	if(candidato.ehValido())        
            		// Apenas serão considerados os candidatos Válidos
            		candidatos.add(candidato);
            
            linhaScanner.close();
            counter++;
        }

        return candidatos;
    }

    /**
     * Abre o arquivo no Scanner e adiciona todos os partidos a uma lista encadeada. Linha do arquivo com 
     * informações incompletas ou inválidas não são criados candidatos referente a informação daquela 
     * linha e não são adicionados a lista.
     * @param arquivo - FileInputStream do arquivo de candidatos 
     * @param formatoData - DateTimeFormatter para formatar data de aniversario
     * @param partidos - Lista encadeada de partidos
     * @return Lista encadeada de candidatos
     */
    public LinkedList<Candidato> abreCandidato(FileInputStream arquivo, DateTimeFormatter formatoData, 
                                               LinkedList<Partido> partidos) {
            Scanner s = new Scanner(arquivo, "UTF-8");
            String linhaStr = s.nextLine();
            
            Verificador ver = new Verificador();
            if (!ver.ehValidoArquivoCandidato(linhaStr)) {
                s.close();
                return null;
            }

            LinkedList<Candidato> candidatos = leCandidato(s, formatoData, partidos);
            s.close();

        return candidatos;
    }

}
