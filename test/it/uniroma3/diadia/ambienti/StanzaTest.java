package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanzaVuota;
	private Attrezzo osso;
	
	@BeforeEach
	public void setUp() {
		this.stanzaVuota = new Stanza("Stanza Vuota");
		this.osso = new Attrezzo("osso", 1);
	}
	
	@Test
	public void testAddAttrezzo() {
		//Verifico che se aggiungo un attrezzo, il metodo mi restituisca true
		assertTrue(this.stanzaVuota.addAttrezzo(this.osso));
	}
	
	@Test
	public void testHasAttrezzo() {
		//Aggiungo l'attrezzo e verifico che la stanza lo veda 
		this.stanzaVuota.addAttrezzo(this.osso);
		assertTrue(this.stanzaVuota.hasAttrezzo("osso"));
	}
	
	@Test
	public void testGetAttrezzo() {
		//aggiungo l'attrezzo e verifico che se lo estraggo, sia proprio quello giusto 
		this.stanzaVuota.addAttrezzo(this.osso);
		assertEquals(this.osso, this.stanzaVuota.getAttrezzo("osso"));
	}
	
	@Test
	public void testStanzaVuotaNonHaAttrezzi() {
		//Verifico che una stanza appena creata non abbia un attrezzo a caso 
		assertFalse(this.stanzaVuota.hasAttrezzo("osso"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		//metto un osso nella stanza
		this.stanzaVuota.addAttrezzo(this.osso);
		
		//lo rimuovo e verifico che l'operazione vada bene 
		assertTrue(this.stanzaVuota.removeAttrezzo(this.osso));
		
		//verifico che l'osso non sia più nella stanza
		assertFalse(this.stanzaVuota.hasAttrezzo("osso"));
	}

}
