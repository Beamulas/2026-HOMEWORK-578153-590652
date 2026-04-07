package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {

	private Giocatore giocatore;

	@BeforeEach
	public void setUp() {
		this.giocatore = new Giocatore();
	}

	@Test
	public void testCfuIniziali() {
		// Di solito i CFU iniziali in DiaDia sono 20
		assertEquals(20, this.giocatore.getCfu());
	}

	@Test
	public void testSetCfu() {
		this.giocatore.setCfu(10);
		assertEquals(10, this.giocatore.getCfu());
	}

	@Test
	public void testGiocatoreHaBorsa() {
		// Verifico che il giocatore appena creato abbia una borsa e che non sia nulla
		assertNotNull(this.giocatore.getBorsa());
	}
}
