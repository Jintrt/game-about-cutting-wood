import java.awt.Graphics;

public class Player {
    private int x, y; // player position
    private int height = 80;
    private boolean facingRight; // Direction: Right = true, Left = false

    public Player(int StartX, int StartY) {
        this.x = StartX;
        this.y = StartY;
        this.facingRight = false; //initially looks to the right
    }

    public int getHeight() { // Getting player height
        return this.height;
    }

    public void draw(Graphics g) {
        //player graphic(Someday I will replace this rectangle with a better model, I promise)
        if (facingRight) {
            g.fillRect(x, y, 35, 100); // right side
        }
        else {
            g.fillRect(x, y, 35, 100); // left side
        }
    }
    public void move(boolean facingRight) {
        this.facingRight = facingRight;
        if (facingRight) {
            x += 30; // move to right
        } else {
            x -= 30; // move to left
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