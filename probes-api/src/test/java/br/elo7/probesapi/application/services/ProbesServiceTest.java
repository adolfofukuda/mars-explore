package br.elo7.probesapi.application.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import br.elo7.mars.domain.CartesianCoordinate;
import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.probesapi.application.entities.PlanToExplore;
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
	
	private ProbesSetup probeSetup = null;

	@Before
	public  void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		probeSetup = getSetup(DEFAULT);
		Mockito.when(planRepository.save(Mockito.any(PlanToExplore.class))).thenReturn(new PlanToExplore("", null));
		Mockito.when(planRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new PlanToExplore("elo7Mars", new CartesianCoordinate(10, 10))));
		Mockito.when(probeRepository.save(Mockito.any())).thenReturn(null);
		Mockito.when(probeRepository.findById("spirit")).thenReturn(Optional.of(getDefaultSpirit()));
		Mockito.when(probeRepository.findById("opportunity")).thenReturn(Optional.of(getDefaultOpportunity()));
		Mockito.when(probeRepository.findById("curiosity")).thenReturn(Optional.of(getDefaultCuriosity()));
		Mockito.when(probeRepository.findById("skylab")).thenThrow(new NoSuchElementException());
		
	}

	private ProbeApp getDefaultSpirit( ) {
		return new ProbeApp("spirit", new CartesianCoordinate(1,2), "N", "RMMMRM");
	}

	private ProbeApp getDefaultOpportunity( ) {
		return new ProbeApp("opportunity", new CartesianCoordinate(4,1), "W", "");
	}

	private ProbeApp getDefaultCuriosity( ) {
		return new ProbeApp("curiosity", new CartesianCoordinate(5,2), "E", "MMMMMMMM");
	}

	private ProbesSetup getSetup(String cenario) {
		ProbeApp spirit = null;
		ProbeApp opportunity = null;
		ProbeApp curiosity = null;
		CartesianCoordinate limits = null;
		
		if (cenario.equals(DEFAULT)) {
			spirit = getDefaultSpirit();
			opportunity = getDefaultOpportunity();
			curiosity = getDefaultCuriosity();
			limits = new CartesianCoordinate(10, 10);
		} else if (cenario.equals(CENARIO1)){
			spirit = new ProbeApp("spirit", new CartesianCoordinate(1,2), "N", "");
			opportunity = new ProbeApp("opportunity", new CartesianCoordinate(4,1), "W", "");
			curiosity = new ProbeApp("curiosity", new CartesianCoordinate(1,2), "E", "");
			limits = new CartesianCoordinate(10, 10);
		} else {
			spirit = new ProbeApp("spirit", new CartesianCoordinate(1,2), "N", "");
			opportunity = new ProbeApp("opportunity", new CartesianCoordinate(6,2), "W", "");
			curiosity = new ProbeApp("curiosity", new CartesianCoordinate(1,8), "E", "");
			limits = new CartesianCoordinate(0, 0);
		}
		
		return new ProbesSetup(limits, Arrays.asList(spirit, opportunity, curiosity));
	}


	// Test for setup
	@Test
	public void should_limits_be_greater_than_zero() {
		try {
			probeService.setup(probeSetup);
			assertTrue(probeSetup.getLimit().getX() > 0);
			assertTrue(probeSetup.getLimit().getY() > 0);
		} catch (Exception e) {
			fail(e.getMessage());
		} 
	}

	@Test
	public void should_limits_be_not_null() {
		try {
			probeService.setup(probeSetup);
			assertTrue(probeSetup.getLimit() != null);
		} catch (Exception e) {
			fail(e.getMessage());
		} 
	}

	@Test
	public void should_probes_be_not_null() {
		try {
			probeService.setup(probeSetup);
			assertTrue(probeSetup.getProbes() != null);
			assertTrue(probeSetup.getProbes().size() > 0);
		} catch (Exception e) {
			fail(e.getMessage());
		} 
	}

	@Test
	public void should_probes_have_valid_locations() {
		try {
			probeService.setup(probeSetup);
		} catch (Exception e) {
			fail(e.getMessage());
		} 
	}

	@Test(expected = InvalidCoordinateException.class)
	public void invalid_limits() throws InvalidConfigurationProbesException, InvalidCoordinateException {
		probeSetup = getSetup(CENARIO2);
		probeService.setup(probeSetup);
	}

	@Test(expected = InvalidConfigurationProbesException.class)
	public void invalid_probes_configuration() throws InvalidConfigurationProbesException, InvalidCoordinateException {
		probeSetup = getSetup(CENARIO1);
		probeService.setup(probeSetup);
	}

	// Test for get info
	@Test(expected = ProbeNotFoundException.class)
	public void probe_not_found() throws ProbeNotFoundException {
		probeService.getInfo("skylab");
	}

	@Test
	public void probe_found() {
		try {
		assertEquals(getDefaultCuriosity(), probeService.getInfo("curiosity"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	// Tests for new movements for probes
	@Test(expected = InvalidMovementsException.class)
	public void invalid_movements() throws ProbeNotFoundException, InvalidMovementsException {
		probeService.newMovements("spirit", "MLKKK");
	}

	// Tests for moves
	@Test(expected = ProbeNotFoundException.class)
	public void probe_not_found_to_move() throws ProbeNotFoundException, InvalidMovementsException, InvalidCoordinateException {
		probeService.move("skylab");
	}

	@Test(expected = InvalidCoordinateException.class)
	public void probe_move_beyond_limits() throws InvalidCoordinateException, ProbeNotFoundException {
		probeService.move("curiosity");
	}

	@Test(expected = InvalidCoordinateException.class)
	public void two_probes_collision() throws InvalidCoordinateException, ProbeNotFoundException {
		probeService.move("spirit");
	}

}
