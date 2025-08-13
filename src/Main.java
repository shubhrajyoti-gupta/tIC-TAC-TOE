// Main.java
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Try to set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // If that fails, continue with default look and feel
                System.err.println("Could not set system look and feel: " + e.getMessage());
            }

            new StartupPage();
        });
    }
}
