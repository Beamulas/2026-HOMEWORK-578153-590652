package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 */
public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma attenzione!\n"+
			"Il labirinto e' popolato da strani personaggi.\n"+
			"E' un gioco di ruolo in cui il giocatore naviga tra stanze.\n"+
			"Digita 'aiuto' se hai bisogno di un suggerimento.";
			
	// Aggiunti i comandi "prendi" e "posa" per l'Esercizio 3
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole console) {
		this.partita = new Partita();
		this.console = console;
	}

	public void gioca() {
		String istruzione; 
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do {		
			istruzione = this.console.leggiRiga();
		} while (!processaIstruzione(istruzione));
	}   

	private boolean processaIstruzione(String istruzione) {
		if (istruzione == null || istruzione.trim().isEmpty())
			return false;

		Comando comandoDaEseguire = new Comando(istruzione);
		String nomeComando = comandoDaEseguire.getNome();

		if (nomeComando.equals("fine")) {
			this.fine(); 
			return true;
		} else if (nomeComando.equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		} else if (nomeComando.equals("aiuto")) {
			this.aiuto();
		} else if (nomeComando.equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} else if (nomeComando.equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} else {
			this.console.mostraMessaggio("Comando sconosciuto");
		}

		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		} else if (this.partita.isFinita()) {
			this.console.mostraMessaggio("Hai esaurito i CFU...");
			return true;
		}

		return false;
	}   

	private void aiuto() {
		StringBuilder risultato = new StringBuilder();
		for (int i = 0; i < elencoComandi.length; i++) {
			risultato.append(elencoComandi[i]).append(" ");
		}
		this.console.mostraMessaggio(risultato.toString());
	}

	private void vai(String direzione) {
		if (direzione == null) {
			this.console.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.console.mostraMessaggio("Direzione inesistente");
			return;
		}
		this.partita.setStanzaCorrente(prossimaStanza);
		int cfu = this.partita.getCfu();
		this.partita.setCfu(cfu - 1);
		this.console.mostraMessaggio(partita.getStanzaCorrente().toString());
	}

	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.console.mostraMessaggio("Cosa vuoi prendere? Devi specificare un attrezzo!");
			return;
		}
		
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		
		if (attrezzo != null) {
			if (this.partita.getGiocatore().getBorsa().AddAttrezzo(attrezzo)) {
				stanzaCorrente.removeAttrezzo(attrezzo);
				this.console.mostraMessaggio("Hai raccolto: " + nomeAttrezzo);
			} else {
				this.console.mostraMessaggio("La tua borsa è piena! Non puoi prendere " + nomeAttrezzo);
			}
		} else {
			this.console.mostraMessaggio("Qui non c'è nessun attrezzo chiamato " + nomeAttrezzo);
		}
	}

	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.console.mostraMessaggio("Cosa vuoi posare? Devi specificare un attrezzo!");
			return;
		}
		
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);
		
		if (attrezzo != null) {
			Stanza stanzaCorrente = this.partita.getStanzaCorrente();
			if (stanzaCorrente.addAttrezzo(attrezzo)) {
				borsa.removeAttrezzo(nomeAttrezzo);
				this.console.mostraMessaggio("Hai posato: " + nomeAttrezzo);
			} else {
				this.console.mostraMessaggio("La stanza è piena di cianfrusaglie, non puoi posare " + nomeAttrezzo);
			}
		} else {
			this.console.mostraMessaggio("Non hai " + nomeAttrezzo + " nella borsa!");
		}
	}

	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!");
	}

	public static void main(String[] argc) {
		// UNICA istanza di IOConsole come richiesto dall'Esercizio 5
		IOConsole console = new IOConsole();
		
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}