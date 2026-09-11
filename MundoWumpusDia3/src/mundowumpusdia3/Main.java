package mundowumpusdia3;

import java.util.Scanner;

/** Classe principal: recebe comandos e aplica as regras do jogo. */
public class Main {

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        AgenteControlado agente = new AgenteControlado();
        Scanner teclado = new Scanner(System.in);
        boolean usuarioSaiu = false;

        mostrarExplicacao();

        while (agente.estaVivo() && !venceu(agente) && !usuarioSaiu) {
            System.out.println();
            mostrarEstado(mundo, agente);
            System.out.print("Digite W, A, S, D ou Q: ");

            if (!teclado.hasNextLine()) {
                break;
            }

            String entrada = teclado.nextLine().trim().toUpperCase();

            if (entrada.isEmpty()) {
                System.out.println("Nenhum comando foi digitado.");
                continue;
            }

            char comando = entrada.charAt(0);

            if (comando == 'Q') {
                usuarioSaiu = true;
                continue;
            }

            String resultado = agente.mover(comando, mundo);
            System.out.println("Resultado do comando: " + resultado);

            if (movimentoRealizado(resultado)) {
                System.out.println("PONTUAÇÃO: "
                        + AgenteControlado.CUSTO_MOVIMENTO
                        + " pelo movimento.");
                resolverCasaAtual(mundo, agente);
            }
        }

        // O bônus é entregue uma única vez, depois que o laço termina.
        if (venceu(agente)) {
            agente.alterarPontuacao(AgenteControlado.BONUS_VITORIA);
            System.out.println("PONTUAÇÃO: +"
                    + AgenteControlado.BONUS_VITORIA
                    + " por retornar com o ouro.");
        }

        System.out.println();
        mostrarEstado(mundo, agente);

        if (venceu(agente)) {
            System.out.println("VITÓRIA: você voltou ao início com o ouro!");
        } else if (usuarioSaiu) {
            System.out.println("Jogo encerrado pelo usuário.");
        } else if (!agente.estaVivo()) {
            System.out.println("FIM DE JOGO: o agente morreu.");
        } else {
            System.out.println("A entrada foi encerrada.");
        }

        System.out.println("PONTUAÇÃO FINAL: " + agente.getPontuacao());
    }

    /** Verifica se a mensagem representa uma movimentação bem-sucedida. */
    private static boolean movimentoRealizado(String resultado) {
        return resultado.equals("CIMA")
                || resultado.equals("BAIXO")
                || resultado.equals("ESQUERDA")
                || resultado.equals("DIREITA");
    }

    /** Aplica o efeito e os pontos da casa escolhida pelo usuário. */
    private static void resolverCasaAtual(Mundo mundo, AgenteControlado agente) {
        int linha = agente.getLinha();
        int coluna = agente.getColuna();
        char elemento = mundo.getElemento(linha, coluna);

        switch (elemento) {
            case Mundo.POCO:
                System.out.println("ACONTECEU: você entrou no poço!");
                aplicarMorte(agente);
                break;
            case Mundo.WUMPUS:
                System.out.println("ACONTECEU: você encontrou o Wumpus!");
                if (agente.tentarMatarWumpus()) {
                    System.out.println("SORTEIO DO COMBATE: você matou o Wumpus!");
                    mundo.removerElemento(linha, coluna);
                    agente.alterarPontuacao(AgenteControlado.BONUS_WUMPUS);
                    System.out.println("PONTUAÇÃO: +"
                            + AgenteControlado.BONUS_WUMPUS
                            + " por derrotar o Wumpus.");
                } else {
                    System.out.println("SORTEIO DO COMBATE: o Wumpus matou você!");
                    aplicarMorte(agente);
                }   break;
            case Mundo.OURO:
                System.out.println("PERCEPÇÃO NA CASA: BRILHO!");
                System.out.println("ACONTECEU: você encontrou e pegou o ouro!");
                agente.pegarOuro();
                mundo.removerElemento(linha, coluna);
                agente.alterarPontuacao(AgenteControlado.BONUS_OURO);
                System.out.println("PONTUAÇÃO: +"
                        + AgenteControlado.BONUS_OURO
                        + " por encontrar o ouro.");
                break;
            default:
                break;
        }
    }

    private static void aplicarMorte(AgenteControlado agente) {
        agente.morrer();
        agente.alterarPontuacao(AgenteControlado.PENALIDADE_MORTE);
        System.out.println("PONTUAÇÃO: "
                + AgenteControlado.PENALIDADE_MORTE
                + " pela morte do agente.");
    }

    private static void mostrarEstado(Mundo mundo, AgenteControlado agente) {
        mundo.mostrar(agente);

        if (agente.estaVivo()) {
            mundo.mostrarPercepcoes(agente);
        }

        System.out.println("Posição: linha " + agente.getLinha()
                + ", coluna " + agente.getColuna());
        System.out.println("Possui o ouro: "
                + (agente.possuiOuro() ? "SIM" : "NÃO"));
        System.out.println("Pontuação atual: " + agente.getPontuacao());
    }

    private static boolean venceu(AgenteControlado agente) {
        return agente.possuiOuro()
                && agente.getLinha() == 0
                && agente.getColuna() == 0;
    }

    private static void mostrarExplicacao() {
        System.out.println("==========================================");
        System.out.println("       MUNDO DE WUMPUS - TERCEIRO DIA");
        System.out.println("==========================================");
        System.out.println("Controle o agente, encontre o ouro e volte a (0, 0).");
        System.out.println();
        System.out.println("Comandos: W=cima, S=baixo, A=esquerda,");
        System.out.println("          D=direita e Q=sair.");
        System.out.println();
        System.out.println("Regras de pontuação:");
        System.out.println("Movimento: -1 | Ouro: +100 | Wumpus: +50");
        System.out.println("Morte: -100   | Vitória: +200");
        System.out.println();
        System.out.println("Legenda:");
        System.out.println("A = Agente vivo | X = Agente morto");
        System.out.println("P = Poço        | W = Wumpus");
        System.out.println("O = Ouro        | . = Posição vazia");
    }
}
