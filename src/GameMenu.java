import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {
    private JPanel menuPanel; // The main panel containing menu elements

    /**
     * Constructor for the GameMenu class.
     * Initializes the main menu window and adds UI components.
     */
    public GameMenu() {
        setTitle("Menu"); // Set the window title
        setSize(800, 625); // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits on close
        setLocationRelativeTo(null); // Center the window on the screen

        // Initialize the main menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Set vertical layout

        // Create a title label for the menu
        JLabel title = new JLabel("Game about cutting wood");
        title.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size and style
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the title

        // Create a "Start" button
        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        startButton.addActionListener(e -> startGame()); // Set action listener to start the game

        // Create an "Exit" button
        JButton exitButton = new JButton("Leave");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        exitButton.addActionListener(e -> System.exit(0)); // Close the application when clicked

        // Add UI elements to the panel with spacing
        menuPanel.add(Box.createVerticalGlue()); // Adds flexible space before the title
        menuPanel.add(title);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Adds spacing between title and start button
        menuPanel.add(startButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Adds spacing between start and exit buttons
        menuPanel.add(exitButton);
        menuPanel.add(Box.createVerticalGlue()); // Adds flexible space after the buttons

        add(menuPanel); // Add the menu panel to the frame

        setVisible(true); // Make the window visible
    }

    /**
     * Starts the game by removing the menu panel and adding the GameControl panel.
     */
    private void startGame() {
        getContentPane().remove(menuPanel); // Remove the menu screen
        GameControl gameControl = new GameControl(); // Create a new game instance
        add(gameControl); // Add the game panel to the window
        revalidate(); // Refresh the window layout
        repaint(); // Redraw the components
        gameControl.requestFocusInWindow(); // Ensure the game panel receives keyboard input
    }

    /**
     * Main method to launch the game menu.
     * Uses SwingUtilities.invokeLater to ensure the UI is created on the Event Dispatch Thread.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMenu::new);
    }
}