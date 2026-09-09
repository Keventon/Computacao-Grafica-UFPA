# MundoWumpusDia1

Primeira versão didática do Mundo de Wumpus para a disciplina de Computação
Gráfica. Não há entrada de dados: o agente se movimenta automaticamente e
todas as suas decisões são aleatórias.

## O que esta versão demonstra

- matriz bidimensional de caracteres;
- coordenadas de linha e coluna;
- posicionamento de elementos;
- desenho do mapa no terminal;
- atualização da posição do agente;
- escolha aleatória de direção, sem evitar perigos;
- verificação dos limites do mapa;
- agente sem memória, que pode repetir posições.
- percepção de brisa, fedor e brilho;
- encontro e combate aleatório contra o Wumpus;
- coleta do ouro e retorno à posição inicial.

O mapa inteiro fica visível. O agente percebe brisa perto de poços e fedor
perto do Wumpus, mas não usa essas informações. Ele sorteia uma direção e pode
entrar em qualquer casa. Se tentar sair da matriz, permanece no lugar.

Ao entrar na casa do Wumpus, outro sorteio decide quem vence o combate. O
agente também pode pegar o ouro e morrer depois. A vitória acontece somente
se ele conseguir voltar à coordenada `(0, 0)` com o ouro.

## Como abrir no NetBeans

1. Extraia `MundoWumpusDia1.zip`.
2. Abra o NetBeans.
3. Escolha **File > Open Project** ou **Arquivo > Abrir Projeto**.
4. Selecione a pasta `MundoWumpusDia1` extraída.
5. Pressione **F6** ou clique em **Run Project/Executar Projeto**.

Não é necessário digitar nada. Para interromper a execução antes do final,
use o botão vermelho **Stop/Parar** na janela de saída do NetBeans.

## Classes

- `Main.java`: contém o laço principal e mostra cada passo;
- `Mundo.java`: cria a matriz e desenha o mapa;
- `AgenteAleatorio.java`: guarda a posição e sorteia os movimentos.

## Ajustes simples para a aula

Em `Main.java`, altere `TEMPO_ENTRE_MOVIMENTOS` para controlar a velocidade.
Em `Mundo.java`, altere as coordenadas dentro de `criarMapa()` para mudar a
posição do ouro, dos poços e do Wumpus.
