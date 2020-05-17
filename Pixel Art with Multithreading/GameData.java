import java.util.ArrayList;
import java.io.Serializable;
import java.util.Stack;

public class GameData implements Serializable {
    private Stack<Pair<Pair<Integer, Integer>, RGB>> undoneStates; // keeping track of undone actions for redo
    private Stack<Pair<Pair<Integer, Integer>, RGB>> previousStates; // keeping track of previous actions for undo
	private Square[][] grid;
	
	public GameData(){	
		undoneStates = new Stack<Pair<Pair<Integer, Integer>, RGB>>(); 
		previousStates = new Stack<Pair<Pair<Integer, Integer>, RGB>>(); 
		grid = new Square[16][16];

		for (int r = 0; r < grid.length; r++) {
        	for (int c = 0; c < grid[r].length; c++) {
            	grid[r][c] = new Square(255, 255, 255);
            }
        }
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
