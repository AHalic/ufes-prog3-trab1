package Eleicoes;

import java.text.DecimalFormat;

public class Relatorio {
    private Eleicao eleicao;

    public Relatorio (Eleicao eleicao) {
        this.eleicao = eleicao;
    }
    
    static private String porcentagem (int valor, int total) {
        double porcentagem = (double) valor * 100 / total;

        DecimalFormat formatoDecimal = new DecimalFormat("#.00");
        String porcentagemStr = formatoDecimal.format(porcentagem);     
        
        porcentagemStr = porcentagemStr.replace('.', ',');
        porcentagemStr = porcentagemStr + "%";

        return porcentagemStr;
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
        int contador = 16;

        System.out.println("Eleitos, que se beneficiaram do sistema proporcional:");
        System.out.println("(com sua posição no ranking de mais votados)");

        for (Candidato c: eleicao.getCandidatos().subList(15, eleicao.getCandidatos().size())) {
            if (c.ehEleito())
                System.out.println(contador + " - " + c);
            
            contador++;
        }

        System.out.println();
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
    	int cont = 1;
        System.out.println("Primeiro e último colocados de cada partido:");
        
        eleicao.ordenaPartidosVotos();
        
        for (Partido p: eleicao.getPartidos()) {
            if (p.getVotosTotal() > 0) {
                System.out.print(cont + " - " + p.getSigla() + " - " + p.getNumPartido() + ", ");

                Candidato prim = p.getCandidatos().getFirst();
                Candidato ult = p.getCandidatos().getLast();         

                System.out.print(prim.shortToString() + " / ");
                System.out.println(ult.shortToString());
                
                cont++;
            }
        }

        System.out.println();
    }

    public void eleitosPorIdade () {
        int qtdMenos30 = this.eleicao.getCandidatosPorIdade(0, 30, eleicao.getDataEleicao());
        int qtdMenos40 = this.eleicao.getCandidatosPorIdade(30, 40, eleicao.getDataEleicao()); 
        int qtdMenos50 = this.eleicao.getCandidatosPorIdade(40, 50, eleicao.getDataEleicao());
        int qtdMenos60 = this.eleicao.getCandidatosPorIdade(50, 60, eleicao.getDataEleicao());
        int qtdMaiorIgual60 = this.eleicao.getCandidatosPorIdade(60, 1000, eleicao.getDataEleicao());
        int qtdCandidatos = this.eleicao.getVagas();

        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.printf("Idade < 30      : %d (%s)\n", qtdMenos30, porcentagem(qtdMenos30, qtdCandidatos));
        System.out.printf("30 <= Idade < 40: %d (%s)\n", qtdMenos40, porcentagem(qtdMenos40, qtdCandidatos));
        System.out.printf("40 <= Idade < 50: %d (%s)\n", qtdMenos50, porcentagem(qtdMenos50, qtdCandidatos));
        System.out.printf("50 <= Idade < 60: %d (%s)\n", qtdMenos60, porcentagem(qtdMenos60, qtdCandidatos));
        System.out.printf("60 <= Idade     : %d (%s)\n", qtdMaiorIgual60, porcentagem(qtdMaiorIgual60, qtdCandidatos));

        System.out.println();
    }

    public void eleitosPorGenero() {
    	int vagas = eleicao.getVagas();
    	int F = eleicao.calculaQtdF();
    	int M = vagas - F;
    	
    	System.out.println("Eleitos, por sexo:");
    	System.out.printf("Feminino:  %d (%s)\n", F, porcentagem(F, vagas));
    	System.out.printf("Masculino: %d (%s)\n", M, porcentagem(M, vagas));

        System.out.println();
    }
    
    public void votosEleicao() {
    	int total, nominal, legenda;
    	
    	total = eleicao.getVotosTotais();
    	nominal = eleicao.getVotosNominais();
    	legenda = eleicao.getVotosLegenda();
    	
    	System.out.println("Total de votos válidos:    " + total);
    	System.out.printf("Total de votos nominais:   %d (%s)\n",  nominal, porcentagem(nominal, total));
    	System.out.printf("Total de votos de Legenda: %d (%s)\n",  legenda, porcentagem(legenda, total));
        
        System.out.println();
    }
}
