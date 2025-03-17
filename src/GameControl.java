import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl extends JPanel implements KeyListener {
    private Terrain terrain; // Represents the game environment (background, score, time)
    private Wood wood; // Represents the tree and its branches
    private Player player; // Represents the player

    private boolean running = true; // Tracks if the game is currently running

    public GameControl() {
        // Initialize game elements
        this.terrain = new Terrain(800, 600, 30); // Game board size: 800x600, initial timer set to 30s
        this.wood = new Wood(400, 600, 8); // Tree starts at position (400, 600) with 8 segments
        this.player = new Player(370, 500, wood); // Player starts near the bottom at (370, 500)

        // Enable keyboard input
        setFocusable(true);
        addKeyListener(this);

        // Start the game loop using a Swing timer (16ms per update, ~60 FPS)
        Timer timer = new Timer(16, e -> gameLoop());
        timer.start();
    }

    //Restarts the game by resetting the terrain, generating a new tree, and resetting the player.
    private void restartGame() {
        terrain.reset(15); // Reset time and score
        wood = new Wood(400, 600, 8); // Generate a new tree
        player = new Player(370, 500, wood); // Create a new player
        running = true; // Allow the game to run again
    }

    /**
     * Main game loop, called by the timer every 16ms.
     * - Updates the game state.
     * - Checks for game-over conditions.
     * - Repaints the screen.
     */
    private void gameLoop() {
        if (!running) return;

        // Check if time has run out
        if (terrain.isTimeUp()) {
            endGame("Time is Up! Score: " + terrain.getScore());
            return;
        }

        // Check if the player was hit by a branch
        if (wood.checkCollision(player.getX(), player.getY(), player.isFacingRight(), player.getHeight())) {
            endGame("You were hit by a branch! Score: " + terrain.getScore());
            return;
        }

        // Update the game state (timer, score, animations)
        terrain.update();
        repaint(); // Refresh the screen
    }

    //Returns the player to the main menu after finishing a game.
    private void returnToMainMenu() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this); // Get the main game window
        frame.getContentPane().removeAll(); // Remove the current game panel

        GameMenu gameMenu = new GameMenu(); // Create the menu screen
        frame.add(gameMenu);
        frame.revalidate(); // Refresh the UI
        frame.repaint();
    }

    /**
     * Handles ending the game when the player loses.
     * - Displays a message.
     * - Closes the game window.
     * - Returns to the main menu.
     */
    private void endGame(String message) {
        running = false; // Stop game updates
        JOptionPane.showMessageDialog(this, message); // Show the game-over message

        // Close the current game window
        SwingUtilities.getWindowAncestor(this).dispose();

        // Return to the game menu
        GameMenu mainMenu = new GameMenu();
        mainMenu.setVisible(true);
    }

    /**
     * Paints the game elements (background, tree, player).
     * This method is automatically called by `repaint()`.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        terrain.draw(g); // Draw the background and UI elements
        wood.draw(g); // Draw the tree and branches
        player.draw(g); // Draw the player
    }

    //Handles key presses for player movement and game actions.

    @Override
    public void keyPressed(KeyEvent e) {
        if (!running) return; // Ignore input if the game is over

        if (e.getKeyCode() == KeyEvent.VK_LEFT) { // Left arrow key
            player.move(false); // Move left
            wood.chop(); // Cut down part of the tree
            terrain.addScore(1); // Increase the score
            terrain.addTime(1); // Add extra time (max 10s limit)
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // Right arrow key
            player.move(true); // Move right
            wood.chop(); // Cut down part of the tree
            terrain.addScore(1); // Increase the score
            terrain.addTime(1); // Add extra time (max 10s limit)
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No action needed on key release
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action needed for typed keys
    }

    //Launches the game by creating a window and adding the game control panel.
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game about cutting wood");
        GameControl gamecontrol = new GameControl();

        frame.add(gamecontrol);
        frame.setSize(800, 600); // Set the initial game window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the app when the window is closed
        frame.setVisible(true); // Show the game window
    }
}