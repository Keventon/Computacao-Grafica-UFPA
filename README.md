# Computação Gráfica — UFPA

Este repositório reúne códigos, exemplos e atividades utilizados na disciplina de **Computação Gráfica da Universidade Federal do Pará (UFPA)**.

Os projetos foram desenvolvidos com finalidade didática, permitindo que os alunos acompanhem gradualmente a construção dos programas e compreendam conceitos fundamentais de Computação Gráfica.

## Tecnologias utilizadas

- **Linguagem:** Java
- **IDE:** Apache NetBeans
- **Tipo de aplicação:** Aplicações de console
- **Instituição:** Universidade Federal do Pará — UFPA

## Mundo de Wumpus

O **Mundo de Wumpus** é um ambiente clássico utilizado no ensino de Inteligência Artificial. Neste projeto, ele também será utilizado como base para apresentar conceitos iniciais de Computação Gráfica e representação de ambientes virtuais.

O mundo é representado por uma matriz bidimensional formada por linhas e colunas. Dentro dessa matriz podem existir diferentes elementos:

- Agente;
- Ouro;
- Wumpus;
- Poços;
- Casas vazias;
- Brisa próxima aos poços;
- Fedor próximo ao Wumpus.

O agente se movimenta pelo mapa procurando o ouro, enquanto pode encontrar perigos durante o caminho.

## Relação com Computação Gráfica

Embora as primeiras versões sejam executadas no terminal, o projeto apresenta conceitos importantes que serão utilizados posteriormente na construção de uma interface gráfica:

- Sistema de coordenadas;
- Linhas e colunas;
- Matrizes bidimensionais;
- Posicionamento de objetos;
- Movimentação no espaço;
- Limites do cenário;
- Representação de elementos;
- Atualização do estado do mundo;
- Entrada de dados pelo usuário;
- Tomada de decisão por um agente.

Em Java, cada posição do mapa pode ser acessada utilizando:

```java
mapa[linha][coluna]
```

As linhas são horizontais e as colunas são verticais. Entretanto, ao mover o agente para cima ou para baixo, alteramos a linha; ao mover para a esquerda ou para a direita, alteramos a coluna.

## Evolução do projeto

O projeto será desenvolvido de maneira gradual, adicionando novos conceitos a cada aula.

### Dia 1 — Movimento aleatório

O agente realiza movimentos aleatórios pelo mapa, sem memória e sem tomar decisões inteligentes. Ele pode encontrar o ouro, cair em um poço ou entrar na posição do Wumpus.

### Dia 2 — Controle pelo usuário

O usuário controla o agente utilizando as teclas `W`, `A`, `S` e `D`.

### Dia 3 — Sistema de pontuação

O jogo passa a registrar pontos de acordo com as ações e acontecimentos durante a partida.

### Dia 4 — Mapa parcialmente oculto

O jogador visualiza apenas as posições já visitadas, introduzindo os conceitos de visibilidade e descoberta do cenário.

### Dia 5 — Flecha

O agente recebe uma flecha que pode ser utilizada para tentar eliminar o Wumpus.

### Dia 6 — Agente com memória

O agente automático passa a registrar as posições que já visitou.

### Dia 7 — Decisões baseadas em percepções

O agente começa a utilizar informações como brisa e fedor para avaliar os possíveis perigos do mapa.

## Como abrir um projeto no NetBeans

1. Faça o download ou clone este repositório.
2. Abra o Apache NetBeans.
3. Acesse **File → Open Project** ou **Arquivo → Abrir Projeto**.
4. Selecione a pasta correspondente ao dia ou à atividade desejada.
5. Clique em **Open Project**.
6. Execute o projeto utilizando o botão **Run Project**.

## Objetivo educacional

O objetivo não é apresentar inicialmente um jogo completo, mas construir cada parte aos poucos. Dessa maneira, os alunos podem compreender como coordenadas, objetos, movimentação, regras e estados são utilizados na criação de ambientes computacionais.

Posteriormente, os conceitos desenvolvidos no terminal poderão ser aplicados em uma versão com representação gráfica.
