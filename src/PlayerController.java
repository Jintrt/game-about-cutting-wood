public class PlayerController {
    private boolean isAlive; // Stores the player's current status (alive or dead)
    private int score; // Stores the player's current score

    /**
     * Constructor for the PlayerController class.
     * Initializes the player as alive and sets the score to zero.
     */
    public PlayerController() {
        this.isAlive = true; // Player starts the game alive
        this.score = 0; // Score starts at 0
    }

    /**
     * Increments the player's score by 1.
     */
    public void incrementScore() {
        this.score += 1; // Increase score when the player successfully chops the tree
    }

    /**
     * Changes the player's status to dead.
     * This is called when the player collides with a branch or runs out of time.
     */
    public void killPlayer() {
        this.isAlive = false; // Player is marked as dead
    }

    /**
     * Checks if the player is still alive.
     *
     * @return true if the player is alive, false if they are dead.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Retrieves the player's current score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Resets the player's state when restarting the game.
     * The player is marked as alive and the score is reset to zero.
     */
    public void restartGame() {
        this.isAlive = true; // Reset player to alive
        this.score = 0; // Reset score to 0
    }
}