package Eleicoes;

public class Relatorio {
    Eleicao eleicao;

    public Relatorio (Eleicao eleicao) {
        this.eleicao = eleicao;
    }
    
    public void numeroDeVagas () {
        System.out.println("Número de vagas: " + eleicao.getVagas());
        System.out.println();
    }

    public void vereadoresEleitos () {
        int contador = 1;

        System.out.println("Vereadores eleitos:");
        for (Candidato c: eleicao.getCandidatos()) {
            if (c.ehEleito()) {
                System.out.println(contador + " - " + c);
                contador++;

                if (contador == eleicao.getVagas() + 1)
                    break;
            }
        }

        System.out.println();
    }

    public void candidatosMaisVotados () {
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

    public void naoEleitosMajoritario () {
        int contador = 1;

        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:");
        System.out.println("(com sua posição no ranking de mais votados)");

        for (Candidato c: eleicao.getCandidatos()) {
            if (!c.ehEleito())
                System.out.println(contador + " - " + c);
            
            contador++;
            
            if (contador == eleicao.getVagas() + 1)
                break;
        }

        System.out.println();
    }

    public void eleitosBeneficiados () {
        int contador;
    }

    public void infoPartidos() {
        int contador = 1;
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        for (Partido p: eleicao.getPartidos()) {
            System.out.println(contador + " - " + p); 
            contador++;
        }

        System.out.println();
    }

    public void primeiroUltimoPartido () {
        System.out.println("Primeiro e último colocados de cada partido:");
        for (Partido p: eleicao.getPartidos()) {
            if (p.getVotosTotal() > 0) {
                System.out.print( p.getSigla() + " - " + p.getNumPartido() + ", ");

                Candidato prim = p.getCandidatos().getFirst();
                Candidato ult = p.getCandidatos().getLast();

                System.out.print(prim.getNomeUrna() + " (" + prim.getNumero() + ", " + prim.getVotosTotal() + " votos) /");
                System.out.println(ult.getNomeUrna() + " (" + ult.getNumero() + ", " + ult.getVotosTotal() + " votos)");                
            }
        }

        System.out.println();
    }

    public void votosEleicao() {
    	int total, nominal, legenda;
    	
    	total = eleicao.getVotosTotais();
    	nominal = eleicao.getVotosNominais();
    	legenda = eleicao.getVotosLegenda();
    	
    	
    	System.out.println("Total de votos válidos:    " + total);
    	System.out.printf("Total de votos nominais:    %d (%.2f%%)\n",  nominal, ((float) nominal*100/total));
    	System.out.printf("Total de votos nominais:    %d (%.2f%%)\n",  legenda, ((float) legenda*100/total));
    }
}