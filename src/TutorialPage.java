// TutorialPage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialPage extends JFrame {
    private String tutorialText = 
        "<html><div style='text-align: center;'>" +
        "<h1>How to Play Tic Tac Toe</h1>" +
        "<p style='font-size: 16px; margin: 20px;'>Tic Tac Toe is a classic game for two players.</p>" +
        "<p style='font-size: 16px; margin: 20px;'>Players take turns placing X and O marks on a 3x3 grid.</p>" +
        "<p style='font-size: 16px; margin: 20px;'>The first player to get 3 of their marks in a row (up, down, across, or diagonally) wins.</p>" +
        "<p style='font-size: 16px; margin: 20px;'>When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.</p>" +
        "</div></html>";

    public TutorialPage() {
        setTitle("Tutorial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(135, 206, 250), 
                                                     0, getHeight(), new Color(147, 112, 219));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        
        JLabel tutorialLabel = new JLabel(tutorialText);
        tutorialLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        tutorialLabel.setForeground(Color.WHITE);
        tutorialLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tutorialLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JButton continueButton = new JButton("Continue");
        styleButton(continueButton, new Color(34, 139, 34), Color.BLUE);
        continueButton.setFont(new Font("Arial", Font.BOLD, 20));
        continueButton.setPreferredSize(new Dimension(200, 50));
        continueButton.addActionListener(e -> {
            dispose();
            new HomePage();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(continueButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        panel.add(tutorialLabel, BorderLayout.CENTER);
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
}
