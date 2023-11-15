package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDePerguntasGUI extends JFrame {
    private int pontuacao = 0;
    private int perguntaAtual = 0;
    private String[] perguntas = {
            "Quantas Bola de Ouro tem o Neymar?",
            "Quantos Gols Cristiano Ronaldo fez pelo Real Madrid?",
            "Quem é o Clube com mais Champions League?"
    };
    
    private String[][] alternativas = {
            {"0 \n", "1 \n", "2 \n", "3 \n"},
            {"400", "450", "500", "550"},
            {"Barcelona", "Real Madrid", "Bayern de Munique", "Liverpool"}
    };
    
    private String[] respostas = {
            "0",
            "450",
            "Real Madrid"
    };

    private JFrame frame;
    private JPanel panel;
    private JLabel perguntaLabel;
    private JTextField respostaField;
    private JButton proximaPerguntaButton;
    private JLabel feedbackLabel;
    private JLabel pontuacaoLabel;

    public JogoDePerguntasGUI() {
        frame = new JFrame("Jogo de Perguntas e Respostas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        perguntaLabel = new JLabel();
        respostaField = new JTextField();
        proximaPerguntaButton = new JButton("Próxima Pergunta");
        feedbackLabel = new JLabel();
        pontuacaoLabel = new JLabel();

        proximaPerguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarResposta();
            }
        });

        panel.add(perguntaLabel);
        panel.add(respostaField);
        panel.add(proximaPerguntaButton);
        panel.add(feedbackLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(pontuacaoLabel, BorderLayout.SOUTH);

        exibirProximaPergunta();

        frame.setVisible(true);
    }

    private void exibirProximaPergunta() {
        if (perguntaAtual < perguntas.length) {
            perguntaLabel.setText("Pergunta " + (perguntaAtual + 1) + ": " + perguntas[perguntaAtual]);
            respostaField.setText("");
            feedbackLabel.setText("");
            for (int i = 0; i < alternativas[perguntaAtual].length; i++) {
                perguntaLabel.setText(perguntaLabel.getText() + "\n" + (char)('A' + i) + ". " + alternativas[perguntaAtual][i]);
            }
        } else {
            perguntaLabel.setText("Jogo concluído!");
            respostaField.setEnabled(false);
            proximaPerguntaButton.setEnabled(false);
            feedbackLabel.setText("Pontuação final: " + pontuacao);
        }
    }

    private void verificarResposta() {
        String resposta = respostaField.getText();
        if (perguntaAtual < perguntas.length && resposta.equalsIgnoreCase(respostas[perguntaAtual])) {
            feedbackLabel.setText("Resposta correta! Você ganhou 10 pontos.");
            pontuacao += 10;
        } else {
            feedbackLabel.setText("Resposta incorreta. A resposta correta é: " + respostas[perguntaAtual]);
        }
        perguntaAtual++;
        exibirProximaPergunta();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JogoDePerguntasGUI();
            }
        });
    }
}

