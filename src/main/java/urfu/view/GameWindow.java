package urfu.view;

import urfu.model.Robot;
import urfu.model.GameTimer;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import javax.swing.JInternalFrame;

public class GameWindow extends JInternalFrame {
    private final GameView game_view;

    public GameWindow(GameView game_view) {
        super("Игровое поле", true, true, true, true);
        this.game_view = game_view;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(game_view, BorderLayout.CENTER);

        JLabel label = new JLabel();
        GameTimer gameTimer = new GameTimer(label, 20);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(gameTimer, 0, 1000);
        Robot.setGameTimer(gameTimer);

        add(label, BorderLayout.NORTH);
        getContentPane().add(panel);
        pack();
    }

    public GameView getGameView() { return this.game_view; }
}