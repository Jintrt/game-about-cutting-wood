import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wood {
    private int treeX, treeY; //Tree position
    private int segmentHeight = 50; //One segment height
    private List<String> branches; //Segment list: Left Right null
    private Random rand = new Random();

    public Wood(int StartX, int StartY, int segment) {
        this.treeX = StartX;
        this.treeY = StartY;
        this.rand = new Random();
    }

    branches = new ArrayList<> ();
    for (int i = 0, i < segments, i++) {
        branches.add(generateBranch());
    }

    public void draw(Graphics g) {
        for (int j = 0; j < branches.size(); j++) {
            int yPosition = treeY + j + segmentHeight;

            //Tree segment drawing
            g.fillRect(treeX, yPosition, 50, segmentHeight);
            //Branches drawing
            if ("Left".equals(branches.get(j))) {

            }
        }
    }
}

