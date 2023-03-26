package urfu.viewmodel;

import urfu.model.GameModel;
import urfu.view.GameView;
import urfu.view.GameWindow;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameViewModel extends JPanel
{
    private final GameModel game_model;
    private final GameWindow game_window;

    public GameViewModel(GameModel game_model, GameWindow game_window)
    {
        this.game_model = game_model;
        this.game_window = game_window;

        Timer m_timer = initTimer();

        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                GameViewModel.this.game_model.setDimension(GameViewModel.this.game_window.getSize());
                getGameView().updateView();
            }
        }, 0, 50);

        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                GameViewModel.this.game_model.setDimension(GameViewModel.this.game_window.getSize());
                getGameView().updateView();
            }
        }, 0, 10);

        this.game_window.getGameView().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                GameViewModel.this.game_model.setTargetPosition(e.getPoint());
                getGameView().repaint();
            }
        });

        setDoubleBuffered(true);
    }

    private static Timer initTimer()
    {
        return new Timer("events_generator", true);
    }

    public GameView getGameView()
    {
        return game_window.getGameView();
    }
}
