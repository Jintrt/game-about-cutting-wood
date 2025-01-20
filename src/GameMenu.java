import javax.swing.*;
import java.awt.*;

public class GameMenu extends JPanel {
    private int bestScore = 0;
    private GameControl gameControl;

    public GameMenu() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("game about cutting wood", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(e -> startGame());
        add(startButton, BorderLayout.CENTER);

        JLabel bestScoreLabel = new JLabel("Best Score: " + bestScore, SwingConstants.CENTER);
        bestScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(bestScoreLabel, BorderLayout.SOUTH);
    }

    public void setBestScore(int score) {
        bestScore = Math.max(bestScore, score);
    }

    private void startGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.remove(this);
        gameControl = new GameControl(); // Creates new Game
        frame.add(gameControl);
        frame.revalidate();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("game  about cutting wood");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new GameMenu());
        frame.setVisible(true);
    }
}
