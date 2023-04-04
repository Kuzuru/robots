package urfu.viewmodel;

import urfu.model.GameModel;
import urfu.view.GameView;
import urfu.view.GameWindow;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameViewModel extends JPanel
{
    private final GameModel game_model;
    private final GameWindow game_window;

    private final java.util.Timer m_timer = initTimer();

    private static java.util.Timer initTimer()
    {
        return new Timer("events generator", true);
    }

    public GameViewModel(GameModel gameModel, GameWindow gameWindow) {
        this.game_model = gameModel;
        this.game_window = gameWindow;

        // Set the initial dimensions for the game model
        game_model.setDimension(game_window.getSize());

        initListeners();
    }


    private void initListeners()
    {
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                game_model.setDimension(game_window.getSize());
                getGameView().updateView();
            }
        }, 0, 50);

        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                game_model.updateModel();
            }
        }, 0, 20);

        game_window.getGameView().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                game_model.setTargetPosition(e.getPoint());
                getGameView().repaint();
            }
        });

        game_window.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(final ComponentEvent e)
            {
                super.componentResized(e);

                System.out.println("resize");
                game_model.setDimension(game_window.getSize());
                System.out.println(game_model.getDimension());
            }
        });
    }

    public GameView getGameView()
    {
        return game_window.getGameView();
    }

    public GameWindow getGameWindow()
    {
        return game_window;
    }
}
