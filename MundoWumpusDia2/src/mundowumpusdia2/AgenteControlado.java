package mundowumpusdia2;

import java.util.Random;

/**
 * Guarda o estado do agente e movimenta-o conforme o comando do usuário.
 */
public class AgenteControlado {

    private int linha;
    private int coluna;
    private int quantidadeDeMovimentos;
    private boolean vivo = true;
    private boolean possuiOuro = false;

    // O combate continua aleatório nesta versão.
    private final Random sorteador = new Random();

    public AgenteControlado() {
        linha = 0;
        coluna = 0;
    }

    /**
     * Converte W/A/S/D em alterações de linha ou coluna.
     * Retorna uma mensagem explicando o resultado do comando.
     */
    public String mover(char comando, Mundo mundo) {
        int novaLinha = linha;
        int novaColuna = coluna;
        String direcao;

        switch (comando) {
            case 'W':
                novaLinha--;
                direcao = "CIMA";
                break;
            case 'S':
                novaLinha++;
                direcao = "BAIXO";
                break;
            case 'A':
                novaColuna--;
                direcao = "ESQUERDA";
                break;
            case 'D':
                novaColuna++;
                direcao = "DIREITA";
                break;
            default:
                return "Comando inválido. Use W, A, S, D ou Q.";
        }

        if (!mundo.estaDentroDoMapa(novaLinha, novaColuna)) {
            return direcao + " - limite do mapa! O agente não se moveu.";
        }

        linha = novaLinha;
        coluna = novaColuna;
        quantidadeDeMovimentos++;
        return direcao;
    }

    /** O usuário escolhe o caminho, mas o resultado do combate é sorteado. */
    public boolean tentarMatarWumpus() {
        return sorteador.nextBoolean();
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

    public boolean estaVivo() {
        return vivo;
    }

    public boolean possuiOuro() {
        return possuiOuro;
    }
}
