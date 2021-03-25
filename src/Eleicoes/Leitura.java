package Eleicoes;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Leitura {
    String caminhoLeitura;

    public Partido criaPartido (Scanner linhaScannerP) {
        int numPartido = Integer.parseInt(linhaScannerP.next());
        int votosLegenda = Integer.parseInt(linhaScannerP.next());
        String nomePartido = linhaScannerP.next();
        String sigla = linhaScannerP.next();
        Partido partido = new Partido(nomePartido, sigla, votosLegenda, numPartido);

        return partido;
    }

    public Set<Partido> lerPartido (Scanner sP) {
        Set<Partido> partidos = new HashSet<Partido>();

        while (sP.hasNextLine()) {
            String linha = sP.nextLine();

            Scanner linhaScannerP = new Scanner(linha);
            linhaScannerP.useDelimiter(",");

            Partido partido = criaPartido(linhaScannerP);
            partidos.add(partido);
            
            linhaScannerP.close();
        }

        return partidos;
    }
}
