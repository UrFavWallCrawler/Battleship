package Game_Package;
//various networking related imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
public class Main {

	public static void main(String[] args) {
		int port=31415;
		try {
			ServerSocket serverSocket= new ServerSocket(port);
			while(true) {
				Socket p1 = serverSocket.accept();
				System.out.println("player 1 connected");
				
				//create structure that represents player1
				Playerinfo infoP1 = new Playerinfo();
				
				ClientHandler handlerP1 = new ClientHandler(infoP1);
				//thread will tell client that they're waiting for
				//another player, and then call a wait() on infoP1,
				//so we'll need to wake them up when the game starts
				Thread threadP1 = new Thread(handlerP1);
				threadP1.start();
				
				Socket p2 = serverSocket.accept();
				System.out.println("player 2 connected");
				PlayerInfo infoP2 = new PlayerInfo();
				ClientHandler handlerP2 = new ClientHandler(infoP2);
				Thread threadP2 = new Thread(handlerP2);
				threadP2.start();
				
				System.out.println("starting game");
				//flip a coin to decide who has first turn
				if(Math.random()>0.5) {
					infoP1.setTurn(true);
				}else {
					infoP2.setTurn(true);
				}
				//give threads opponent's info
				//and wake them up, after which they can be left alone
				handlerP1.setOpponent(infoP2);
				handlerP2.setOppenent(infoP1);
				infoP1.notifyall();
				infoP2.notifyall();
			}
		}catch(Exception e) {
			System.err.println("Server error: "+ e.getMessage());
			e.printStackTrace();
			
		}
	}
	
}

