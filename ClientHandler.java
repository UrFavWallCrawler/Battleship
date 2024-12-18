package Game_Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler implements Runnable {

	Socket link;
	
	public ClientHandler(Socket s) {
		this.link = s; 
	}
	
	public void cleanup() {
		try {
			link.close();
		} catch (IOException e) {
		}
		counter.dec();
	}
	
	@Override
	public void run() {
		BufferedReader reader = null; 
		PrintWriter writer = null;
		String message = null;
		try {
			reader = new BufferedReader(new InputStreamReader(link.getInputStream()));
			writer = new PrintWriter(link.getOutputStream(), true);
		}catch(IOException ex) {
			ex.printStackTrace();
			System.out.println("Client died too early, cleaning up the mess!");
			cleanup();
			return;
		}
		
		while("".equalsIgnoreCase(message) == false) {
            try {
				message = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
            // Legge il messaggio dal client
            System.out.println("Messaggio ricevuto dal client: " + message);

            //Verifico se il messaggio è valido e rispondo solo in quel caso
            if ("Hello".equalsIgnoreCase(message)) {
                // Calcola un tempo casuale basato sul numero di client attivi
                int delay = counter.get() * CLIENT_DELAY; // Millisecondi
                System.out.println("Tempo di attesa per rispondere: " + delay + " ms");

                // Attende per il tempo calcolato
                attendi(delay);

                // Invia la risposta al client
                writer.println("World");
                System.out.println("Risposta inviata al client: World");
            } else {
            	//Tentativo di hacking - chiudo la connessione
            	writer.println("You are trying to hack into private system");
            	writer.println("All your data are logged and they will sent to the authority");
            	writer.println("for investigation.");
            	
            	writer.println("You are:"+link.getRemoteSocketAddress());
            }
		}
    	try {
			reader.close();
        	writer.close();
		} catch (IOException e) {
		}
    	cleanup();
    	
	}
}