package mundowumpusdia2;

/**
 * A classe Mundo cria e mostra o mapa.
 * Cada posição da matriz possui uma linha e uma coluna.
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

    /** Preenche a matriz e posiciona os elementos da fase. */
    private void criarMapa() {
        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                mapa[linha][coluna] = VAZIO;
            }
        }

        mapa[1][2] = POCO;
        mapa[3][1] = POCO;
        mapa[2][3] = WUMPUS;
        mapa[4][4] = OURO;
    }

    /** Verifica se a coordenada existe dentro da matriz. */
    public boolean estaDentroDoMapa(int linha, int coluna) {
        return linha >= 0 && linha < TAMANHO
                && coluna >= 0 && coluna < TAMANHO;
    }

    public char getElemento(int linha, int coluna) {
        return mapa[linha][coluna];
    }

    /** Retira do mapa o ouro coletado ou o Wumpus derrotado. */
    public void removerElemento(int linha, int coluna) {
        mapa[linha][coluna] = VAZIO;
    }

    /** Procura um elemento nas quatro posições vizinhas. */
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

    /** Mostra os sinais existentes ao redor do jogador. */
    public void mostrarPercepcoes(AgenteControlado agente) {
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

    /** Desenha os índices, todos os elementos e a posição do agente. */
    public void mostrar(AgenteControlado agente) {
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
