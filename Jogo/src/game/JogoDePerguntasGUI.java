package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDePerguntasGUI extends JFrame {
    private int pontuacao = 0;
    private int perguntaAtual = 0;

    private String[][] alternativas = {
            {"Nenhuma", "Uma", "Duas", "Três"},
            {"400 Gols", "450 Gols", "500 Gols", "550 Gols"},
            {"Barcelona", "Real Madrid", "Bayern de Munique", "Liverpool"}
    };

    private String[] perguntas = {
            "Quantas Bola de Ouro tem o Neymar?",
            "Quantos Gols Cristiano Ronaldo fez pelo Real Madrid?",
            "Quem é o Clube com mais Champions League?"
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

    public JogoDePerguntasGUI() {
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
                exibirProximaPergunta();
            }
        });
        
        
        feedbackLabel = new JLabel();
        pontuacaoLabel = new JLabel();

        panel.add(perguntaLabel);
        panel.add(proximaPerguntaButton);
        panel.add(feedbackLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(pontuacaoLabel, BorderLayout.SOUTH);
        


        exibirProximaPergunta();

        frame.setVisible(true);
        
        JButton tentarNovamenteButton = new JButton("Tentar Novamente");
        
        tentarNovamenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
                feedbackLabel.setText("          JOGO REINICIADO");
            }
        });
        panel.add(tentarNovamenteButton);
    }
    

    private void exibirProximaPergunta() {
        if (perguntaAtual < perguntas.length) {
            perguntaLabel.setText("Pergunta " + (perguntaAtual + 1) + ": " + perguntas[perguntaAtual]);

            for (int i = 0; i < alternativaButtons.length; i++) {
                alternativaButtons[i].setText((char)('A' + i) + ". " + alternativas[perguntaAtual][i]);
            }

            proximaPerguntaButton.setEnabled(false);
            feedbackLabel.setText("");
        } else {
            perguntaLabel.setText("Jogo concluído!");
            proximaPerguntaButton.setEnabled(false);
            feedbackLabel.setText("Pontuação final: " + pontuacao);
        }
    }

    private class AlternativaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
            String respostaEscolhida = sourceButton.getText().substring(3); 
            verificarResposta(respostaEscolhida);
        }
    }

    private void verificarResposta(String respostaEscolhida) {
        if (respostaEscolhida.equalsIgnoreCase(respostas[perguntaAtual])) {
            feedbackLabel.setText("Resposta correta! Você ganhou 10 pontos.");
            pontuacao += 10;
            proximaPerguntaButton.setEnabled(true);
        } else {
        	feedbackLabel.setText("Resposta incorreta.");
        	proximaPerguntaButton.setEnabled(false);
            tentarNovamenteButton.setEnabled(true); 
            
        }

        perguntaAtual++;
    }
    private void reiniciarJogo() {
        perguntaAtual = 0;
        pontuacao = 0;
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
