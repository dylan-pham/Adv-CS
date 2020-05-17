import java.io.Serializable;

public class Player implements Serializable{
	private int id;

	
	public Player(int id){	
		this.id = id;
	}
	
	public int getID(){
		return id;
	}

	public String toString(){
		return "testing";
	}
	
}
