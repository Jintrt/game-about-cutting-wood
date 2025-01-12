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
            int yPosition = treeY + i * segmentHeight; // Positon Y of a segment

            // Tree segment drawing
            g.setColor(new Color(139, 69, 19)); // Tree color(Brown)
            g.fillRect(treeX, yPosition, treeWidth, segmentHeight);

            // Branch drawing
            g.setColor(new Color(34, 139, 34)); // Branch color(Green)
            if ("left".equals(branches.get(i))) {
                g.fillRect(treeX - 30, yPosition + 15, 30, 10); // Branch on a left
            } else if ("right".equals(branches.get(i))) {
                g.fillRect(treeX + treeWidth, yPosition + 15, 30, 10); // Branch on a right
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
    public boolean checkCollision(int playerX, int playerY, boolean playerFacingRight, int playerHeight) {
        int firstBranchY = treeY; // Branch on the highest segment

        // If player is at the same height as a branch we are checking collision
        if (playerY + playerHeight > firstBranchY && playerY < firstBranchY + segmentHeight) {
            String branch = branches.get(0);
            if ("left".equals(branch) && !playerFacingRight) { // Branch on the left, player looking left
                return true;
            }
            if ("right".equals(branch) && playerFacingRight) { // Branch on the right, player looking right
                return true;
            }
        }
        return false;
    }

    // Getters
    public int getTreeX() {
        return treeX;
    }

    public int getTreeWidth() {
        return treeWidth;
    }

}