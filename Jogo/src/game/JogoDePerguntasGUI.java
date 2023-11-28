package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


interface EstrategiaPontuacao {
    int calcularPontuacao(boolean respostaCorreta);
}
class EstrategiaPontuacaoPadrao implements EstrategiaPontuacao {
    @Override
    public int calcularPontuacao(boolean respostaCorreta) {
        return respostaCorreta ? 10 : 0;
    }
}

class EstrategiaPontuacaoDesafio implements EstrategiaPontuacao {
    @Override
    public int calcularPontuacao(boolean respostaCorreta) {
        return respostaCorreta ? 20 : 0;
    }
}

public class JogoDePerguntasGUI extends JFrame {
	private int pontuacao;
    private int perguntaAtual = 0;
    private EstrategiaPontuacao estrategiaPontuacao;


    private String[] perguntas = {
            "Quantas Bola de Ouro tem o Neymar?",
            "Quantos Gols Cristiano Ronaldo fez pelo Real Madrid?",
            "Quem é o Clube com mais Champions League?"
    };

    private String[][] alternativas = {
            {"Nenhuma", "Uma", "Duas", "Três"},
            {"400 Gols", "450 Gols", "500 Gols", "550 Gols"},
            {"Barcelona", "Real Madrid", "Bayern de Munique", "Liverpool"}
    };

    private String[] respostas = {
            "Nenhuma",
            "450 Gols",
            "Real Madrid"
    };

    private JFrame frame;
    private JPanel panel;
    private JLabel perguntaLabel;
    private JButton[] alternativaButtons;
    private JButton proximaPerguntaButton;
    private JButton tentarNovamenteButton;
    private JLabel feedbackLabel;
    private JLabel pontuacaoLabel;

    public JogoDePerguntasGUI(EstrategiaPontuacao estrategiaPontuacao) {
    	this.estrategiaPontuacao = estrategiaPontuacao;
        this.pontuacao = estrategiaPontuacao.calcularPontuacao(true);
    	frame = new JFrame("Jogo de Perguntas e Respostas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 300);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        perguntaLabel = new JLabel();
        alternativaButtons = new JButton[4];
        for (int i = 0; i < alternativaButtons.length; i++) {
            alternativaButtons[i] = new JButton();
            alternativaButtons[i].addActionListener(new AlternativaButtonListener());
            panel.add(alternativaButtons[i]);
        }
        proximaPerguntaButton = new JButton("Próxima Pergunta");
        proximaPerguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirProximaTela();
            }
        });

        feedbackLabel = new JLabel();
        pontuacaoLabel = new JLabel();

        panel.add(perguntaLabel);
        panel.add(proximaPerguntaButton);
        panel.add(feedbackLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(pontuacaoLabel, BorderLayout.SOUTH);

        exibirProximaTela();

        frame.setVisible(true);

        tentarNovamenteButton = new JButton("Tentar Novamente");

        tentarNovamenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
                feedbackLabel.setText("          JOGO REINICIADO");
            }
        });
        panel.add(tentarNovamenteButton);
        iniciarJogo();
    }
    
    private void iniciarJogo() {
        pontuacao = 0;
        exibirProximaTela();
    }
    
    private void exibirProximaTela() {
        if (perguntaAtual < perguntas.length) {
            perguntaLabel.setText("Pergunta " + (perguntaAtual + 1) + ": " + perguntas[perguntaAtual]);

            for (int i = 0; i < alternativaButtons.length; i++) {
                alternativaButtons[i].setText((char) ('A' + i) + ". " + alternativas[perguntaAtual][i]);
            }

            proximaPerguntaButton.setEnabled(false);
            feedbackLabel.setText("");
        } else {
            panel.remove(perguntaLabel);
            panel.remove(feedbackLabel);

            panel.remove(proximaPerguntaButton);
            panel.remove(tentarNovamenteButton);
            for (JButton button : alternativaButtons) {
                panel.remove(button);
            }

            JLabel jogoConcluidoLabel = new JLabel("Jogo concluído!");
            jogoConcluidoLabel.setFont(new Font("Arial", Font.BOLD, 30));
            jogoConcluidoLabel.setHorizontalAlignment(JLabel.CENTER);
            panel.add(jogoConcluidoLabel);

            JLabel pontuacaoFinalLabel = new JLabel("Pontuação final: " + pontuacao);
            pontuacaoFinalLabel.setFont(new Font("Arial", Font.BOLD, 20));
            pontuacaoFinalLabel.setHorizontalAlignment(JLabel.CENTER);
            panel.add(pontuacaoFinalLabel);

            frame.revalidate();
            frame.repaint();
        }
    }

    private class AlternativaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
            String respostaEscolhida = sourceButton.getText().substring(3);
            boolean respostaCorreta = verificarResposta(respostaEscolhida);
            pontuacao += estrategiaPontuacao.calcularPontuacao(respostaCorreta);

            exibirFeedback(respostaCorreta);
        }
    }

    private boolean verificarResposta(String respostaEscolhida) {
        return respostaEscolhida.equalsIgnoreCase(respostas[perguntaAtual]);
    }

    private void exibirFeedback(boolean respostaCorreta) {
        if (respostaCorreta) {
            feedbackLabel.setText("Resposta correta! Você ganhou " + estrategiaPontuacao.calcularPontuacao(true) + " pontos.");
            proximaPerguntaButton.setEnabled(true);
            
        } else {
            feedbackLabel.setText("Resposta incorreta. Você ganhou " + estrategiaPontuacao.calcularPontuacao(false) + " pontos.");
            proximaPerguntaButton.setEnabled(false);
            tentarNovamenteButton.setEnabled(true);
        }
        perguntaAtual++;
    }

    private void reiniciarJogo() {
        perguntaAtual = 0;
        pontuacao = 0;
        exibirProximaTela();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModoPontuacaoGUI();
            }
        });
    }}