package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {

	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		this.partita =  new Partita();		
	}
	
	@Test
	public void testPartitaNonFinitaAllInizio() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testPartitaFinitaSenzaCfu() {
		this.partita.setCfu(0);
		assertTrue(this.partita.isFinita());
		
	}

}
