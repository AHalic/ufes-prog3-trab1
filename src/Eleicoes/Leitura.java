package Eleicoes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Leitura {
    private Partido criaPartido(Scanner linhaScannerP) {
        int numPartido = Integer.parseInt(linhaScannerP.next());
        int votosLegenda = Integer.parseInt(linhaScannerP.next());
        String nomePartido = linhaScannerP.next();
        String sigla = linhaScannerP.next();
        Partido partido = new Partido(nomePartido, sigla, votosLegenda, numPartido);

        return partido;
    }

    private Set<Partido> lePartido(Scanner s) {
        Set<Partido> partidos = new HashSet<Partido>();
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

    public Set<Partido> abrePartidos(String caminhoPartido) {
        try {
            FileInputStream arquivoP = new FileInputStream(caminhoPartidos);
            Scanner sP = new Scanner(arquivoP, "UTF-8");
            Leitura leituraPartido = new Leitura();
            Set<Partido> partidos = leituraPartido.lerPartido(sP);
            sP.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo partido não foi encontrado");
        }

        return partidos;
    }

    public Candidato criaCandidato(Scanner linhaScanner) {
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

        Candidato candidato = new Candidato(nome, genero, aniversario, situacao, nomeUrna, votosNominais, numero,
                destVoto);
        return candidato;
    }

    public Set<Candidato> leCandidato(Scanner s) {
        Set<Candidato> candidatos = new HashSet<Candidato>();
        s.nextLine();

        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScanner = new Scanner(linha);
            linhaScanner.useDelimiter(",");

            Candidato candidato = criaCandidato(linhaScanner);
            candidatos.add(candidato);

            linhaScanner.close();
        }

        return candidatos;
    }

    public Set<Candidato> abreCandidato(String caminhoCandidatos) {
        try {
            FileInputStream arquivo = new FileInputStream(caminhoCandidatos);
            Scanner s = new Scanner(arquivo, "UTF-8");
            s.nextLine();
            Set<Candidato> candidatos = leCandidato(s);
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo candidato não foi encontrado");
        }

        return candidatos;
    }
}
