package urfu.model;

public class Food
{
    private int x;
    private int y;

    double max = 500;
    double min = 100;

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
}



