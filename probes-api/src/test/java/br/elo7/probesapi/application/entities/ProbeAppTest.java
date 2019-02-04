package br.elo7.probesapi.application.entities;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.elo7.mars.domain.CartesianCoordinate;
import br.elo7.probesapi.applicataion.entities.ProbeApp;
import br.elo7.probesapi.application.exceptions.InvalidMovementsException;

public class ProbeAppTest {


	@Test
	public void validateMovementsTest() {
		ProbeApp probe = new ProbeApp("teste", new CartesianCoordinate(1, 2), "N", "LMMMR"); 
		try {
			probe.validateMovements();
		} catch (InvalidMovementsException e) {
			fail(e.getMessage());
		}		
				
	}
}
