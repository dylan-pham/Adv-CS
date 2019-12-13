import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;

public class Game implements Serializable {
    private HashMap<Coordinate, String> placedGameItems; // includes obstacles
    private ArrayList<String> collectedItems1;
    private ArrayList<String> collectedItems2;
    private Coordinate player1;
    private Coordinate player2;
    private ArrayList<Coordinate> filledCoordinates;
    private ArrayList<String> gameItems;
  
    public Game() {
        placedGameItems = new HashMap<Coordinate, String>();
        collectedItems1= new ArrayList<String>();
        collectedItems2 = new ArrayList<String>();
        player1 = new Coordinate(0, 0);
        player2 = new Coordinate(0, 0);
        filledCoordinates = new ArrayList<Coordinate>();
        gameItems = new ArrayList<String>();
        gameItems.add("avocado");
        gameItems.add("ball");
        gameItems.add("banana");
        gameItems.add("book");
        gameItems.add("coin");
        gameItems.add("fries");
        gameItems.add("guitar");
        gameItems.add("hat");
        gameItems.add("pokemon");
        gameItems.add("socks");
        gameItems.add("yeezys");
        // gameItems.add("fire0");
        // gameItems.add("fire1");
        // gameItems.add("fire2");
        // gameItems.add("fire3");
        // gameItems.add("fire4");
        // gameItems.add("fire5");
        // gameItems.add("fire6");
        // gameItems.add("fire7");
        // gameItems.add("fire8");
        // gameItems.add("fire9");

        for (String each : gameItems) {
            placedGameItems.put(getOpenCoordinate(), each);
        }
    }

    // for non-overlapping game items
    public Coordinate getOpenCoordinate() {
        int x = (int)(Math.random() * 7 + 1);
        int y = (int)(Math.random() * 7 + 1);
        Coordinate temp = new Coordinate(x, y);

        for (int i = 0; i < filledCoordinates.size(); i++) {
            if (filledCoordinates.get(i).equals(temp)) {
                x = (int)(Math.random() * 7 + 1);
                y = (int)(Math.random() * 7 + 1);
                temp = new Coordinate(x, y);
                i = 0;
            }
        }

        filledCoordinates.add(temp);

        return temp;
    }

    public HashMap<Coordinate, String> getPlacedGameItems() {
        return placedGameItems;
    }
    
    // getting rid of items that have already collected by either player
    public void destroyItem(Coordinate c) {
        placedGameItems.put(c, null);
    }

    public void addToCollectedItems1(String itemName) {
        collectedItems1.add(itemName);
    }

    public void addToCollectedItems2(String itemName) {
        collectedItems2.add(itemName);
    }

    public ArrayList<String> getCollectedItems1() {
        return collectedItems1;
    }

    public ArrayList<String> getCollectedItems2() {
        return collectedItems2;
    }

    public Coordinate getPlayer1Coordinate() {
        return player1;
    }

    public Coordinate getPlayer2Coordinate() {
        return player2;
    }

    public void setPlayer1Coordinate(Coordinate player1) {
        this.player1 = player1;
    }

    public void setPlayer2Coordinate(Coordinate player2) {
        this.player2 = player2;
    }

    public void checkEndGame() {
        if (collectedItems1.size() > 5 || collectedItems2.size() > 5) {

        }
    }
}