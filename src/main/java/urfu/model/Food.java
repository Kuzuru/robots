package urfu.model;

public class Food
{
    private static GameTimer gameTimer;

    public static void setGameTimer(GameTimer timer) {
        gameTimer = timer;
    }
    private volatile int x;
    private volatile int y;

    double max = 500;
    double min = 100;

    private static int counter = 0;
    public Food() {
        this.x = (int) ((Math.random() * ((max - min) + 1)) + min);
        this.y = (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    public void setPositionX()
    {
        this.x = (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    public void setPositionY()
    {
        this.y = (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public static int count() {
        counter++;
        gameTimer.increaseTimeLeft(5);
        return counter;
    }
    public static int countValue() {return counter; }
}



