import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl extends JPanel implements KeyListener {
    private Terrain terrain;
    private Wood wood;
    private Player player;

    private boolean running = true;

    public GameControl() {
        // Initialization of game elements
        this.terrain = new Terrain(800, 600, 30); // A board with dimensions of 800 by 600 and an initialization time of 30s
        this.wood = new Wood(400, 600, 8); // Tree on a center of display with 8 segments
        this.player = new Player(370, 500, wood); // Player at the bottom of the board

        setFocusable(true);
        addKeyListener(this);

        // Game loop
        Timer timer = new Timer(16, e -> gameLoop());
        timer.start();
    }

    private void restartGame() {
        terrain.reset(15); // restart time and score
        wood = new Wood(400, 600, 8); // New tree
        player = new Player(370, 500, wood); // New player
        running = true;
    }

    // Main game loop
    private void gameLoop() {
        if (!running) return;

        // checking if time is up
        if (terrain.isTimeUp()) {
            endGame("Time is Up! Score: " + terrain.getScore());
            return;
        }

        // checking if player touched branch
        if (wood.checkCollision(player.getX(), player.getY(), player.isFacingRight(), player.getHeight())) {
            endGame("you were hit by branch! score: " + terrain.getScore());
            return;
        }

        // refresh elements
        terrain.update(); // clock update
        repaint();
    }

    private void returnToMainMenu() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this); // Download upper window
        frame.getContentPane().removeAll(); //Delete current panel (GameControl)

        GameMenu gameMenu = new GameMenu();
        frame.add(gameMenu);
        frame.revalidate();
        frame.repaint();

    }

    // endGame method
    private void endGame(String message) {
        running = false;
        JOptionPane.showMessageDialog(this, "Unfortunately, you are dead! Score: " + terrain.getScore());

        // Close current window
        SwingUtilities.getWindowAncestor(this).dispose();

        // Game menu comback option
        GameMenu mainMenu = new GameMenu();
        mainMenu.setVisible(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        terrain.draw(g);
        wood.draw(g);
        player.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!running) return;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.move(false); // Move left
            wood.chop(); // Cutting down tree
            terrain.addScore(1); // Adding a point
            terrain.addTime(1); // adding time
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.move(true); // Move right
            wood.chop(); // Cutting down tree
            terrain.addScore(1);
            terrain.addTime(1); // adding time
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Launching the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game about cutting wood");
        GameControl gamecontrol = new GameControl();

        frame.add(gamecontrol);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}