package mundowumpusdia5;

import java.util.Random;

/**
 * Guarda posição, vida, ouro e pontuação do agente.
 */
public class AgenteControlado {

    // Regras de pontuação reunidas em constantes para facilitar alterações.
    public static final int CUSTO_MOVIMENTO = -1;
    public static final int BONUS_OURO = 100;
    public static final int BONUS_WUMPUS = 50;
    public static final int PENALIDADE_MORTE = -100;
    public static final int BONUS_VITORIA = 200;
    public static final int CUSTO_FLECHA = -10;

    private int linha;
    private int coluna;
    private int quantidadeDeMovimentos;
    private int pontuacao;
    private boolean vivo = true;
    private boolean possuiOuro = false;
    private boolean possuiFlecha = true;

    private final Random sorteador = new Random();

    public AgenteControlado() {
        linha = 0;
        coluna = 0;
        pontuacao = 0;
    }

    /**
     * Converte W/A/S/D em alterações de linha ou coluna.
     * Cada movimento realizado custa um ponto.
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
                return "Comando inválido. Use W, A, S, D, F ou Q.";
        }

        if (!mundo.estaDentroDoMapa(novaLinha, novaColuna)) {
            return direcao + " - limite do mapa! O agente não se moveu.";
        }

        linha = novaLinha;
        coluna = novaColuna;
        quantidadeDeMovimentos++;
        alterarPontuacao(CUSTO_MOVIMENTO);
        return direcao;
    }

    public boolean tentarMatarWumpus() {
        return sorteador.nextBoolean();
    }

    /** Soma um bônus ou uma penalidade ao total atual. */
    public void alterarPontuacao(int pontos) {
        pontuacao = pontuacao + pontos;
    }

    public void morrer() {
        vivo = false;
    }

    public void pegarOuro() {
        possuiOuro = true;
    }

    /** A única flecha é consumida no momento do disparo. */
    public void usarFlecha() {
        possuiFlecha = false;
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
