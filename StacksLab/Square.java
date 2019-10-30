import java.awt.Graphics;
import java.awt.Color;
 
public class Square {
    private int red;
    private int green;
    private int blue;

    public Square(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void drawMe(Graphics g, int x, int y) {
        g.setColor(new Color(red, green, blue));
        g.fillRect(x, y, 20, 20);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, 20, 20);
    }

    public void changeColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }
    
    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}