import javax.swing.*;
import java.awt.*;

public class SplashScreen {

    // public static void showSplashAndStart() {
    //     // --- CODE SMELL: Long Method, Magic Numbers, Duplicates, Dead Code ---
    //     JFrame frame = new JFrame("Splash");
    //     frame.setUndecorated(true);
    //     frame.setSize(400, 200);
    //     frame.setLocationRelativeTo(null);
    //     frame.getContentPane().setBackground(new Color(30, 87, 120));

    //     JLabel lbl = new JLabel("Calculator Loading...", JLabel.CENTER);
    //     lbl.setForeground(Color.WHITE);
    //     lbl.setFont(new Font("Arial", Font.BOLD, 18));

    //     ImageIcon icon = new ImageIcon("loading.gif"); // hardcoded path
    //     JLabel iconLabel = new JLabel(icon);

    //     JPanel panel = new JPanel(new BorderLayout());
    //     panel.setBackground(new Color(30, 87, 120));
    //     panel.add(lbl, BorderLayout.CENTER);
    //     panel.add(iconLabel, BorderLayout.SOUTH);

    //     frame.add(panel);
    //     frame.setVisible(true);

    //     try {
    //         Thread.sleep(1000);
    //         System.out.println("Loading 33%");
    //         Thread.sleep(1000);
    //         System.out.println("Loading 66%");
    //         Thread.sleep(1000);
    //         System.out.println("Loading 100%");
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }

    //     // poor naming, direct call to start main app
    //     startMainApp(frame);
    // }

    public static void showSplashAndStart() {
    JFrame frame = createSplashFrame();
    showProgressSteps();
    startMainApp(frame);
}

/**
 * Creates and configures the splash screen window.
 */
private static JFrame createSplashFrame() {
    JFrame frame = new JFrame("Splash");
    frame.setUndecorated(true);
    frame.setSize(400, 200);
    frame.setLocationRelativeTo(null);
    frame.getContentPane().setBackground(new Color(30, 87, 120));

    JLabel label = new JLabel("Calculator Loading...", JLabel.CENTER);
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Arial", Font.BOLD, 18));

    JLabel iconLabel = new JLabel(new ImageIcon("loading.gif"));

    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(30, 87, 120));
    panel.add(label, BorderLayout.CENTER);
    panel.add(iconLabel, BorderLayout.SOUTH);

    frame.add(panel);
    frame.setVisible(true);
    return frame;
}

/**
 * Displays simulated loading progress in console.
 */
private static void showProgressSteps() {
    int[] progressSteps = {33, 66, 100};
    for (int step : progressSteps) {
        pause(1000);
        System.out.println("Loading " + step + "%");
    }
}

/**
 * Handles thread sleep safely.
 */
private static void pause(int millis) {
    try {
        Thread.sleep(millis);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.err.println("Splash screen interrupted.");
    }
}


    private static void startMainApp(JFrame f) {
        f.dispose();
        new Calculator().setVisible(true); // assume Calculator.java exists
    }

    // ------------------------------------------------------
    // Below methods are added intentionally with different smells
    // ------------------------------------------------------

    // Method 1: Poor naming, unused parameter, confusing purpose
    public static void doStuff(int x) {
        // CODE SMELL: meaningless name, unused parameter
        System.out.println("Doing stuff... but not really using x = " + x);
        String s = "Calculator";
        for (int i = 0; i < 3; i++) { // duplicate logic from main splash
            System.out.println(s + " loading part " + (i + 1));
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                // swallowed exception
            }
        }
    }

    // Method 2: Duplicated code and hardcoded values
    public static void loadFakeProgress() {
        // CODE SMELL: duplicate of loading logic from showSplashAndStart()
        for (int i = 1; i <= 3; i++) {
            System.out.println("Fake Loading " + (i * 33) + "%");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method 3: Feature envy and poor cohesion
    public static void showMessageInAnotherWay() {
        // CODE SMELL: unrelated UI creation in same class
        JFrame f = new JFrame("Extra Message");
        f.setSize(300, 150);
        JLabel msg = new JLabel("Welcome Again!", JLabel.CENTER);
        msg.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        f.add(msg);
        f.setLocation(500, 300);
        f.setVisible(true);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f.dispose();
    }

    // Method 4: Overly complex and mixed responsibilities
    public static String doEverything(int a, int b, String name) {
        // CODE SMELL: mixed logic - calculation, UI, string manipulation
        int result = a + b;
        System.out.println("Sum = " + result);
        JOptionPane.showMessageDialog(null, "Hi " + name + ", result is " + result);
        if (result > 10) {
            showMessageInAnotherWay(); // circular dependency smell
        }
        return name + result; // poor return purpose
    }
}
