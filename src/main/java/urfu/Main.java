package urfu;

import urfu.model.GameModel;
import urfu.view.GameView;
import urfu.view.GameWindow;
import urfu.viewmodel.GameViewModel;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {

        GameModel gameModel = new GameModel();
        GameView gameView = new GameView(gameModel);
        GameWindow gameWindow = new GameWindow(gameView);

        GameViewModel viewModel = new GameViewModel(gameModel, gameWindow);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() ->
        {
            MainApplicationFrame frame = new MainApplicationFrame(viewModel);
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }
}
