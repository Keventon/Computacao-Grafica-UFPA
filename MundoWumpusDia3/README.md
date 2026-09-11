# MundoWumpusDia3

Terceira versão didática do Mundo de Wumpus. Ela mantém o controle por teclado
do Dia 2 e acrescenta um sistema simples de pontuação.

## Novidade do Dia 3

| Ação | Pontos |
|---|---:|
| Realizar um movimento | -1 |
| Encontrar o ouro | +100 |
| Derrotar o Wumpus | +50 |
| Morrer | -100 |
| Voltar ao início com o ouro | +200 |

Comandos inválidos e tentativas de sair do mapa não alteram a pontuação.
O total é exibido durante a partida e novamente no final.

## Comandos

- `W`: mover para cima;
- `S`: mover para baixo;
- `A`: mover para a esquerda;
- `D`: mover para a direita;
- `Q`: encerrar o jogo.

## Regras mantidas

- o mapa inteiro permanece visível;
- brisa indica um poço vizinho;
- fedor indica o Wumpus em uma casa vizinha;
- ao entrar na casa do Wumpus, um sorteio decide o combate;
- o ouro deve ser levado até a coordenada `(0, 0)`;
- o usuário pode ignorar as percepções e entrar em casas perigosas.

## Como abrir no NetBeans

1. Extraia `MundoWumpusDia3.zip`.
2. Abra o NetBeans.
3. Escolha **File > Open Project** ou **Arquivo > Abrir Projeto**.
4. Selecione a pasta `MundoWumpusDia3` extraída.
5. Pressione **F6** ou clique em **Run Project/Executar Projeto**.
6. Digite os comandos na janela **Output/Saída** e pressione Enter.

Se a janela de saída não aceitar entrada, abra as propriedades do projeto,
acesse **Run/Executar** e configure o uso de um terminal externo.

## Classes

- `Main.java`: lê comandos e aplica eventos, bônus e penalidades;
- `Mundo.java`: cria a matriz, mostra o mapa e calcula as percepções;
- `AgenteControlado.java`: guarda posição, vida, ouro e pontuação.

As constantes de pontuação ficam no início de `AgenteControlado.java`. Assim,
o professor pode mudar os valores rapidamente durante a aula.
