package mundowumpusdia1;

/**
 * A classe Mundo cria e mostra o mapa.
 *
 * O mapa é uma matriz: cada posição possui uma linha e uma coluna.
 * Nesta primeira versão, todos os elementos ficam visíveis.
 */
public class Mundo {

    public static final int TAMANHO = 5;

    // Símbolos usados dentro da matriz.
    public static final char VAZIO = '.';
    public static final char POCO = 'P';
    public static final char WUMPUS = 'W';
    public static final char OURO = 'O';

    private final char[][] mapa;

    public Mundo() {
        mapa = new char[TAMANHO][TAMANHO];
        criarMapa();
    }

    /** Coloca um símbolo em cada posição da matriz. */
    private void criarMapa() {
        // Primeiro, todas as posições recebem um ponto.
        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                mapa[linha][coluna] = VAZIO;
            }
        }

        // Depois, colocamos os elementos em coordenadas específicas.
        mapa[1][2] = POCO;
        mapa[3][1] = POCO;
        mapa[2][3] = WUMPUS;
        mapa[4][4] = OURO;
    }

    /** Verifica se a coordenada está dentro da matriz. */
    public boolean estaDentroDoMapa(int linha, int coluna) {
        return linha >= 0 && linha < TAMANHO
                && coluna >= 0 && coluna < TAMANHO;
    }

    /** Devolve o elemento existente em uma coordenada. */
    public char getElemento(int linha, int coluna) {
        return mapa[linha][coluna];
    }

    /** Remove um elemento depois que o ouro é pego ou o Wumpus é morto. */
    public void removerElemento(int linha, int coluna) {
        mapa[linha][coluna] = VAZIO;
    }

    /**
     * Procura um elemento nas quatro casas vizinhas.
     * As diagonais não são consideradas nesta versão.
     */
    private boolean existeVizinho(int linha, int coluna, char procurado) {
        int[][] direcoes = {
            {-1, 0}, // cima
            {1, 0},  // baixo
            {0, -1}, // esquerda
            {0, 1}   // direita
        };

        for (int[] direcao : direcoes) {
            int linhaVizinha = linha + direcao[0];
            int colunaVizinha = coluna + direcao[1];

            if (estaDentroDoMapa(linhaVizinha, colunaVizinha)
                    && mapa[linhaVizinha][colunaVizinha] == procurado) {
                return true;
            }
        }

        return false;
    }

    /**
     * Mostra o que o agente percebe, mas essa informação não altera
     * a decisão aleatória tomada por ele.
     */
    public void mostrarPercepcoes(AgenteAleatorio agente) {
        int linha = agente.getLinha();
        int coluna = agente.getColuna();
        boolean percebeuAlgo = false;

        System.out.print("Percepções: ");

        if (existeVizinho(linha, coluna, POCO)) {
            System.out.print("BRISA  ");
            percebeuAlgo = true;
        }

        if (existeVizinho(linha, coluna, WUMPUS)) {
            System.out.print("FEDOR  ");
            percebeuAlgo = true;
        }

        if (mapa[linha][coluna] == OURO) {
            System.out.print("BRILHO  ");
            percebeuAlgo = true;
        }

        if (!percebeuAlgo) {
            System.out.print("NENHUMA");
        }

        System.out.println();
    }

    /**
     * Mostra os números das colunas, os números das linhas e todo o mapa.
     * A letra A é desenhada na posição atual do agente.
     */
    public void mostrar(AgenteAleatorio agente) {
        System.out.print("      ");
        for (int coluna = 0; coluna < TAMANHO; coluna++) {
            System.out.print(coluna + "   ");
        }
        System.out.println("  COLUNAS");

        for (int linha = 0; linha < TAMANHO; linha++) {
            System.out.print("  " + linha + "  ");

            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                if (linha == agente.getLinha()
                        && coluna == agente.getColuna()) {
                    System.out.print(agente.estaVivo() ? "[A] " : "[X] ");
                } else {
                    System.out.print("[" + mapa[linha][coluna] + "] ");
                }
            }

            System.out.println();
        }
        System.out.println("LINHAS");
    }
}
