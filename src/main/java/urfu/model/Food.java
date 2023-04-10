package urfu.model;

public class Food
{
    private volatile int x;
    private volatile int y;

    double max = 600;
    double min = 0;
    private static int counter = 1;
    public Food()
    {
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

    public static int count()
    {
        return counter++;
    }
}

