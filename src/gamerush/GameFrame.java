package gamerush;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Lane Rush - Bike Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new GamePanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
