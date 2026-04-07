package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole {
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		// scannerDiLinee.close(); <-- Il prof dice di omettere questa riga per ora, quindi la lasciamo commentata
		return riga;
	}

}
