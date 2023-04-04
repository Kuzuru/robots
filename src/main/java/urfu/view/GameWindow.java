package urfu.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JInternalFrame
{
    private final GameView game_view;

    public GameWindow(GameView game_view)
    {
        super("Игровое поле", true, true, true, true);
        this.game_view = game_view;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(game_view, BorderLayout.CENTER);

        getContentPane().add(panel);
        pack();
    }

    public GameView getGameView() {
        return this.game_view;
    }
}