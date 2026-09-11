# MundoWumpusDia6

Sexta versão didática. O agente volta a se movimentar automaticamente, mas
agora possui memória das coordenadas visitadas.

## Novidade do Dia 6

- o usuário não informa movimentos;
- o agente possui sua própria matriz `boolean[][] memoria`;
- posições ainda não visitadas são preferidas;
- quando não há posição nova, o agente reutiliza uma posição conhecida;
- brisa e fedor são exibidos, mas ainda não orientam a decisão;
- a flecha possui 15% de chance de ser disparada ao acaso.

O objetivo é comparar o agente aleatório do Dia 1 com um agente que possui
memória, mas ainda não raciocina sobre os perigos.

## Como abrir no NetBeans

1. Extraia `MundoWumpusDia6.zip`.
2. Abra a pasta `MundoWumpusDia6` como projeto no NetBeans.
3. Pressione **F6**. Nenhuma entrada pelo teclado é necessária.
4. Use o botão **Parar** para interromper antes do limite, se desejar.

## Classes

- `Main.java`: executa a demonstração automática;
- `Mundo.java`: mantém o mapa real, a visualização e a flecha;
- `AgenteComMemoria.java`: escolhe movimentos usando a memória de exploração.
