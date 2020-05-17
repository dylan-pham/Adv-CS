import java.util.ArrayList;
import java.io.Serializable;
import java.util.Stack;

public class GameData implements Serializable {
	private ArrayList<Player> playerList;
    private Stack<Pair<Pair<Integer, Integer>, RGB>> undoneStates; // keeping track of undone actions for redo
    private Stack<Pair<Pair<Integer, Integer>, RGB>> previousStates; // keeping track of previous actions for undo
	private Square[][] grid;
	
	public GameData(){	
		playerList = new ArrayList<Player>();
		undoneStates = new Stack<Pair<Pair<Integer, Integer>, RGB>>(); 
		previousStates = new Stack<Pair<Pair<Integer, Integer>, RGB>>(); 
		grid = new Square[16][16];

		for (int r = 0; r < grid.length; r++) {
        	for (int c = 0; c < grid[r].length; c++) {
            	grid[r][c] = new Square(255, 255, 255);
            }
        }
	}
	
	public void addNewPlayer(Player p){
		playerList.add(p);
	}
	
	public void removePlayer(int id){
		for(int i=0; i<playerList.size(); i++){
			if( id == playerList.get(i).getID() ){
				playerList.remove(i);
				i--;
			}
		}
	}
	
	public ArrayList<Player> getPlayerList(){
		return playerList;
	}

	public Stack<Pair<Pair<Integer, Integer>, RGB>> getUndoneStates() {
		return undoneStates;
	}

	public Stack<Pair<Pair<Integer, Integer>, RGB>> getPreviousStates() {
		return previousStates;
	}

	public Square[][] getGrid() {
		return grid;
	}
}
