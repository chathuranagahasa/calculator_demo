import javax.swing.*;
import java.awt.*;

public class SplashScreen {

    public static void showSplashAndStart() {
        // --- CODE SMELL EXAMPLE METHOD (long, duplicated, poor naming) ---
        // This method violates multiple clean code principles intentionally.

        JFrame frame = new JFrame("Splash");
        frame.setUndecorated(true);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(30, 87, 120));

        JLabel lbl = new JLabel("Calculator Loading...", JLabel.CENTER);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));

        // Adding icon (loading gif)
        ImageIcon icon = new ImageIcon("loading.gif"); // hardcoded path
        JLabel iconLabel = new JLabel(icon);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 87, 120));
        panel.add(lbl, BorderLayout.CENTER);
        panel.add(iconLabel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // --- Duplicate behavior below (intentional code smell) ---
        try {
            Thread.sleep(1000); // Magic number
            System.out.println("Loading 33%");
            Thread.sleep(1000);
            System.out.println("Loading 66%");
            Thread.sleep(1000);
            System.out.println("Loading 100%");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Uncommented code (dead code example)
        // frame.dispose(); 
        // System.out.println("Old splash closed.");

        // poor naming, direct call to start main app
        startMainApp(frame);
    }

    private static void startMainApp(JFrame f) {
        f.dispose(); // now close splash
        // Another hardcoded direct call
        new CalculatorUI().setVisible(true); // assume Calculator.java exists in your project
    }
}
