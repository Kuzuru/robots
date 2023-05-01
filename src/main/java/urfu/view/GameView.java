package urfu.view;

import urfu.model.GameModel;
import urfu.view.drawer.RobotDrawer;
import urfu.view.drawer.TargetDrawer;
import urfu.view.drawer.FoodDrawer;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel
{
    private final GameModel gameModel;

    RobotDrawer robotDrawer;
    TargetDrawer targetDrawer;
    FoodDrawer foodDrawer;

    public GameView(GameModel gameModel)
    {
        robotDrawer = new RobotDrawer();
        targetDrawer = new TargetDrawer();
        foodDrawer = new FoodDrawer();

        this.gameModel = gameModel;
    }

    public void updateView()
    {
        onRedrawEvent();
    }

    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        robotDrawer.drawRobot(g2d, gameModel.getRobot());
        targetDrawer.drawTarget(g2d, gameModel.getTarget());
        foodDrawer.drawFood(g2d, gameModel.getFood());
    }
}
