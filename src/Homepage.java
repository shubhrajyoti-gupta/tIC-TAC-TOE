// HomePage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Tic Tac Toe Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 182, 193), 
                                                     0, getHeight(), new Color(255, 165, 0));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        
        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 40, 20));
        
        JButton newGameButton = new JButton("New Game");
        styleButton(newGameButton, new Color(70, 130, 180), Color.BLUE);
        newGameButton.setFont(new Font("Arial", Font.BOLD, 24));
        newGameButton.setPreferredSize(new Dimension(250, 60));
        newGameButton.addActionListener(e -> {
            dispose();
            new GameBoard();
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 30, 10);
        panel.add(titleLabel, gbc);
        
        gbc.gridy = 1;
        panel.add(newGameButton, gbc);
        
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
