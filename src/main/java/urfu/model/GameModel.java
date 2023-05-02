package urfu.model;

import java.awt.*;

public class GameModel
{
    private final Robot robot;
    private final Target target;
    private final Food food;

    private Dimension dimension;

    public GameModel() {
        this.robot = new Robot();
        this.target = new Target();
        this.food = new Food();
    }

    public void setTargetPosition(Point p)
    {
        target.setX(p.x);
        target.setY(p.y);
    }

    protected Point getTargetPosition()
    {
        return new Point(target.getX(), target.getY());
    }

    public void setDimension(Dimension dimension)
    {
        this.dimension = dimension;
    }

    public Dimension getDimension()
    {
        return this.dimension;
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY)
    {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    private static double asNormalizedRadians(double angle)
    {
        while (angle < 0) {
            angle += 2 * Math.PI;
        }

        while (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }

        return angle;
    }

    private double normalizedPositionX(double x)
    {
        if (x < 0)
            return 0;

        if (x < dimension.width)
            return dimension.width;

        return x;
    }

    private double normalizedPositionY(double y)
    {
        if (y < 0)
            return 0;

        if (y < dimension.height)
            return dimension.height;

        return y;
    }

    private static double applyLimits(double value, double min, double max)
    {
        if (value < min)
            return min;

        return Math.min(value, max);
    }

    private void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = applyLimits(velocity, 0, Robot.maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -Robot.maxAngularVelocity, Robot.maxAngularVelocity);

        double newX = robot.getPosX() + velocity / angularVelocity *
                (Math.sin(robot.getRobotDirection() + angularVelocity * duration) -
                        Math.sin(robot.getRobotDirection()));

        if (!Double.isFinite(newX)) {
            newX = robot.getPosX() + velocity * duration * Math.cos(robot.getRobotDirection());
        }

        double newY = robot.getPosY() - velocity / angularVelocity *
                (Math.cos(robot.getRobotDirection() + angularVelocity * duration) -
                        Math.cos(robot.getRobotDirection()));

        if (!Double.isFinite(newY)) {
            newY = robot.getPosY() + velocity * duration * Math.sin(robot.getRobotDirection());
        }

        robot.setPosX(newX);
        robot.setPosY(newY);

        double newDirection = asNormalizedRadians(robot.getRobotDirection() + angularVelocity * duration);

        robot.setRobotDirection(newDirection);
    }

    public void updateModel()
    {
        robot.updateDistances(target, food);

        robot.ifCloseEnoughToFood(food);

        if (robot.isCloseEnoughToTarget()) { return; }

        double velocity = Robot.maxVelocity;
        double angleToTarget = angleTo(robot.getPosX(), robot.getPosY(), target.getX(), target.getY());
        double angularDifference = asNormalizedRadians(angleToTarget - robot.getRobotDirection());
        double angularVelocity;
        if (angularDifference > Math.PI) {
            angularDifference -= 2 * Math.PI;
        } else if (angularDifference < -Math.PI) {
            angularDifference += 2 * Math.PI;
        }
        // Scale the angular velocity based on the angular difference
        angularVelocity = Robot.maxAngularVelocity * (angularDifference / Math.PI);
        moveRobot(velocity, angularVelocity, 50);
    }

    public Robot getRobot()
    {
        return robot;
    }
    public Target getTarget()
    {
        return target;
    }
    public Food getFood()
    {
        return food;
    }
}


