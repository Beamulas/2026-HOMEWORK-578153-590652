package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa borsaVuota;
	private Attrezzo piombo;
	private Attrezzo piuma;
	
	@BeforeEach
	public void setUp() {
		this.borsaVuota = new Borsa();
		this.piombo = new Attrezzo("piombo", 10);
		this.piuma = new Attrezzo("piuma", 1);
	}
	
	@Test
	public void testAddAttrezzo() {
		//Aggiungo un attrezzo leggero, deve riuscirci 
		assertTrue(this.borsaVuota.AddAttrezzo(this.piuma));
	}
	
	@Test
	public void testAddAttrezzoTroppoPesante() {
		//Riempio la borsa con un attrezzo da 10 kg
		this.borsaVuota.AddAttrezzo(this.piombo);
		//Tento di aggiungere un altro attrezzo, ma non deve entrare 
		assertFalse(this.borsaVuota.AddAttrezzo(this.piuma));
	}
}
