package gamerush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerBike {

    private int x;
    private int y;
    private final int laneLeftX;
    private final int laneCenterX;
    private final int laneRightX;

    private Image bikeImage;

    public PlayerBike(int panelWidth, int panelHeight) {
        int laneWidth = panelWidth / 3;
        this.laneLeftX = laneWidth / 2 - 32;
        this.laneCenterX = laneWidth + laneWidth / 2 - 32;
        this.laneRightX = 2 * laneWidth + laneWidth / 2 - 32;

        this.x = laneCenterX; // start in center
        this.y = panelHeight - 150;

        // Load image from your full path (double backslashes)
        bikeImage = new ImageIcon("C:\\Users\\Ansh kumar jha\\Downloads\\bike.png").getImage();

        if (bikeImage == null) {
            System.out.println("❌ Failed to load bike image.");
        } else {
            System.out.println("✅ Bike image loaded successfully.");
        }
    }

    public void update() {
        // For future use: animation, physics, etc.
    }

    public void draw(Graphics g) {
        g.drawImage(bikeImage, x, y, 64, 128, null);
    }

    public void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            if (x == laneCenterX) {
                x = laneLeftX;
            } else if (x == laneRightX) {
                x = laneCenterX;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (x == laneCenterX) {
                x = laneRightX;
            } else if (x == laneLeftX) {
                x = laneCenterX;
            }
        }
    }

    // Getters for collision detection
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
