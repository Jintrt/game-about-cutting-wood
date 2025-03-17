import java.awt.*;

public class Player {
    private int x, y; // Player's position on the screen
    private int height = 40; // Player's height in pixels
    private int width = 25; // Player's width in pixels
    private boolean facingRight; // Indicates the direction the player is facing (true = right, false = left)
    private Wood wood; // Reference to the Wood object (tree), used for positioning

    /**
     * Constructor for the Player class.
     * Initializes the player's starting position and associates it with the tree (Wood object).
     *
     * @param startX Initial X coordinate of the player
     * @param startY Initial Y coordinate of the player
     * @param wood Reference to the tree (Wood) for positioning
     */
    public Player(int startX, int startY, Wood wood) {
        this.x = startX;
        this.y = 560; // Hardcoded y position, possibly needs dynamic calculation based on terrain
        this.facingRight = false; // Initially facing left
        this.wood = wood; // Store reference to the tree for positioning
    }

    /**
     * Updates the player's position based on the terrain.
     * Ensures the player stays on the ground level.
     *
     * @param terrain Reference to the Terrain object for retrieving ground level
     */
    public void updatePosition(Terrain terrain) {
        this.y = terrain.getGroundY(); // Update player's position to match the ground level
    }

    /**
     * Returns the height of the player.
     *
     * @return Player's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Draws the player on the screen.
     *
     * @param g Graphics object used for drawing
     */
    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY); // Set player color
        g.fillRect(x, y, width, height); // Draw player as a rectangle
    }

    /**
     * Moves the player left or right based on the key input.
     *
     * @param toRight If true, the player moves to the right side of the tree; otherwise, moves left
     */
    public void move(boolean toRight) {
        if (toRight) {
            x = wood.getTreeX() + wood.getTreeWidth(); // Move to the right side of the tree
            facingRight = true;
        } else {
            x = wood.getTreeX() - width; // Move to the left side of the tree
            facingRight = false;
        }
    }

    /**
     * Gets the player's X coordinate.
     *
     * @return The player's current X position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the player's Y coordinate.
     *
     * @return The player's current Y position
     */
    public int getY() {
        return y;
    }

    /**
     * Checks if the player is facing right.
     *
     * @return true if the player is facing right, false if facing left
     */
    public boolean isFacingRight() {
        return facingRight;
    }
}