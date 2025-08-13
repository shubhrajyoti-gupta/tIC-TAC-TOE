// ScoreboardPage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreboardPage extends JFrame {
    public ScoreboardPage() {
        setTitle("Scoreboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(148, 0, 211), 
                                                     0, getHeight(), new Color(75, 0, 130));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Scoreboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        
        int[] scores = ResultPage.getScores();
        
        JPanel scorePanel = new JPanel();
        scorePanel.setOpaque(false);
        scorePanel.setLayout(new GridLayout(3, 2, 10, 20));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Player 1 score
        JLabel player1Label = new JLabel("Player 1 Wins:", SwingConstants.RIGHT);
        player1Label.setFont(new Font("Arial", Font.BOLD, 20));
        player1Label.setForeground(Color.WHITE);
        JLabel player1Score = new JLabel(String.valueOf(scores[0]), SwingConstants.LEFT);
        player1Score.setFont(new Font("Arial", Font.BOLD, 20));
        player1Score.setForeground(Color.WHITE);
        
        // Player 2 score
        JLabel player2Label = new JLabel("Player 2 Wins:", SwingConstants.RIGHT);
        player2Label.setFont(new Font("Arial", Font.BOLD, 20));
        player2Label.setForeground(Color.WHITE);
        JLabel player2Score = new JLabel(String.valueOf(scores[1]), SwingConstants.LEFT);
        player2Score.setFont(new Font("Arial", Font.BOLD, 20));
        player2Score.setForeground(Color.WHITE);
        
        // Ties
        JLabel tiesLabel = new JLabel("Ties:", SwingConstants.RIGHT);
        tiesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tiesLabel.setForeground(Color.WHITE);
        JLabel tiesScore = new JLabel(String.valueOf(scores[2]), SwingConstants.LEFT);
        tiesScore.setFont(new Font("Arial", Font.BOLD, 20));
        tiesScore.setForeground(Color.WHITE);
        
        scorePanel.add(player1Label);
        scorePanel.add(player1Score);
        scorePanel.add(player2Label);
        scorePanel.add(player2Score);
        scorePanel.add(tiesLabel);
        scorePanel.add(tiesScore);
        
        JButton playAgainButton = new JButton("Play Again");
        styleButton(playAgainButton, new Color(255, 69, 0), Color.BLUE);
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 20));
        playAgainButton.setPreferredSize(new Dimension(200, 50));
        playAgainButton.addActionListener(e -> {
            dispose();
            new GameBoard();
        });

        JButton homeButton = new JButton("Home");
        styleButton(homeButton, new Color(30, 144, 255), Color.BLUE);
        homeButton.setFont(new Font("Arial", Font.BOLD, 20));
        homeButton.setPreferredSize(new Dimension(200, 50));
        homeButton.addActionListener(e -> {
            dispose();
            new HomePage();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(playAgainButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(homeButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scorePanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(panel);
        setVisible(true);
    }
    
    private void styleButton(JButton button, Color bgColor, Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    // Reset scores when application closes
    public static void resetScores() {
        ResultPage.resetScores();
    }
}
