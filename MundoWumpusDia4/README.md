# MundoWumpusDia4

Quarta versão didática do Mundo de Wumpus. Mantém controle e pontuação do
Dia 3 e acrescenta mapa oculto, exploração e memória visual.

## Novidade do Dia 4

- `?` representa uma posição ainda desconhecida;
- `+` representa uma posição já visitada;
- poços, Wumpus e ouro ficam ocultos durante a partida;
- uma matriz `boolean[][] visitado` guarda o caminho explorado;
- o mapa verdadeiro é revelado quando a partida termina.

As percepções agora são essenciais: brisa indica poço vizinho, fedor indica
Wumpus vizinho e brilho aparece na casa do ouro.

## Comandos

- `W`: cima; `S`: baixo; `A`: esquerda; `D`: direita; `Q`: sair.

## Pontuação

- movimento: -1; ouro: +100; Wumpus: +50;
- morte: -100; retorno ao início com o ouro: +200.

## Como abrir no NetBeans

1. Extraia `MundoWumpusDia4.zip`.
2. No NetBeans, escolha **Arquivo > Abrir Projeto**.
3. Selecione a pasta `MundoWumpusDia4`.
4. Pressione **F6** e digite os comandos na janela de saída.

## Classes

- `Main.java`: controla comandos, eventos e pontuação;
- `Mundo.java`: mantém o mapa real e a matriz de posições visitadas;
- `AgenteControlado.java`: guarda posição, vida, ouro e pontos.
