import java.awt.*;

public class Terrain {
    private int width, height; // Dimensions of the game area
    private int groundY; // Y-coordinate of the ground level
    private int score; // Current game score
    private int timeLeft; // Remaining time in seconds
    private int timerInterval = 1000; // Time update interval (in milliseconds)
    private long lastUpdateTime; // Stores the last time the timer was updated

    /**
     * Constructor for the Terrain class.
     * Initializes the game board dimensions, ground position, score, and timer.
     *
     * @param width Width of the game area
     * @param height Height of the game area
     * @param initialTime Initial time available for the player (in seconds)
     */
    public Terrain(int width, int height, int initialTime) {
        this.width = width;
        this.height = height;
        this.groundY = height - 40; // The ground is set 40 pixels above the bottom of the screen
        score = 0;
        this.timeLeft = initialTime; // Set the initial time limit
        this.lastUpdateTime = System.currentTimeMillis(); // Store the current time as the last update time
    }

    /**
     * Draws the background, score, and time display.
     *
     * @param g The Graphics object used for drawing
     */
    public void draw(Graphics g) {
        // Draw background color (light blue)
        g.setColor(new Color(135, 206, 250));
        g.fillRect(0, 0, width, height);

        // Draw the score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);

        // Draw the remaining time
        g.drawString("Time Left: " + timeLeft, 10, 60);
    }

    /**
     * Updates the timer by decreasing the remaining time every second.
     */
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= timerInterval) { // If one second has passed
            if (timeLeft > 0) { // Prevents negative time values
                timeLeft--; // Reduce remaining time
            }
            lastUpdateTime = currentTime; // Update the last recorded time
        }
    }

    /**
     * Increases the player's score by a given number of points.
     *
     * @param points The number of points to add
     */
    public void addScore(int points) {
        score += points;
    }

    /**
     * Adds extra time to the player's remaining time, with a maximum limit of 10 seconds.
     *
     * @param seconds The number of extra seconds to add
     */
    public void addTime(int seconds) {
        timeLeft = Math.min(timeLeft + seconds, 10); // Ensures time never exceeds 10 seconds
    }

    /**
     * Checks if the game time has run out.
     *
     * @return true if timeLeft is 0 or below, false otherwise
     */
    public boolean isTimeUp() {
        return timeLeft <= 0;
    }

    /**
     * Resets the game state, including score and time.
     *
     * @param newTime The new time limit for the restarted game
     */
    public void reset(int newTime) {
        score = 0;
        timeLeft = newTime;
        lastUpdateTime = System.currentTimeMillis(); // Reset the timer to current time
    }

    /**
     * Retrieves the player's current score.
     *
     * @return The current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the Y-coordinate of the ground level.
     * Ensures the player stays positioned correctly on the ground.
     *
     * @return The Y-position of the ground
     */
    public int getGroundY() {
        return groundY;
    }
}