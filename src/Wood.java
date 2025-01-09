import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wood {
    private int treeX, treeY; // tree position
    private int segmentHeight = 50; // Height of one tree segment
    private int treeWidth = 50; // Width of the tree
    private List<String> branches; // Branches List: "left", "right" lub ""
    private Random random = new Random();

    public Wood(int treeX, int treeY, int segments) {
        this.treeX = treeX;
        this.treeY = treeY;
        this.branches = new ArrayList<>();

        // Generating initial segments
        for (int i = 0; i < segments; i++) {
            branches.add(generateBranchSide());
        }
    }

    // Tree and branches drawing
    public void draw(Graphics g) {
        for (int i = 0; i < branches.size(); i++) {
            int yPosition = treeY - i * segmentHeight;

            // Tree segment drawing
            g.setColor(new Color(139, 69, 19)); // Tree colour
            g.fillRect(treeX, yPosition, treeWidth, segmentHeight);

            // Branches drawing
            String side = branches.get(i);
            g.setColor(Color.GREEN);
            if ("left".equals(side)) {
                g.fillRect(treeX - 30, yPosition + segmentHeight / 2 - 5, 30, 10); // Branch on the left
            } else if ("right".equals(side)) {
                g.fillRect(treeX + treeWidth, yPosition + segmentHeight / 2 - 5, 30, 10); // Branch on the right
            }
        }
    }

    // Cutting down the tree
    public void chop() {
        if (!branches.isEmpty()) {
            branches.remove(branches.size() - 1); // Removing a cut piece of wood
            branches.add(0, generateBranchSide()); // Adding new tree segment
        }
    }

    // Generating random branch side
    private String generateBranchSide() {
        int side = random.nextInt(3); // 0 = no branches at all, 1 = branch on a left, 2 = branch on a right
        switch (side) {
            case 1:
                return "left";
            case 2:
                return "right";
            default:
                return "";
        }
    }

    // Checking collision with a player
    public boolean checkCollision(int playerX, int playerY, boolean playerFacingRight, int playerWidth) {
        int firstBranchY = treeY - (branches.size() - 1) * segmentHeight; // Position of first wood pice

        // Checking collision if player is at the same height as a wood piece
        if (playerY >= firstBranchY && playerY < firstBranchY + segmentHeight) {
            String branch = branches.get(branches.size() - 1); // First branch
            if ("left".equals(branch) && playerX < treeX) { // Branch on the left
                return true;
            }
            if ("right".equals(branch) && playerX + playerWidth > treeX + treeWidth) { // Branch on the right
                return true;
            }
        }
        return false;
    }
}