package Game_Package;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;


public class Client {

	private static final int MAX_TRY = 3;
	
	public static void attendi(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		//configurazioni GUI
		JFrame frame = new JFrame("Schermata di Attesa");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		
		
		// Configura l'indirizzo IP e la porta del server
		String serverAddress = "127.0.0.1"; // IP del server (localhost)
		int port = 12345; // Porta del server

		Socket link = null;
		int i = 0;
		
		// Messaggio di attesa
		JLabel label = new JLabel("Caricamento in corso, attendere prego...",
		SwingConstants.CENTER);
		frame.add(label, BorderLayout.NORTH);
		// Barra di progresso
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true); // Modalità indeterminata
		frame.add(progressBar, BorderLayout.CENTER);
		// Mostra la finestra
		frame.setVisible(true);
		
		while( link == null && i < MAX_TRY ) {
			try {
				// Crea una connessione al server
				link = new Socket(serverAddress, port);
			}catch(IOException ex) {
				ex.printStackTrace();
				attendi((long) (1000*Math.pow(2, i)));
			}
			i++;
		}
		
		if( link == null ) {
			frame.dispose();
			JOptionPane.showMessageDialog(null, "Impossibile collegarsi al server");
			return;
		}else {
			// Chiude la schermata di attesa
			frame.dispose();
			JOptionPane.showMessageDialog(null, "Operazione completata!");

		}
		
		try {

				// Ottiene l'output stream per inviare dati
				OutputStream out = link.getOutputStream();

				// Ottiene l'output stream per ricevere dati
				InputStream in = link.getInputStream();

				// Usa un PrintWriter per inviare RIGHE di testo al server
				PrintWriter writer = new PrintWriter(out, true);
				// Usa un BufferReader per leggere RIGHE di testo al server
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				for (int j = 0; j < 5; j++) {
					long send = System.currentTimeMillis();
					writer.println("Hello");				
					System.out.println("Messaggio inviato al server: Hello");
					
					
					System.out.println(reader.readLine());
					long receive = System.currentTimeMillis();
					
					System.out.println("Ricevuta risposta dopo:"+(receive-send)+"ms");				
					attendi((long) (250*Math.pow(2, j)));
				}
				
				//Inviamo linea vuota per indicare la fine di comunicazione
				writer.println();
				
		} catch (Exception e) {
			// Gestisce eventuali errori
			System.err.println("Errore: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
