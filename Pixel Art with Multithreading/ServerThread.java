import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ServerThread implements Runnable{
	private Socket clientSocket;
	private ObjectOutputStream out;
	private GameManager gm;
	private int myID;

	public ServerThread(Socket clientSocket, GameManager gm){
		this.clientSocket = clientSocket;
		this.gm = gm;
		myID = -1;
	}
	
	public void sendGameData(GameData gameData){
		if (out != null) {
			try {
				System.out.print("Sending GameData to player: " + myID );
				out.reset();
				out.writeObject(gameData);
			} catch (IOException ex){
				System.out.println(ex.getMessage());
			}
			
		}
	}
	
	public int getMyID(){
		return myID;
	}
	
	@SuppressWarnings("unchecked")
	public void run(){
		System.out.println(Thread.currentThread().getName() + ": connection opened.");
		
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			
			String message = (String) in.readObject();
			System.out.println(message + ": " +Thread.currentThread().getName() + " Connection Successful. " );
			
			//add new player
			myID = gm.addNewPlayer();
			out.writeObject(myID);
			
			//send gameData to all clients
			gm.broadCastGameData();
			
			while(true) {
				System.out.println(" Waiting for gameData...");
				GameData gameData = (GameData) in.readObject();
				gm.setGameData(gameData);
				gm.broadCastGameData();
			}
		} catch (IOException ex){
			System.out.println("Connection closed");
			
			gm.removePlayer(myID);
			gm.broadCastGameData();	
			
            System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
	}
}
