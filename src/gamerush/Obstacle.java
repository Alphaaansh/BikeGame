package gamerush;

import java.awt.*;
import java.util.Random;

public class Obstacle {

    private int x, y;
    private final int width = 64;
    private final int height = 128;

    private final int laneLeftX;
    private final int laneCenterX;
    private final int laneRightX;

    public Obstacle(int panelWidth) {
        int laneWidth = panelWidth / 3;
        this.laneLeftX = laneWidth / 2 - 32;
        this.laneCenterX = laneWidth + laneWidth / 2 - 32;
        this.laneRightX = 2 * laneWidth + laneWidth / 2 - 32;

        // Choose random lane
        int lane = new Random().nextInt(3);
        this.x = switch (lane) {
            case 0 -> laneLeftX;
            case 1 -> laneCenterX;
            case 2 -> laneRightX;
            default -> laneCenterX;
        };

        this.y = -height; // Start above screen
    }

    public void update() {
        y += 10; // falling speed
    }

    public boolean isOutOfScreen(int panelHeight) {
        return y > panelHeight;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
