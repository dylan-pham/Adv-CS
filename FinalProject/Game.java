import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;

public class Game implements Serializable {
    private HashMap<Coordinate, String> gameItems;
    private ArrayList<String> collectedItems;
  
    public Game() {
        gameItems = new HashMap<Coordinate, String>();
        gameItems.put(new Coordinate(5, 5), "ball");
        gameItems.put(new Coordinate(7, 7), "hat");
        collectedItems = new ArrayList<String>();
    }

    public HashMap<Coordinate, String> getGameItems() {
        return gameItems;
    }

    public void destroyItem(Coordinate c, String itemName) { // for items already collected
        gameItems.put(c, null);
        collectedItems.add(itemName);
    }

    public ArrayList<String> getCollectedItems() {
        return collectedItems;
    }

    // public String checkCoordinate(Coordinate c) {
    //     if (gameItems.get(c) == null) {
    //         return "blank";
    //     }

    //     return gameItems.get(c);
    // }
}