package Eleicoes;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Esta classe representa a verificacao dos valores lidos na leitura.
 * 
 * @author Beatriz Maia & Sophie Dilhon
 * @version 1.0
 * @since 29/03/2021
 */
public class Verificador {
    // Classe Verificador para analisar se todas as informacoes estao dentro dos limites permitidos.

    /**
     * Metodo para verificar se o genero eh F ou M
     * @param genero - String que sera verificada se eh um genero
     * @return {@code true} se eh um genero, {@code false} se nao
     */
    private static boolean ehValidoGenero (String genero) {
        if (genero.equals("F") || genero.equals("M"))
            return true;
        return false;
    }

    /**
     * Metodo para verificar se o valor eh maior que 0 (positivo).
     * @param valor
     * @return {@code true} se positivo, {@code false} se negativo
     */
    private static boolean ehMinZero (int valor) {
        if (valor >= 0)
            return true;
        return false;
    }

    /**
     * Metodo para verificar se a String situacao eh um dos tres valores:
     * <ul>
     *     <li> Eleito </li>
     *     <li> Não eleito </li>
     *     <li> Suplente </li>
     * </ul>
     * Nao ha outro tipo de situacao permitida.
     * @param situacao String da situacao
     * @return {@code true} se eh uma situacao valida, {@code false} caso contrario
     */
    private static boolean ehValidoSituacao (String situacao) {
        if (situacao.equals("Eleito") || situacao.equals("Não eleito") || situacao.equals("Suplente"))
            return true;
        return false;
    }

    /**
     * Metodo para verificar se a String destVoto eh um dos tres valores:
     * <ul>
     *     <li> Válido </li>
     *     <li> Anulado </li>
     *     <li> Anulado sub judice </li>
     * </ul>
     * @param destVoto String da condicao do destino do voto
     * @return {@code true} se eh um destino permitido, {@code false} caso contrario
     */
    private static boolean ehValidoDestino (String destVoto) {
        if (destVoto.equals("Válido") || destVoto.equals("Anulado") || destVoto.equals("Anulado sub judice"))
            return true;
        return false;
    }

    /**
     * Metodo para verificar se o valor possui 5 digitos (esta dentro do limite 10000 <= valor < 100000)
     * @param valor
     * @return {@code true} se esta dentro do limite, {@code false} caso contrario
     */
    private static boolean eh5Digitos (int valor) {
        if (valor < 100000 && valor >= 10000)
            return true;
        return false;
    }

    /**
     * Metodo para verificar se as informacoes lida estao dentro do limite permitivel.
     * @param situacao - String da situacao do candidato
     * @param nome - String do nome do candidato
     * @param nomeUrna - String do nome de urna do candidato
     * @param genero - String do genero do candidato
     * @param aniversarioStr - String do aniversario do candidato
     * @param destVoto - String da situacao do destino do voto do candidato
     * @param voto - Quantidade de votos do candidato
     * @param numero - Numero (ID) do candidato  
     * @return {@code true} se todas informacoes estao dentro do limite, {@code false} caso contrario
     */
    public boolean ehValidoCandidato (String situacao, String nome, String nomeUrna, String genero, 
                                      String aniversarioStr, String destVoto, int voto, int numero) {
        // Verifica se todas as strings lidas estão preenchidas
        if (situacao.isBlank() || nome.isBlank() || nomeUrna.isBlank() || 
            genero.isBlank() || aniversarioStr.isBlank() || destVoto.isBlank())
            return false;
        
        // Verifica se o genero eh F ou M
        if (!ehValidoGenero(genero))
            return false;
        
        // Verifica se o voto eh no minimo 0
        if(!ehMinZero(voto) || !ehMinZero(numero))
            return false;

        // Verifica se a situacao eh Eleito, Não eleito ou Suplente
        if (!ehValidoSituacao(situacao))
            return false;
    
        // Verifica se a situacao eh Valido, Anulado ou Anula sub judice
        if (!ehValidoDestino(destVoto))
            return false;

        // Verifica se o numero do candidato possue 5 digitos
        if (!eh5Digitos(numero))
            return false;
        return true;
    }

    /**
     * Metodo para verificar se as informacoes lidas sobre partido sao validas.
     * @param votosTotal - Quantidade de votos de legenda
     * @param numero - Numero do partido
     * @return {@code true} se o partido apresenta informacoes validas, {@code false} se nao
     */
    public boolean ehValidoPartido (int votosTotal, int numero, String nomePartido, String sigla) {
        // Verifica se todas as Strings sao preenchidas
        if (nomePartido.isEmpty() || sigla.isEmpty())
            return false;

        // Verifica se todos os inteiros sao positivos
        if (ehMinZero(votosTotal) && ehMinZero(numero))
            return true;
        return false;
    }

    /**
     * Verifica se o cabecalho do arquivo de candidato esta correto
     * @param linhaStr string da linha referente ao cabecalho
     * @return {@code true} se eh igual ao cabecalho, {@code false} se estiver diferente
     */
    public boolean ehValidoArquivoCandidato (String linhaStr) {
        /*
         Ver se o cabeçalho do arquivo esta nessa ordem:
         numero,votos_nominais,situacao,nome,nome_urna,sexo,data_nasc,destino_voto,numero_partido
        */
        String numero, votos, situacao, nome, nome_urna, sexo, data_nasc, destino_voto, num_partido;

        try {
            Scanner linha = new Scanner(linhaStr);
            linha.useDelimiter(",");

            numero = linha.next();                
            votos = linha.next();
            situacao = linha.next();
            nome = linha.next();
            nome_urna = linha.next();
            sexo = linha.next();
            data_nasc = linha.next();
            destino_voto = linha.next();
            num_partido = linha.next();

            linha.close();                
        } catch (NoSuchElementException e) {
            System.out.println("Arquivo incorreto");
            return false;
        } 

        if(!votos.equals("votos_nominais") || !numero.equals("numero") 
            || !situacao.equals("situacao") || !nome.equals("nome") 
            || !nome_urna.equals("nome_urna") || !sexo.equals("sexo")
            || !data_nasc.equals("data_nasc") || !destino_voto.equals("destino_voto")
            || !num_partido.equals("numero_partido"))
            return false;
        return true;
    }

    /**
     * Verifica se o cabecalho do arquivo de partidos esta correto
     * @param linhaStr string da linha referente ao cabecalho
     * @return {@code true} se eh igual ao cabecalho, {@code false} se estiver diferente
     */
    public boolean ehValidoArquivoPartido (String linhaStr) {
        /*
         Ver se o cabeçalho do arquivo esta nessa ordem:
        numero_partido,votos_legenda,nome_partido,sigla_partido
        */
        String numero, votos, nome, sigla;

        try {
            Scanner linha = new Scanner(linhaStr);
            linha.useDelimiter(",");

            numero = linha.next();                
            votos = linha.next();
            nome = linha.next();
            sigla = linha.next();

            linha.close();
        } catch (NoSuchElementException e) {
            System.out.println("Arquivo incorreto");
            return false;
        } 

        if(!votos.equals("votos_legenda") || !nome.equals("nome_partido") 
           || !numero.equals("numero_partido") || !sigla.equals("sigla_partido"))
            return false;

        return true;
    }
}
