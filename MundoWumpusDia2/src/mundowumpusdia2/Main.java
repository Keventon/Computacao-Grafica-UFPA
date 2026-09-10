package mundowumpusdia2;

import java.util.Scanner;

/** Classe principal: recebe comandos e mantém o jogo funcionando. */
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

            // hasNextLine evita erro se o terminal for fechado.
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

            // Só verifica a casa quando ocorreu um movimento válido.
            if (resultado.equals("CIMA") || resultado.equals("BAIXO")
                    || resultado.equals("ESQUERDA")
                    || resultado.equals("DIREITA")) {
                resolverCasaAtual(mundo, agente);
            }
        }

        System.out.println();
        mostrarEstado(mundo, agente);

        if (venceu(agente)) {
            System.out.println("VITÓRIA: você voltou ao início com o ouro!");
            System.out.println("Total de movimentos: "
                    + agente.getQuantidadeDeMovimentos());
        } else if (usuarioSaiu) {
            System.out.println("Jogo encerrado pelo usuário.");
        } else if (!agente.estaVivo()) {
            System.out.println("FIM DE JOGO: o agente morreu.");
        } else {
            System.out.println("A entrada foi encerrada.");
        }
    }

    /** Aplica o efeito da casa escolhida pelo usuário. */
    private static void resolverCasaAtual(Mundo mundo, AgenteControlado agente) {
        int linha = agente.getLinha();
        int coluna = agente.getColuna();
        char elemento = mundo.getElemento(linha, coluna);

        switch (elemento) {
            case Mundo.POCO:
                System.out.println("ACONTECEU: você entrou no poço!");
                agente.morrer();
                break;
            case Mundo.WUMPUS:
                System.out.println("ACONTECEU: você encontrou o Wumpus!");
                if (agente.tentarMatarWumpus()) {
                    System.out.println("SORTEIO DO COMBATE: você matou o Wumpus!");
                    mundo.removerElemento(linha, coluna);
                } else {
                    System.out.println("SORTEIO DO COMBATE: o Wumpus matou você!");
                    agente.morrer();
                }   break;
            case Mundo.OURO:
                System.out.println("PERCEPÇÃO NA CASA: BRILHO!");
                System.out.println("ACONTECEU: você encontrou e pegou o ouro!");
                agente.pegarOuro();
                mundo.removerElemento(linha, coluna);
                break;
            default:
                break;
        }
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
    }

    private static boolean venceu(AgenteControlado agente) {
        return agente.possuiOuro()
                && agente.getLinha() == 0
                && agente.getColuna() == 0;
    }

    private static void mostrarExplicacao() {
        System.out.println("==========================================");
        System.out.println("       MUNDO DE WUMPUS - SEGUNDO DIA");
        System.out.println("==========================================");
        System.out.println("Agora você controla o agente pelo teclado.");
        System.out.println("Observe o mapa e decida se seguirá os avisos.");
        System.out.println("Pegue o ouro e volte à coordenada (0, 0).");
        System.out.println();
        System.out.println("Comandos:");
        System.out.println("W = cima  | S = baixo");
        System.out.println("A = esquerda | D = direita | Q = sair");
        System.out.println();
        System.out.println("Legenda:");
        System.out.println("A = Agente vivo | X = Agente morto");
        System.out.println("P = Poço        | W = Wumpus");
        System.out.println("O = Ouro        | . = Posição vazia");
    }
}
