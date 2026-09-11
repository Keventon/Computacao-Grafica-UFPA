package mundowumpusdia6;

import java.util.Random;

/**
 * Agente automático que lembra quais coordenadas já visitou.
 * Ele prefere casas novas, mas ainda ignora brisa e fedor.
 */
public class AgenteComMemoria {

    public static final int CUSTO_MOVIMENTO = -1;
    public static final int BONUS_OURO = 100;
    public static final int BONUS_WUMPUS = 50;
    public static final int PENALIDADE_MORTE = -100;
    public static final int BONUS_VITORIA = 200;
    public static final int CUSTO_FLECHA = -10;

    // Cada número representa uma alteração em linha e coluna.
    private static final int[][] DIRECOES = {
        {-1, 0}, // cima
        {1, 0},  // baixo
        {0, -1}, // esquerda
        {0, 1}   // direita
    };

    private static final String[] NOMES = {
        "CIMA", "BAIXO", "ESQUERDA", "DIREITA"
    };

    private int linha;
    private int coluna;
    private int quantidadeDeMovimentos;
    private int pontuacao;
    private boolean vivo = true;
    private boolean possuiOuro;
    private boolean possuiFlecha = true;

    // Esta matriz é a memória interna do agente.
    private final boolean[][] memoria;
    private final Random sorteador = new Random();

    public AgenteComMemoria() {
        memoria = new boolean[Mundo.TAMANHO][Mundo.TAMANHO];
        memoria[0][0] = true;
    }

    /**
     * Primeiro procura vizinhos ainda não visitados. Se todos já foram
     * visitados, escolhe qualquer vizinho válido para continuar andando.
     */
    public String mover(Mundo mundo) {
        int[] candidatas = new int[4];
        int quantidade = buscarDirecoes(mundo, candidatas, true);

        if (quantidade == 0) {
            quantidade = buscarDirecoes(mundo, candidatas, false);
        }

        int indiceSorteado = sorteador.nextInt(quantidade);
        int direcao = candidatas[indiceSorteado];

        linha = linha + DIRECOES[direcao][0];
        coluna = coluna + DIRECOES[direcao][1];
        memoria[linha][coluna] = true;
        quantidadeDeMovimentos++;
        alterarPontuacao(CUSTO_MOVIMENTO);

        return NOMES[direcao];
    }

    /** Preenche o vetor candidatas e devolve quantas opções encontrou. */
    private int buscarDirecoes(Mundo mundo, int[] candidatas,
            boolean somenteNaoVisitadas) {
        int quantidade = 0;

        for (int direcao = 0; direcao < DIRECOES.length; direcao++) {
            int novaLinha = linha + DIRECOES[direcao][0];
            int novaColuna = coluna + DIRECOES[direcao][1];

            if (mundo.estaDentroDoMapa(novaLinha, novaColuna)
                    && (!somenteNaoVisitadas
                    || !memoria[novaLinha][novaColuna])) {
                candidatas[quantidade] = direcao;
                quantidade++;
            }
        }

        return quantidade;
    }

    /**
     * A flecha é mantida, mas seu uso ainda não é inteligente:
     * existe 15% de chance de disparo aleatório a cada rodada.
     */
    public boolean decidiuAtirarAoAcaso() {
        return possuiFlecha && sorteador.nextInt(100) < 15;
    }

    public char sortearDirecaoDaFlecha() {
        return new char[]{'W', 'S', 'A', 'D'}[sorteador.nextInt(4)];
    }

    public boolean tentarMatarWumpus() {
        return sorteador.nextBoolean();
    }

    public void alterarPontuacao(int pontos) {
        pontuacao = pontuacao + pontos;
    }

    public void usarFlecha() {
        possuiFlecha = false;
    }

    public void morrer() {
        vivo = false;
    }

    public void pegarOuro() {
        possuiOuro = true;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public int getQuantidadeDeMovimentos() {
        return quantidadeDeMovimentos;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public boolean estaVivo() {
        return vivo;
    }

    public boolean possuiOuro() {
        return possuiOuro;
    }

    public boolean possuiFlecha() {
        return possuiFlecha;
    }
}
