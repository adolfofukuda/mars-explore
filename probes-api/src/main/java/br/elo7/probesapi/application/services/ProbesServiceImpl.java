package br.elo7.probesapi.application.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.probesapi.applicataion.entities.PlanToExplore;
import br.elo7.probesapi.applicataion.entities.ProbeApp;
import br.elo7.probesapi.applicataion.entities.ProbesSetup;
import br.elo7.probesapi.application.exceptions.InvalidConfigurationProbesException;
import br.elo7.probesapi.application.exceptions.InvalidMovementsException;
import br.elo7.probesapi.application.exceptions.ProbeNotFoundException;
import br.elo7.probesapi.application.interfaces.ProbesService;
import br.elo7.probesapi.application.repositories.PlanToExploreRepository;
import br.elo7.probesapi.application.repositories.ProbeRepository;

@Service
public class ProbesServiceImpl implements ProbesService {

	@Autowired
	private ProbeRepository probeRepository;

	@Autowired
	private PlanToExploreRepository planRepository;

	private static String PLAN_NAME = "elo7Mars";

	@Override
	public void setup(ProbesSetup probesSetup) throws InvalidConfigurationProbesException, InvalidCoordinateException {
		probeRepository.deleteAll();
		planRepository.deleteAll();
		planRepository.save(new PlanToExplore(PLAN_NAME, probesSetup.getLimit()));

		probesSetup.validateConfigurationProbes();
		probesSetup.validateLimits();
		probesSetup.getProbes().forEach(p -> probeRepository.save(p));
	}

	@Override
	public ProbeApp move(String probeId) throws InvalidCoordinateException {
		return null;
	}

	@Override
	public ProbeApp newMovements(String probeId, String movements) throws ProbeNotFoundException, InvalidMovementsException{
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
