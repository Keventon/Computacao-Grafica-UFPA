package mundowumpusdia1;

import java.util.Random;

/**
 * Representa o agente que anda sozinho.
 *
 * Ele não guarda as posições visitadas e não usa as percepções para decidir.
 * Em cada rodada, sorteia uma direção sem saber o que encontrará.
 */
public class AgenteAleatorio {

    private int linha;
    private int coluna;
    private int quantidadeDeMovimentos;
    private boolean vivo = true;
    private boolean possuiOuro = false;

    // O objeto Random é responsável pelos sorteios.
    private final Random sorteador = new Random();

    public AgenteAleatorio() {
        // O agente começa na coordenada (linha 0, coluna 0).
        linha = 0;
        coluna = 0;
    }

    /**
     * Sorteia apenas uma das quatro direções.
     *
     * O agente não verifica poço, Wumpus ou ouro antes de andar. Ele verifica
     * somente o limite, pois não existe uma posição fora da matriz.
     */
    public String mover(Mundo mundo) {
        int direcao = sorteador.nextInt(4);
        int novaLinha = linha;
        int novaColuna = coluna;
        String nomeDaDirecao;

        switch (direcao) {
            case 0:
                novaLinha--;
                nomeDaDirecao = "CIMA";
                break;
            case 1:
                novaLinha++;
                nomeDaDirecao = "BAIXO";
                break;
            case 2:
                novaColuna--;
                nomeDaDirecao = "ESQUERDA";
                break;
            default:
                novaColuna++;
                nomeDaDirecao = "DIREITA";
                break;
        }

        quantidadeDeMovimentos++;

        if (mundo.estaDentroDoMapa(novaLinha, novaColuna)) {
            linha = novaLinha;
            coluna = novaColuna;
            return nomeDaDirecao;
        }

        // Se sair da matriz, o movimento é contado, mas o agente não muda.
        return nomeDaDirecao + " - bateu no limite do mapa";
    }

    /** O resultado do combate contra o Wumpus também é aleatório. */
    public boolean tentarMatarWumpus() {
        return sorteador.nextBoolean();
    }

    public void morrer() {
        vivo = false;
    }

    public void pegarOuro() {
        possuiOuro = true;
    }

    public boolean estaVivo() {
        return vivo;
    }

    public boolean possuiOuro() {
        return possuiOuro;
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
}
