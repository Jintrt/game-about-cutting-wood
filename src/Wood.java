import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wood {
    private int treeX, treeY; // X and Y position of the tree
    private int segmentHeight = 50; // Height of one tree segment
    private int treeWidth = 50; // Width of the tree trunk
    private List<String> branches; // Stores the branch positions ("left", "right", or "")
    private Random random = new Random(); // Random object for generating branch placements

    /**
     * Constructor for the Wood class.
     * Initializes the tree at a specific position and generates its segments.
     *
     * @param treeX X-coordinate of the tree
     * @param panelHeight Height of the game panel
     * @param segments Number of tree segments
     */
    public Wood(int treeX, int panelHeight, int segments) {
        this.treeX = treeX;
        this.treeY = panelHeight - segments * segmentHeight; // Position the tree so it starts at the bottom
        this.branches = new ArrayList<>();

        // Generate the initial tree segments with random branch placement
        for (int i = 0; i < segments; i++) {
            if (i == segments - 1) {
                branches.add(""); // Ensure the bottom segment has no branch
            } else {
                branches.add(generateBranchSide());
            }
        }
    }

    /**
     * Draws the tree, its branches, and leaves.
     *
     * @param g The Graphics object used for drawing
     */
    public void draw(Graphics g) {
        g.setColor(new Color(139, 69, 19)); // Set the trunk color (brown)

        for (int i = 0; i < branches.size(); i++) {
            int yPosition = treeY + i * segmentHeight; // Calculate Y position for each segment

            // Draw the tree trunk segment
            g.fillRect(treeX, yPosition, treeWidth, segmentHeight);

            // Draw branches
            g.setColor(Color.GREEN);
            if ("left".equals(branches.get(i))) {
                g.fillRect(treeX - 30, yPosition + 15, 30, 10); // Left branch
            } else if ("right".equals(branches.get(i))) {
                g.fillRect(treeX + treeWidth, yPosition + 15, 30, 10); // Right branch
            }
            g.setColor(new Color(139, 69, 19)); // Reset color to brown for the next segment
        }

        // 🌿 Draw overlapping leaves at the top 🌿
        g.setColor(new Color(34, 139, 34)); // Dark green for leaves
        int leavesWidth = treeWidth * 4; // Leaves are wider than the trunk
        int leavesHeight = segmentHeight * 3; // Cover the top 3 segments
        int leavesX = treeX - (leavesWidth - treeWidth) / 2; // Center the leaves over the trunk
        int leavesY = treeY - segmentHeight * 2; // Position the leaves to overlap the top segments

        g.fillOval(leavesX, leavesY, leavesWidth, leavesHeight); // Draw the leaf canopy
    }

    /**
     * Generates a random branch placement for a tree segment.
     *
     * @return "left", "right", or "" (no branch)
     */
    private String generateBranchSide() {
        int side = random.nextInt(3); // 0 = no branch, 1 = left, 2 = right
        switch (side) {
            case 1:
                return "left";
            case 2:
                return "right";
            default:
                return "";
        }
    }

    /**
     * Removes the lowest segment of the tree and adds a new one at the top.
     * Simulates the tree being chopped down.
     */
    public void chop() {
        if (!branches.isEmpty()) {
            branches.remove(branches.size() - 1); // Remove the bottom segment
            branches.add(0, generateBranchSide()); // Add a new segment at the top
        }
    }

    /**
     * Checks if the player collides with a branch at their position.
     *
     * @param playerX Player's X-coordinate
     * @param playerY Player's Y-coordinate
     * @param playerFacingRight Whether the player is facing right
     * @param playerHeight Player's height
     * @return true if the player is hit by a branch, false otherwise
     */
    public boolean checkCollision(int playerX, int playerY, boolean playerFacingRight, int playerHeight) {
        int lastBranchY = treeY + (branches.size() - 1) * segmentHeight; // Get the Y position of the lowest branch

        // Check if the player is at the same height as the lowest branch
        if (playerY + playerHeight > lastBranchY && playerY < lastBranchY + segmentHeight) {
            String branch = branches.get(branches.size() - 1); // Get the lowest branch
            if ("left".equals(branch) && !playerFacingRight) { // Left branch, player facing left
                return true;
            }
            if ("right".equals(branch) && playerFacingRight) { // Right branch, player facing right
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the Y-coordinate of the tree.
     *
     * @return The Y position of the tree
     */
    public int getTreeY() {
        return treeY;
    }

    /**
     * Gets the X-coordinate of the tree.
     *
     * @return The X position of the tree
     */
    public int getTreeX() {
        return treeX;
    }

    /**
     * Gets the width of the tree trunk.
     *
     * @return The width of the tree trunk
     */
    public int getTreeWidth() {
        return treeWidth;
    }
}