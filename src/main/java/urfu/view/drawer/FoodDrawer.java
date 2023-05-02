package urfu.view.drawer;

import urfu.model.Food;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class FoodDrawer extends Drawer
{
    public void drawFood(Graphics2D g, Food food)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);

        g.setColor(Color.RED);
        fillOval(g, food.getX(), food.getY(), 10, 10);

        g.setColor(Color.BLACK);
        drawOval(g, food.getX(), food.getY(), 10, 10);

    }

}
