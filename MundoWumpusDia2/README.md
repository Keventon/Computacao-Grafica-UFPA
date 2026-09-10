# MundoWumpusDia2

Segunda versão didática do Mundo de Wumpus para a disciplina de Computação
Gráfica. Ela mantém os conceitos do Dia 1 e acrescenta entrada de dados pelo
teclado para o usuário controlar o agente.

## Novidade do Dia 2

- `W`: mover para cima;
- `S`: mover para baixo;
- `A`: mover para a esquerda;
- `D`: mover para a direita;
- `Q`: encerrar o jogo.

O mapa permanece completamente visível. O programa informa brisa, fedor e
brilho, mas quem decide o movimento é o usuário.

## Regras mantidas

- entrar em um poço provoca a morte do agente;
- ao entrar na casa do Wumpus, um sorteio decide o combate;
- se o agente vencer, o Wumpus é removido;
- pegar o ouro não encerra imediatamente a partida;
- a vitória ocorre ao retornar à coordenada `(0, 0)` com o ouro;
- comandos contra os limites do mapa não alteram a posição;
- ainda não existe sistema de pontuação.

## Como abrir no NetBeans

1. Extraia `MundoWumpusDia2.zip`.
2. Abra o NetBeans.
3. Escolha **File > Open Project** ou **Arquivo > Abrir Projeto**.
4. Selecione a pasta `MundoWumpusDia2` extraída.
5. Pressione **F6** ou clique em **Run Project/Executar Projeto**.
6. Digite os comandos na janela **Output/Saída** e pressione Enter.

Se a janela de saída não aceitar entrada, abra as propriedades do projeto,
acesse **Run/Executar** e configure o uso de um terminal externo.

## Classes

- `Main.java`: lê o teclado e controla as regras do jogo;
- `Mundo.java`: cria a matriz, mostra o mapa e calcula as percepções;
- `AgenteControlado.java`: guarda o estado e movimenta o agente.
