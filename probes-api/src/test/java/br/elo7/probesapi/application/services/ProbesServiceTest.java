package br.elo7.probesapi.application.services;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.elo7.mars.domain.CartesianCoordinate;
import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.probesapi.application.entities.ProbeApp;
import br.elo7.probesapi.application.entities.ProbesSetup;
import br.elo7.probesapi.application.exceptions.InvalidConfigurationProbesException;
import br.elo7.probesapi.application.exceptions.InvalidMovementsException;
import br.elo7.probesapi.application.exceptions.ProbeNotFoundException;
import br.elo7.probesapi.application.repositories.PlanToExploreRepository;
import br.elo7.probesapi.application.repositories.ProbeRepository;

public class ProbesServiceTest {
	private static String DEFAULT = "DEFAULT";
	private static String CENARIO1 = "CENARIO1";
	private static String CENARIO2 = "CENARIO2";
	
	@Mock
	private ProbeRepository probeRepository;
	
	@Mock
	private PlanToExploreRepository planRepository;
	
	@InjectMocks
	private ProbesServiceImpl probeService;
	
	private ProbesSetup probeSetup;

	@Before
	public  void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		probeSetup = getSetup(DEFAULT);
	}


	private ProbesSetup getSetup(String cenario) {
		ProbeApp spirit = null;
		ProbeApp opportunity = null;
		ProbeApp curiosity = null;
		CartesianCoordinate limits = null;
		
		if (cenario.equals(DEFAULT)) {
			spirit = new ProbeApp("spirit", new CartesianCoordinate(1,2), "N", "");
			opportunity = new ProbeApp("opportunity", new CartesianCoordinate(1,2), "W", "");
			curiosity = new ProbeApp("curiosity", new CartesianCoordinate(1,2), "E", "");
			limits = new CartesianCoordinate(10, 10);
		} else if (cenario.equals(CENARIO1)){
			
		} else {
			spirit = new ProbeApp("spirit", new CartesianCoordinate(1,2), "N", "");
			opportunity = new ProbeApp("opportunity", new CartesianCoordinate(1,2), "W", "");
			curiosity = new ProbeApp("curiosity", new CartesianCoordinate(1,2), "E", "");
			limits = new CartesianCoordinate(0, 0);
		}
		
		return new ProbesSetup(limits, Arrays.asList(spirit, opportunity, curiosity));
	}


	// Test for setup
	@Test
	public void should_limits_be_greater_than_zero() {
		fail("Not yet implemented");
	}

	@Test
	public void should_limits_be_not_null() {
		fail("Not yet implemented");
	}

	@Test
	public void should_probes_be_not_null() {
		fail("Not yet implemented");
	}

	@Test
	public void should_probes_have_valid_locations() {
		fail("Not yet implemented");
	}

	@Test(expected = InvalidCoordinateException.class)
	public void invalid_limits() {
		fail("Not yet implemented");
	}

	@Test(expected = InvalidConfigurationProbesException.class)
	public void invalid_probes_configuration() {
		fail("Not yet implemented");
	}

	// Test for get info
	@Test(expected = ProbeNotFoundException.class)
	public void probe_not_found() {
		fail("Not yet implemented");
	}

	@Test
	public void probe_found() {
		fail("Not yet implemented");
	}

	// Tests for new movements for probes
	@Test(expected = InvalidMovementsException.class)
	public void invalid_movements() {
		fail("Not yet implemented");
	}

	// Tests for moves
	@Test(expected = ProbeNotFoundException.class)
	public void probe_not_found_to_move() {
		fail("Not yet implemented");
	}

	@Test(expected = InvalidCoordinateException.class)
	public void probe_move_beyond_limits_X() {
		fail("Not yet implemented");
	}

	@Test(expected = InvalidCoordinateException.class)
	public void probe_move_beyond_limits_Y() {
		fail("Not yet implemented");
	}

	@Test(expected = InvalidCoordinateException.class)
	public void probe_move_to_negative_value_X() {
		fail("Not yet implemented");
	}

	@Test(expected = InvalidCoordinateException.class)
	public void probe_move_to_negative_value_Y() {
		fail("Not yet implemented");
	}

	@Test(expected = InvalidCoordinateException.class)
	public void two_probes_collision() {
		fail("Not yet implemented");
	}

}
