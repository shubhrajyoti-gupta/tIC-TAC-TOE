// GameBoard.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JFrame {
    private JPanel[][] cells = new JPanel[3][3];
    private JLabel[][] labels = new JLabel[3][3];
    private boolean player1Turn = true;
    private int moveCount = 0;
    private String player1Name = "Player 1";
    private String player2Name = "Player 2";

    public GameBoard() {
        setTitle("Tic Tac Toe - Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(173, 216, 230), // Light blue
                                                     0, getHeight(), new Color(135, 206, 250)); // Periwinkle
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create game grid
        JPanel gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Fill background with light gray
                g.setColor(new Color(240, 240, 240)); // Light gray
                g.fillRect(0, 0, getWidth(), getHeight());

                // Draw grid lines
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(70, 70, 70)); // Dark gray
                g2d.setStroke(new BasicStroke(4));

                int width = getWidth();
                int height = getHeight();
                int cellWidth = width / 3;
                int cellHeight = height / 3;

                // Draw vertical lines
                for (int i = 1; i < 3; i++) {
                    g2d.drawLine(i * cellWidth, 0, i * cellWidth, height);
                }

                // Draw horizontal lines
                for (int i = 1; i < 3; i++) {
                    g2d.drawLine(0, i * cellHeight, width, i * cellHeight);
                }
            }
        };
        gridPanel.setLayout(new GridLayout(3, 3));
        gridPanel.setPreferredSize(new Dimension(400, 400));
        gridPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 3)); // Dark gray border

        // Create grid cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new JPanel(new BorderLayout());
                cells[i][j].setBackground(new Color(240, 240, 240)); // Light gray
                // Add boundary line to each cell
                cells[i][j].setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 1)); // Gray border

                labels[i][j] = new JLabel("", SwingConstants.CENTER);
                labels[i][j].setFont(new Font("Arial", Font.BOLD, 80));
                labels[i][j].setForeground(new Color(30, 30, 30)); // Dark gray text
                labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                labels[i][j].setVerticalAlignment(SwingConstants.CENTER);
                cells[i][j].add(labels[i][j], BorderLayout.CENTER);

                final int row = i;
                final int col = j;

                cells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleCellClick(row, col);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (labels[row][col].getText().equals("")) {
                            cells[row][col].setBackground(new Color(220, 220, 220)); // Lighter gray on hover
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        cells[row][col].setBackground(new Color(240, 240, 240)); // Restore original color
                    }
                });

                gridPanel.add(cells[i][j]);
            }
        }

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private void handleCellClick(int row, int col) {
        // If cell already has content, do nothing
        if (!labels[row][col].getText().equals("")) {
            return;
        }

        // Set the mark (X or O)
        if (player1Turn) {
            labels[row][col].setText("X");
        } else {
            labels[row][col].setText("O");
        }

        moveCount++;

        if (checkWin()) {
            String winner = player1Turn ? player1Name : player2Name;
            dispose();
            new ResultPage(winner + " Wins!", "win");
        } else if (moveCount == 9) {
            dispose();
            new ResultPage("It's a Tie!", "tie");
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (checkLine(labels[i][0], labels[i][1], labels[i][2])) {
                highlightWinningLine(new int[]{i,0}, new int[]{i,1}, new int[]{i,2});
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(labels[0][i], labels[1][i], labels[2][i])) {
                highlightWinningLine(new int[]{0,i}, new int[]{1,i}, new int[]{2,i});
                return true;
            }
        }

        // Check diagonals
        if (checkLine(labels[0][0], labels[1][1], labels[2][2])) {
            highlightWinningLine(new int[]{0,0}, new int[]{1,1}, new int[]{2,2});
            return true;
        }
        if (checkLine(labels[0][2], labels[1][1], labels[2][0])) {
            highlightWinningLine(new int[]{0,2}, new int[]{1,1}, new int[]{2,0});
            return true;
        }

        return false;
    }

    private void highlightWinningLine(int[] pos1, int[] pos2, int[] pos3) {
        labels[pos1[0]][pos1[1]].setForeground(new Color(220, 20, 60)); // Crimson
        labels[pos2[0]][pos2[1]].setForeground(new Color(220, 20, 60)); // Crimson
        labels[pos3[0]][pos3[1]].setForeground(new Color(220, 20, 60)); // Crimson
    }

    private boolean checkLine(JLabel l1, JLabel l2, JLabel l3) {
        String text1 = l1.getText();
        String text2 = l2.getText();
        String text3 = l3.getText();
        
        return !text1.equals("") && text1.equals(text2) && text2.equals(text3);
    }
}
