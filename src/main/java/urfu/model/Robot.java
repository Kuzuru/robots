package urfu.model;

public class Robot {
    private double posX = 100;
    private double posY = 100;

    public static final double maxVelocity = 0.05;
    public static final double maxAngularVelocity = 0.005;

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
}
