package br.elo7.probesapi.application.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.elo7.mars.domain.CartesianCoordinate;
import br.elo7.mars.domain.Heading;
import br.elo7.mars.domain.entities.Probe;
import br.elo7.mars.domain.enums.WindRoseEnum;
import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.mars.domain.interfaces.Coordinate;
import br.elo7.probesapi.application.entities.PlanToExplore;
import br.elo7.probesapi.application.entities.ProbeApp;
import br.elo7.probesapi.application.entities.ProbesSetup;
import br.elo7.probesapi.application.exceptions.InvalidConfigurationProbesException;
import br.elo7.probesapi.application.exceptions.InvalidMovementsException;
import br.elo7.probesapi.application.exceptions.ProbeNotFoundException;
import br.elo7.probesapi.application.interfaces.ProbesService;
import br.elo7.probesapi.application.repositories.PlanToExploreRepository;
import br.elo7.probesapi.application.repositories.ProbeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProbesServiceImpl implements ProbesService {

	@Autowired
	private ProbeRepository probeRepository;

	@Autowired
	private PlanToExploreRepository planRepository;

	private static String PLAN_NAME = "elo7Mars";

	@Override
	public void setup(ProbesSetup probesSetup)
			throws InvalidConfigurationProbesException, InvalidCoordinateException, InvalidMovementsException {
		log.info("Initializing plan to explore with configuration: {}", probesSetup);
		probeRepository.deleteAll();
		planRepository.deleteAll();
		planRepository.save(new PlanToExplore(PLAN_NAME, probesSetup.getLimit()));

		probesSetup.validateConfigurationProbes();
		probesSetup.validateLimits();
		for (ProbeApp p: probesSetup.getProbes()) {
			p.validateMovements();
		}
		probesSetup.getProbes().forEach(p -> probeRepository.save(p));
	}

	@Override
	public ProbeApp move(String probeId) throws InvalidCoordinateException, ProbeNotFoundException {
		ProbeApp probe = getInfo(probeId);
		CartesianCoordinate limit = planRepository.findById(PLAN_NAME).get().getLimits();

		// translate to domain objects
		Probe probeDomain = new Probe(probe.getId(), limit, probe.getCurrentPosition(),
				new Heading(WindRoseEnum.getBySymbol(probe.getHeading())), probe.getMovementsAsList());

		// listing all locations visited by probeId
		List<Pair<Coordinate<Integer>, Heading>> locations = probeDomain.getLocations();

		// retrieve all probes except the probeId itself
		List<ProbeApp> probes = ((List<ProbeApp>) probeRepository.findAll()).stream().filter(p -> !p.equals(probe))
				.collect(Collectors.toList());

		// another probe at same location?
		for (ProbeApp another : probes) {
			if (locations.parallelStream()
					.filter(p -> ((CartesianCoordinate) p.getValue0()).equals(another.getCurrentPosition()))
					.count() > 0) {
				throw new InvalidCoordinateException("Probe cannot complain because there another probe");
			}
		}
		// saving the last location
		CartesianCoordinate lastCoordinate = (CartesianCoordinate) locations.get(locations.size() - 1).getValue0();
		String lastHeading = locations.get(locations.size() - 1).getValue1().currentWindRose().getSymbol();
		probe.setCurrentPosition(lastCoordinate);
		probe.setHeading(lastHeading);
		probe.setMovements("");
		probeRepository.save(probe);
		return probe;
	}

	@Override
	public ProbeApp newMovements(String probeId, String movements)
			throws ProbeNotFoundException, InvalidMovementsException {
		ProbeApp probe = getInfo(probeId);
		probe.setMovements(movements);
		probe.validateMovements();
		probeRepository.save(probe);
		return probe;
	}

	@Override
	public ProbeApp getInfo(String probeId) throws ProbeNotFoundException {
		try {
			ProbeApp result = probeRepository.findById(probeId).get();
			return result;
		} catch (NoSuchElementException e) {
			throw new ProbeNotFoundException();
		}
	}

}
