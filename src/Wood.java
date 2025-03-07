import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wood {
    private int treeX, treeY; // Tree position
    private int segmentHeight = 50; // Height of one tree segment
    private int treeWidth = 50; // Width of the tree
    private List<String> branches; // Branches List: "left", "right" or ""
    private Random random = new Random();

    public Wood(int treeX, int panelHeight, int segments) {
        this.treeX = treeX;
        this.treeY = panelHeight - segments * segmentHeight; // Start tree at the bottom
        this.branches = new ArrayList<>();

        // Generate initial segments
        for (int i = 0; i < segments; i++) {
            if (i == segments - 1) {
                branches.add(""); // Ensure the bottom segment has no branch
            } else {
                branches.add(generateBranchSide());
            }
        }
    }

    // Tree and branches drawing
    public void draw(Graphics g) {
        g.setColor(new Color(139, 69, 19)); // Brown for the tree trunk

        for (int i = 0; i < branches.size(); i++) {
            int yPosition = treeY + i * segmentHeight; // Calculate Y position for the segment

            // Draw tree trunk
            g.fillRect(treeX, yPosition, treeWidth, segmentHeight);

            // Draw branches
            g.setColor(Color.GREEN);
            if ("left".equals(branches.get(i))) {
                g.fillRect(treeX - 30, yPosition + 15, 30, 10); // Left branch
            } else if ("right".equals(branches.get(i))) {
                g.fillRect(treeX + treeWidth, yPosition + 15, 30, 10); // Right branch
            }
            g.setColor(new Color(139, 69, 19)); // Reset color to brown
        }

        // 🌿 Draw overlapping leaves at the top 🌿
        g.setColor(new Color(34, 139, 34)); // Dark green for leaves
        int leavesWidth = treeWidth * 4; // Make leaves much wider than the trunk
        int leavesHeight = segmentHeight * 3; // Cover top 3 segments
        int leavesX = treeX - (leavesWidth - treeWidth) / 2; // Center leaves over trunk
        int leavesY = treeY - segmentHeight * 2; // Lower the leaves to overlap top 3 segments

        g.fillOval(leavesX, leavesY, leavesWidth, leavesHeight); // Draw overlapping leaves
    }

    // Generating random branch side
    private String generateBranchSide() {
        int side = random.nextInt(3); // 0 = no branches at all, 1 = branch on left, 2 = branch on right
        switch (side) {
            case 1:
                return "left";
            case 2:
                return "right";
            default:
                return "";
        }
    }

    // Cutting down the tree
    public void chop() {
        if (!branches.isEmpty()) {
            branches.remove(branches.size() - 1); // Remove the cut piece of wood
            branches.add(0, generateBranchSide()); // Add new tree segment
        }
    }

    // Checking collision with player
    public boolean checkCollision(int playerX, int playerY, boolean playerFacingRight, int playerHeight) {
        int lastBranchY = treeY + (branches.size() - 1) * segmentHeight; // Bottom segment

        // Check if player is at the same height as the bottom segment
        if (playerY + playerHeight > lastBranchY && playerY < lastBranchY + segmentHeight) {
            String branch = branches.get(branches.size() - 1); // Bottom branch
            if ("left".equals(branch) && !playerFacingRight) { // Left branch, player facing left
                return true;
            }
            if ("right".equals(branch) && playerFacingRight) { // Right branch, player facing right
                return true;
            }
        }
        return false;
    }

    // Getters
    public int getTreeY() {
        return treeY;
    }

    public int getTreeX() {
        return treeX;
    }

    public int getTreeWidth() {
        return treeWidth;
    }
}