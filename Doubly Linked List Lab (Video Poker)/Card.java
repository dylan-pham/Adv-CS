import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;
 
public class Card {
    private int value;
    private String name;
    private String suit;
    private Image suitImage;

    public Card(int value, String name, String suit) {
        this.value = value;
        this.name = name;
        this.suit = suit;

        if (suit.equals("diamonds")) {
            try {
                suitImage = ImageIO.read(new File("images/diamonds.png"));
                suitImage = suitImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            } catch ( IOException e ){
                System.out.println(e);
            }
        } else if (suit.equals("hearts")) {
            try {
                suitImage = ImageIO.read(new File("images/hearts.png"));
                suitImage = suitImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            } catch ( IOException e ){
                System.out.println(e);
            }
        } else if (suit.equals("spades")) {
            try {
                suitImage = ImageIO.read(new File("images/spades.png"));
                suitImage = suitImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            } catch ( IOException e ){
                System.out.println(e);
            }
        } else if (suit.equals("clubs")) {
            try {
                suitImage = ImageIO.read(new File("images/clubs.png"));
                suitImage = suitImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            } catch ( IOException e ){
                System.out.println(e);
            }
        }
    }

    public void drawMe(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 120, 150);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 120, 150);

        Font font = new Font("Calibri", Font.PLAIN, 50);
        g.setFont(font);

        if (suit.equals("diamonds")) {
            g.setColor(Color.RED);
        } else if (suit.equals("hearts")) {
            g.setColor(Color.RED);
        } else if (suit.equals("spades")) {
            g.setColor(Color.BLACK);
        } else if (suit.equals("clubs")) {
            g.setColor(Color.BLACK);
        }

        g.drawString(name, x+30, y+100);

        g.drawImage(suitImage, x+2, y, null);
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getSuit() {
        return suit;
    }

    public boolean equals(Card c){
        return name.equals(c.getName()) && suit.equals(c.getSuit());
    }

    public String toString() {
        return name + " | " + suit;
    }

    public boolean isNextCard(Card c) {
        if (name.equals("1") && c.getName().equals("2")) {
            return true;
        } else if (name.equals("2") && c.getName().equals("3")) {
            return true;
        } else if (name.equals("3") && c.getName().equals("4")) {
            return true;
        } else if (name.equals("4") && c.getName().equals("5")) {
            return true;
        } else if (name.equals("5") && c.getName().equals("6")) {
            return true;
        } else if (name.equals("6") && c.getName().equals("7")) {
            return true;
        } else if (name.equals("7") && c.getName().equals("8")) {
            return true;
        } else if (name.equals("8") && c.getName().equals("9")) {
            return true;
        } else if (name.equals("9") && c.getName().equals("10")) {
            return true;
        } else if (name.equals("10") && c.getName().equals("J")) {
            return true;
        } else if (name.equals("J") && c.getName().equals("Q")) {
            return true;
        } else if (name.equals("Q") && c.getName().equals("K")) {
            return true;
        } else if (name.equals("K") && c.getName().equals("A")) {
            return true;
        }

        return false;
    }
}