import java.io.Serializable;

public class Coordinate implements Serializable {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x; 
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public boolean equals(Coordinate c) {
        return x == c.getX() && y == c.getY();
    }
}