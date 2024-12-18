package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
public class ClientHandler {
	public void run() {
		Socket socket= new Socket();
		BufferedReader in;
		PrintWriter out;
	       try {
	           in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	           out = new PrintWriter(socket.getOutputStream(), true);
	           out.println("Benvenuto alla Battaglia Navale! Inserisci le coordinate (es. 4,5)");
	           String inputLine;
	           while ((inputLine = in.readLine()) != null) {
	               if (inputLine.equalsIgnoreCase("quit")) {
	                   out.println("Connessione terminata.");
	                   break;
	               }
	               // Parsing delle coordinate
	               String[] coord = inputLine.split(",");
	               int x = Integer.parseInt(coord[0]);
	               int y = Integer.parseInt(coord[1]);
	               // Risposta in base al contenuto della griglia
	               if (griglia[x][y] == ALIVE) {
	                   griglia[x][y] = HIT; // Segna come colpito
	                   out.println("Colpito!");
	               } else {
	                   out.println("Mancato!");
	               }
	           }
	       } catch (IOException | NumberFormatException e) {
	           e.printStackTrace();
	       } finally {
	           try {
	               socket.close();
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	       }
	   }
	}
}
