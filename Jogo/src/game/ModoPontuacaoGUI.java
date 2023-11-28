package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModoPontuacaoGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JButton modoPadraoButton;
    private JButton modoDesafioButton;
    
    public ModoPontuacaoGUI() {
        frame = new JFrame("Escolha o Modo de Pontuação");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());
      
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        modoPadraoButton = new JButton("Modo Padrão");
        modoDesafioButton = new JButton("Modo Desafio");

        modoPadraoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(new EstrategiaPontuacaoPadrao());
            }
        });

        modoDesafioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(new EstrategiaPontuacaoDesafio());
            }
        });

        panel.add(modoPadraoButton);
        panel.add(modoDesafioButton);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void iniciarJogo(EstrategiaPontuacao estrategiaPontuacao) {
        frame.dispose(); 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JogoDePerguntasGUI(estrategiaPontuacao);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModoPontuacaoGUI();
            }
        });
    }
}
