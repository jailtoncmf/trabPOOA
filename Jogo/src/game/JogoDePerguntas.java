package game;

import java.util.Scanner;

public class JogoDePerguntas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pontuacao = 0;

        String[] perguntas = {
        		"Quantas Bola de Ouro tem o Neymar?",
                "Quantos Gols Cristiano Ronaldo fez pelo Real Madrid?",
                "Quem é o Clube com mais Champions League?"
        };

        String[] respostas = {
        		"0",
                "450",
                "Real Madrid"
        };

        for (int i = 0; i < perguntas.length; i++) {
            System.out.println("Pergunta " + (i + 1) + ": " + perguntas[i]);
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase(respostas[i])) {
                System.out.println("Resposta correta! Você ganhou 10 pontos.");
                pontuacao += 10;
            } else {
                System.out.println("Resposta incorreta. A resposta correta é: " + respostas[i]);
            }
        }

        System.out.println("Pontuação final: " + pontuacao);
        if (pontuacao == 30) {
            System.out.println("Parabéns! Você acertou todas as perguntas.");
        } else {
            System.out.println("Bom jogo! Tente novamente para obter uma pontuação perfeita.");
        }
    }
}

