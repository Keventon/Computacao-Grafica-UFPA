# MundoWumpusDia5

Quinta versão didática do Mundo de Wumpus. Mantém mapa oculto, exploração e
pontuação do Dia 4 e acrescenta uma flecha para enfrentar o Wumpus à distância.

## Novidade do Dia 5

- o agente começa com uma única flecha;
- pressione `F` e informe W/A/S/D para escolher a direção;
- a flecha segue em linha reta até sair do mapa;
- um grito informa quando o Wumpus foi atingido;
- o disparo custa 10 pontos e derrotar o Wumpus concede 50 pontos;
- uma direção inválida cancela o disparo sem consumir a flecha.

Entrar diretamente na casa do Wumpus ainda inicia o combate aleatório das
versões anteriores.

## Comandos

- `W`, `A`, `S`, `D`: movimentar;
- `F`: disparar a flecha;
- `Q`: sair.

## Como abrir no NetBeans

1. Extraia `MundoWumpusDia5.zip`.
2. No NetBeans, escolha **Arquivo > Abrir Projeto**.
3. Selecione a pasta `MundoWumpusDia5` e pressione **F6**.
4. Digite os comandos na janela de saída.

## Classes

- `Main.java`: recebe comandos e controla disparos, eventos e pontos;
- `Mundo.java`: mantém os mapas e percorre a trajetória da flecha;
- `AgenteControlado.java`: guarda posição, inventário e pontuação.
