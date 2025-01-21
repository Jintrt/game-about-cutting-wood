import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {
    private JPanel menuPanel;

    public GameMenu() {
        setTitle("Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("game about cutting wood");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> startGame());

        JButton exitButton = new JButton("Leave");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));

        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(title);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(startButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(exitButton);
        menuPanel.add(Box.createVerticalGlue());

        add(menuPanel);

        setVisible(true);
    }

    private void startGame() {
        getContentPane().remove(menuPanel); // delete menu screen
        GameControl gameControl = new GameControl();
        add(gameControl);
        revalidate(); // refresh window
        repaint();
        gameControl.requestFocusInWindow(); // pass focus to game
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMenu::new);
    }
}