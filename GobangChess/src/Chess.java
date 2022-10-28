import java.awt.*;

/**
 * @author jiuzhe
 * @create 2022-10-28-21:07
 * chess class
 */
public class Chess {
    //creat coordinate
    private int x;
    private int y;
    //color
    private Color color;
    //size
    public static final int DIAMETER=30;

    //constructor
    public Chess(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    //get method
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
