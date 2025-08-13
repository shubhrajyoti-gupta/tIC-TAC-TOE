// ResultPage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPage extends JFrame {
    private static int player1Wins = 0;
    private static int player2Wins = 0;
    private static int ties = 0;
    
    public ResultPage(String result, String type) {
        setTitle("Game Result");
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
                
                Color color1, color2;
                if ("win".equals(type)) {
                    color1 = new Color(50, 205, 50);
                    color2 = new Color(34, 139, 34);
                } else {
                    color1 = new Color(255, 215, 0);
                    color2 = new Color(255, 165, 0);
                }
                
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        
        // Update scores
        if (result.contains("Player 1")) {
            player1Wins++;
        } else if (result.contains("Player 2")) {
            player2Wins++;
        } else {
            ties++;
        }
        
        JLabel resultLabel = new JLabel(result, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 36));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        
        JButton scoreboardButton = new JButton("View Scoreboard");
        styleButton(scoreboardButton, new Color(70, 130, 180), Color.WHITE);
        scoreboardButton.setFont(new Font("Arial", Font.BOLD, 20));
        scoreboardButton.setPreferredSize(new Dimension(250, 50));
        scoreboardButton.addActionListener(e -> {
            dispose();
            new ScoreboardPage();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(scoreboardButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        
        panel.add(resultLabel, BorderLayout.CENTER);
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
    
    public static void resetScores() {
        player1Wins = 0;
        player2Wins = 0;
        ties = 0;
    }
    
    public static int[] getScores() {
        return new int[]{player1Wins, player2Wins, ties};
    }
}
