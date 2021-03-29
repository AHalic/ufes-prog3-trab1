package Eleicoes;

/**
 * Esta classe representa a verificacao dos valores lidos na leitura.
 * 
 * @author Beatriz Maia & Sophie Dilhon
 * @version 1.0
 * @since 20/03/2021
 */
public class Verificador {
    // Classe Verificador para analisar se todas as informacoes estao dentro dos limites permitidos.

    /**
     * Metodo para verificar se o genero eh F ou M
     * @param genero - String que sera verificada se eh um genero
     * @return true se eh um genero, false se nao
     */
    private static boolean ehValidoGenero (String genero) {
        if (genero.equals("F") || genero.equals("M"))
            return true;
        return false;
    }

    /**
     * Metodo para verificar se o valor eh maior que 0 (positivo).
     * @param valor
     * @return true se positivo, false se negativo
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
     * @return true se eh uma situacao valida, false caso contrario
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
     * @return true seh eh um destino permitido, false caso contrario
     */
    private static boolean ehValidoDestino (String destVoto) {
        if (destVoto.equals("Válido") || destVoto.equals("Anulado") || destVoto.equals("Anulado sub judice"))
            return true;
        return false;
    }

    /**
     * Metodo para verificar se o valor possui 5 digitos (esta dentro do limite 10000 <= valor < 100000)
     * @param valor
     * @return true se esta dentro do limite, false caso contrario
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
     * @return true se todas informacoes estao dentro do limite, false caso contrario
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
     * @return
     */
    public boolean ehValidoPartido (int votosTotal, int numero) {
        if (ehMinZero(votosTotal) && ehMinZero(numero))
            return true;
        return false;
    }
}
