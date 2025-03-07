import java.awt.*;

public class Player {
    private int x, y; // player position
    private int height = 40; // Player height
    private int width = 25; // Player width
    private boolean facingRight; // Direction: Right = true, Left = false
    private Wood wood; // Reference to the Wood object

    public Player(int startX, int startY, Wood wood) {
        this.x = startX;
        this.y = 560;
        this.facingRight = false; // Initially looks to the left
        this.wood = wood; // Assign the reference
    }

    public void updatePosition(Terrain terrain) {
        this.y = terrain.getGroundY(); // it is updating player position
    }

    public int getHeight() { // Getting player height
        return this.height;
    }

    public void draw(Graphics g) {
        // Drawing the player
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);
    }

    public void move(boolean toRight) {
        if (toRight) {
            x = wood.getTreeX() + wood.getTreeWidth(); // Move to the right side of the tree
            facingRight = true;
        } else {
            x = wood.getTreeX() - width; // Move to the left side of the tree
            facingRight = false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFacingRight() {
        return facingRight;
    }
}