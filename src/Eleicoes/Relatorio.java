package Eleicoes;

public class Relatorio {
    
    public void numeroDeVagas (Eleicao eleicao) {
        System.out.println("Número de vagas: " + eleicao.getVagas());
        System.out.println();
    }

    public void candidatosMaisVotados (Eleicao eleicao) {
        int contador = 1;
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for (Candidato c: eleicao.getCandidatos()) {
            System.out.println(contador + " - " + c); 
            contador++;

            if (contador == eleicao.getVagas() + 1)
                break;
        }

        System.out.println();
    }

    public void infoPartidos(Eleicao eleicao) {
        int contador = 1;
        System.out.println("\nVotação dos partidos e número de candidatos eleitos:");
        for (Partido p: eleicao.getPartidos()) {
            System.out.println(contador + " - " + p); 
            contador++;
        }
    }
}
