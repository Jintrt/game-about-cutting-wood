import java.awt.*;

public class Terrain {
    private int width, height; // Board dimensions
    private int score; // Actual score
    private int timeLeft; // Time left in seconds
    private int timerInterval = 1000; // Time interval in milliseconds
    private long lastUpdateTime; // Last update time

    public Terrain(int width, int height, int initialTime) {
        this.width = width;
        this.height = height;
        score = 0;
        this.timeLeft = initialTime;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    // Background, score, time drawing
    public void draw(Graphics g) {
        //Background
        g.setColor(new Color(135, 206, 250)); // Light-blue
        g.fillRect(0, 0, width, height);

        // score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score,10, 20);

        // Time left
        g.drawString("TimeLeft: " + timeLeft, 10, 60);
    }
    // Game time actualization
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= timerInterval) {
            timeLeft--;
            lastUpdateTime = currentTime;
        }
    }

    // Adding points
    public void addScore(int points) {
        score += points;
    }
    public boolean isTimeUp() {
        return timeLeft <= 0;
    }

    // Restarting game
    public void reset(int newTime) {
        score = 0;
        timeLeft = newTime;
        lastUpdateTime = System.currentTimeMillis();
    }

    public int getScore() {
        return score;
    }
}