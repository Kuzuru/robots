package urfu.model;

public class Robot {
    private double posX = 100;
    private double posY = 100;

    public static final double maxVelocity = 0.05;
    public static final double maxAngularVelocity = 0.005;

    private double distanceToFood = 0.0;
    private double distanceToTarget = 0.0;

    private volatile double robotDirection = 0;

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getRobotDirection() {
        return robotDirection;
    }

    public void setRobotDirection(double robotDirection) {
        this.robotDirection = robotDirection;
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;

        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    public void updateDistances(Target target, Food food) {
        this.distanceToTarget = distance(target.getX(), target.getY(), getPosX(), getPosY());
        this.distanceToFood = distance(food.getX(), food.getY(), getPosX(), getPosY());
    }

    public boolean isCloseEnoughToFood(Food food) {
        return isCloseEnough(this.distanceToFood, 10.0);
    }

    public void ifCloseEnoughToFood(Food food) {
        if (isCloseEnoughToFood(food)) {
            food.setPositionX();
            food.setPositionY();
            Food.count();
        }
    }

    public boolean isCloseEnoughToTarget() {
        return isCloseEnough(this.distanceToTarget, 4.0);
    }

    private boolean isCloseEnough(double distance, double threshold) {
        return distance <= threshold;
    }
}






