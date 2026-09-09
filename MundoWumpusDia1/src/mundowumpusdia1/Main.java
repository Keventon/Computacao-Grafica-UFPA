package mundowumpusdia1;

/**
 * Classe principal: cria os objetos e mantém o jogo funcionando.
 */
public class Main {

    // Tempo em milissegundos entre os movimentos.
    // Aumente o valor para deixar a animação mais lenta.
    private static final int TEMPO_ENTRE_MOVIMENTOS = 500;

    // Evita que uma execução dure para sempre por causa dos sorteios.
    private static final int LIMITE_DE_MOVIMENTOS = 300;

    public static void main(String[] args) throws InterruptedException {
        Mundo mundo = new Mundo();
        AgenteAleatorio agente = new AgenteAleatorio();

        mostrarExplicacao();
        mostrarEstado(mundo, agente);

        // O laço termina em caso de morte, vitória ou limite de movimentos.
        while (agente.estaVivo()
                && !venceu(agente)
                && agente.getQuantidadeDeMovimentos() < LIMITE_DE_MOVIMENTOS) {

            Thread.sleep(TEMPO_ENTRE_MOVIMENTOS);

            String decisao = agente.mover(mundo);

            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("Decisão sorteada: " + decisao);
            System.out.println("Movimento: " + agente.getQuantidadeDeMovimentos());
            System.out.println("Posição: linha " + agente.getLinha()
                    + ", coluna " + agente.getColuna());

            resolverCasaAtual(mundo, agente);
            mostrarEstado(mundo, agente);
        }

        if (venceu(agente)) {
            System.out.println();
            System.out.println("VITÓRIA: o agente voltou ao início com o ouro!");
            System.out.println("Total de movimentos: "
                    + agente.getQuantidadeDeMovimentos());
        } else if (agente.estaVivo()) {
            System.out.println();
            System.out.println("O limite de movimentos foi atingido.");
            System.out.println("Execute novamente para iniciar novos sorteios.");
        }
    }

    /** Aplica o efeito do elemento existente na posição atual. */
    private static void resolverCasaAtual(Mundo mundo, AgenteAleatorio agente) {
        int linha = agente.getLinha();
        int coluna = agente.getColuna();
        char elemento = mundo.getElemento(linha, coluna);

        if (elemento == Mundo.POCO) {
            System.out.println("ACONTECEU: o agente caiu no poço e morreu!");
            agente.morrer();
        } else if (elemento == Mundo.WUMPUS) {
            System.out.println("ACONTECEU: o agente encontrou o Wumpus!");

            if (agente.tentarMatarWumpus()) {
                System.out.println("SORTEIO DO COMBATE: o agente matou o Wumpus!");
                mundo.removerElemento(linha, coluna);
            } else {
                System.out.println("SORTEIO DO COMBATE: o Wumpus matou o agente!");
                agente.morrer();
            }
        } else if (elemento == Mundo.OURO) {
            System.out.println("PERCEPÇÃO NA CASA: BRILHO!");
            System.out.println("ACONTECEU: o agente encontrou e pegou o ouro!");
            agente.pegarOuro();
            mundo.removerElemento(linha, coluna);
        }
    }

    /** Mostra o mapa e as percepções disponíveis na posição do agente. */
    private static void mostrarEstado(Mundo mundo, AgenteAleatorio agente) {
        mundo.mostrar(agente);

        if (agente.estaVivo()) {
            mundo.mostrarPercepcoes(agente);
        }

        System.out.println("Possui o ouro: "
                + (agente.possuiOuro() ? "SIM" : "NÃO"));
    }

    /** O objetivo é pegar o ouro e voltar à coordenada inicial (0, 0). */
    private static boolean venceu(AgenteAleatorio agente) {
        return agente.possuiOuro()
                && agente.getLinha() == 0
                && agente.getColuna() == 0;
    }

    private static void mostrarExplicacao() {
        System.out.println("==========================================");
        System.out.println("       MUNDO DE WUMPUS - PRIMEIRO DIA");
        System.out.println("==========================================");
        System.out.println("O agente anda sozinho e escolhe tudo ao acaso.");
        System.out.println("Ele percebe brisa e fedor, mas ignora os avisos.");
        System.out.println("Pode repetir posições, cair no poço ou achar o ouro.");
        System.out.println("Todo o mapa está visível para a turma.");
        System.out.println();
        System.out.println();
    }
}
