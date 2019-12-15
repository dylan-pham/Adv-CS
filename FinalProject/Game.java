import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;

public class Game implements Serializable {
    private HashMap<String, String> placedGameItems; // includes obstacles
    private ArrayList<String> collectedItems1;
    private ArrayList<String> collectedItems2;
    private String player1Loc;
    private String player2Loc;
    private ArrayList<String> filledCoordinates;
    private ArrayList<String> gameItems;
    private boolean endGame;
    private String winner;
  
    public Game() {
        placedGameItems = new HashMap<String, String>();
        collectedItems1= new ArrayList<String>();
        collectedItems2 = new ArrayList<String>();
        player1Loc = "0 0";
        player2Loc = "0 0";

        filledCoordinates = new ArrayList<String>();
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
        gameItems.add("fire0");
        gameItems.add("fire1");
        gameItems.add("fire2");
        gameItems.add("fire3");
        gameItems.add("fire4");
        gameItems.add("fire5");
        gameItems.add("fire6");
        gameItems.add("fire7");
        gameItems.add("fire8");
        gameItems.add("fire9");

        for (String each : gameItems) {
            placedGameItems.put(getOpenCoordinate(), each);
        }

        endGame = false;
        winner = "";
    }

    // for non-overlapping game items
    public String getOpenCoordinate() {
        int x = (int)(Math.random() * 7 + 1);
        int y = (int)(Math.random() * 7 + 1);
        String temp = x + " " + y;

        for (int i = 0; i < filledCoordinates.size(); i++) {
            if (filledCoordinates.get(i).equals(temp)) {
                x = (int)(Math.random() * 7 + 1);
                y = (int)(Math.random() * 7 + 1);
                temp = x + " " + y;
                i = 0;
            }
        }

        filledCoordinates.add(temp);

        return temp;
    }

    public HashMap<String, String> getPlacedGameItems() {
        return placedGameItems;
    }
    
    // getting rid of items that have already collected by either player
    public void destroyItem(String c) {
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

    public String getPlayer1Coordinate() {
        return player1Loc;
    }

    public String getPlayer2Coordinate() {
        return player2Loc;
    }

    public void setPlayer1Coordinate(String player1) {
        this.player1Loc = player1;
    }

    public void setPlayer2Coordinate(String player2) {
        this.player2Loc = player2;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String checkWinner() {
        return winner;
    }

    public void placeObstacle() {
        placedGameItems.put(getOpenCoordinate(), "bomb");
    }
}