import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {
        int portNumber = 1024;
		ServerSocket serverSocket = new ServerSocket(portNumber);
		GameManager gm = new GameManager();
		
		while(true){
			System.out.println("Waiting for a connection");
			Socket clientSocket = serverSocket.accept();
			
			ServerThread st = new ServerThread(clientSocket,gm);
			gm.addThread(st);
			Thread thread = new Thread(st);
			
			thread.start();
		}
    }
}
