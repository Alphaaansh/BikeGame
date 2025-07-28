package gamerush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

    private final int WIDTH = 400;
    private final int HEIGHT = 600;

    private Thread gameThread;
    private PlayerBike bike;
    private List<Obstacle> obstacles = new ArrayList<>();
    private int score = 0;
    private boolean isGameOver = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isGameOver) {
                    bike.handleKeyPress(e.getKeyCode());
                }
            }
        });

        bike = new PlayerBike(WIDTH, HEIGHT);

        // Spawn obstacles every 1.5 seconds
        Timer spawnTimer = new Timer(1500, e -> {
            if (!isGameOver) {
                obstacles.add(new Obstacle(WIDTH));
            }
        });
        spawnTimer.start();

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            if (!isGameOver) {
                bike.update();

                Iterator<Obstacle> it = obstacles.iterator();
                while (it.hasNext()) {
                    Obstacle ob = it.next();
                    ob.update();

                    if (ob.isOutOfScreen(HEIGHT)) {
                        it.remove();
                        score += 10;
                    } else if (ob.getBounds().intersects(new Rectangle(bike.getX(), bike.getY(), 64, 128))) {
                        isGameOver = true;
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(this, "💥 Game Over!\nScore: " + score);
                            System.exit(0);
                        });
                    }
                }
            }

            repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLanes(g);
        bike.draw(g);

        for (Obstacle ob : obstacles) {
            ob.draw(g);
        }

        // Draw Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);
    }

    private void drawLanes(Graphics g) {
        g.setColor(Color.GRAY);
        int laneWidth = WIDTH / 3;

        for (int i = 1; i < 3; i++) {
            int x = i * laneWidth;
            g.drawLine(x, 0, x, HEIGHT);
        }
    }
}