package mundowumpusdia6;

/** Classe principal da demonstração automática com memória. */
public class Main {

    private static final int TEMPO_ENTRE_MOVIMENTOS = 500;
    private static final int LIMITE_DE_MOVIMENTOS = 150;

    public static void main(String[] args) throws InterruptedException {
        Mundo mundo = new Mundo();
        AgenteComMemoria agente = new AgenteComMemoria();

        mostrarExplicacao();
        mostrarEstado(mundo, agente, false);

        while (agente.estaVivo() && !venceu(agente)
                && agente.getQuantidadeDeMovimentos() < LIMITE_DE_MOVIMENTOS) {
            Thread.sleep(TEMPO_ENTRE_MOVIMENTOS);
            System.out.println();
            System.out.println("------------------------------------------");

            // Neste dia, até o uso da flecha ainda é uma decisão aleatória.
            if (agente.decidiuAtirarAoAcaso()) {
                realizarDisparoAleatorio(mundo, agente);
            }

            String direcao = agente.mover(mundo);
            mundo.marcarVisitada(agente.getLinha(), agente.getColuna());

            System.out.println("DECISÃO COM MEMÓRIA: " + direcao);
            System.out.println("O agente preferiu uma posição nova, se havia opção.");
            resolverCasaAtual(mundo, agente);
            mostrarEstado(mundo, agente, false);
        }

        if (venceu(agente)) {
            agente.alterarPontuacao(AgenteComMemoria.BONUS_VITORIA);
            System.out.println("PONTUAÇÃO: +" + AgenteComMemoria.BONUS_VITORIA
                    + " por retornar com o ouro.");
        }

        System.out.println();
        System.out.println("MAPA COMPLETO REVELADO NO FINAL:");
        mostrarEstado(mundo, agente, true);

        if (venceu(agente)) {
            System.out.println("VITÓRIA: o agente voltou com o ouro!");
        } else if (!agente.estaVivo()) {
            System.out.println("FIM DE JOGO: o agente morreu.");
        } else {
            System.out.println("O limite de movimentos foi atingido.");
        }

        System.out.println("PONTUAÇÃO FINAL: " + agente.getPontuacao());
    }

    private static void realizarDisparoAleatorio(Mundo mundo,
            AgenteComMemoria agente) {
        char direcao = agente.sortearDirecaoDaFlecha();
        agente.usarFlecha();
        agente.alterarPontuacao(AgenteComMemoria.CUSTO_FLECHA);
        System.out.println("DECISÃO ALEATÓRIA: disparar para " + direcao + ".");

        if (mundo.atirarFlecha(agente.getLinha(), agente.getColuna(), direcao)) {
            System.out.println("GRITO: a flecha matou o Wumpus!");
            agente.alterarPontuacao(AgenteComMemoria.BONUS_WUMPUS);
        } else {
            System.out.println("A flecha não atingiu o Wumpus.");
        }
    }

    private static void resolverCasaAtual(Mundo mundo,
            AgenteComMemoria agente) {
        int linha = agente.getLinha();
        int coluna = agente.getColuna();
        char elemento = mundo.getElemento(linha, coluna);

        switch (elemento) {
            case Mundo.POCO:
                System.out.println("ACONTECEU: o agente caiu no poço!");
                aplicarMorte(agente);
                break;
            case Mundo.WUMPUS:
                System.out.println("ACONTECEU: o agente encontrou o Wumpus!");
                if (agente.tentarMatarWumpus()) {
                    System.out.println("SORTEIO: o agente matou o Wumpus!");
                    mundo.removerElemento(linha, coluna);
                    agente.alterarPontuacao(AgenteComMemoria.BONUS_WUMPUS);
                } else {
                    System.out.println("SORTEIO: o Wumpus matou o agente!");
                    aplicarMorte(agente);
                }   break;
            case Mundo.OURO:
                System.out.println("PERCEPÇÃO: BRILHO! O agente pegou o ouro.");
                agente.pegarOuro();
                mundo.removerElemento(linha, coluna);
                agente.alterarPontuacao(AgenteComMemoria.BONUS_OURO);
                break;
            default:
                break;
        }
    }

    private static void aplicarMorte(AgenteComMemoria agente) {
        agente.morrer();
        agente.alterarPontuacao(AgenteComMemoria.PENALIDADE_MORTE);
    }

    private static void mostrarEstado(Mundo mundo, AgenteComMemoria agente,
            boolean revelarTudo) {
        mundo.mostrar(agente, revelarTudo);

        if (agente.estaVivo()) {
            mundo.mostrarPercepcoes(agente);
        }

        System.out.println("Movimentos: " + agente.getQuantidadeDeMovimentos());
        System.out.println("Pontuação: " + agente.getPontuacao());
        System.out.println("Ouro: " + (agente.possuiOuro() ? "SIM" : "NÃO")
                + " | Flecha: " + (agente.possuiFlecha() ? "SIM" : "NÃO"));
    }

    private static boolean venceu(AgenteComMemoria agente) {
        return agente.possuiOuro()
                && agente.getLinha() == 0
                && agente.getColuna() == 0;
    }

    private static void mostrarExplicacao() {
        System.out.println("==========================================");
        System.out.println("        MUNDO DE WUMPUS - SEXTO DIA");
        System.out.println("==========================================");
        System.out.println("O agente voltou a andar automaticamente.");
        System.out.println("Agora ele possui memória das casas visitadas.");
        System.out.println("Ele prefere casas novas, mas ignora brisa e fedor.");
        System.out.println("A flecha ainda é disparada ao acaso.");
    }
}
