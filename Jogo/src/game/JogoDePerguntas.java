package game;

import java.util.Scanner;

public class JogoDePerguntas {
	
	private static String[][] alternativas = {
            {"Nenhuma", "Uma", "Duas", "Três"},
            {"400 Gols", "450 Gols", "500 Gols", "550 Gols"},
            {"Barcelona", "Real Madrid", "Bayern de Munique", "Liverpool"}
    };
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pontuacao = 0;

        String[] perguntas = {
        		"Quantas Bola de Ouro tem o Neymar?",
                "Quantos Gols Cristiano Ronaldo fez pelo Real Madrid?",
                "Quem é o Clube com mais Champions League?"
        };
        
        

        String[] respostas = {
        		"Nenhuma",
                "450 Gols",
                "Real Madrid"
        };

        for (int i = 0; i < perguntas.length; i++) {
            System.out.println("Pergunta " + (i + 1) + ": " + perguntas[i]);

            for (int j = 0; j < alternativas[i].length; j++) {
                System.out.println((char) ('A' + j) + ") " + alternativas[i][j]);
            }
            System.out.print("Sua resposta: ");

            String resposta = scanner.nextLine().toLowerCase(); 

            if (resposta.equals(respostas[i].toLowerCase())) {
                System.out.println("Resposta correta! Você ganhou 10 pontos.");
                pontuacao += 10;
            } else {
                System.out.println("Resposta incorreta.");
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
