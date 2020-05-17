import java.util.ArrayList;
import java.awt.Color;

public class GameManager implements Runnable{
	private GameData gameData;
	private ArrayList<ServerThread> serverThreads;
	private int id = 0;
	
	public GameManager(){
		serverThreads = new ArrayList<ServerThread>();
		gameData = new GameData();
	}
	
	public void addThread(ServerThread st){
		serverThreads.add(st);
	}

	public synchronized void broadCastGameData(){
		for(int i=0; i<serverThreads.size(); i++){
			serverThreads.get(i).sendGameData(gameData);
		}
	}
	
	public void setGameData(GameData gameData){
		this.gameData = gameData;
	}
	
	public void remove(ServerThread st){
		serverThreads.remove(st);
	}
	
	public int addNewPlayer(){
		id++;
			
		gameData.addNewPlayer(new Player(id));
		
		return id;
	}
	
	public void run(){
		while(true) {
			try {
				Thread.sleep(1000); //millisecond
			} catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public void removePlayer(int id){
		//remove player
		gameData.removePlayer(id);
		
		//remove serverThreads that ended
		for(int i=0; i<serverThreads.size(); i++){
			if( serverThreads.get(i).getMyID() == id ){
				serverThreads.remove(i);
				i--;
			}
		}
	}
	
	
	
	
	
}
