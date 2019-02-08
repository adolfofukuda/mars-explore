package br.elo7.probesapi.application.interfaces;

import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.probesapi.application.entities.ProbeApp;
import br.elo7.probesapi.application.entities.ProbesSetup;
import br.elo7.probesapi.application.exceptions.InvalidConfigurationProbesException;
import br.elo7.probesapi.application.exceptions.InvalidMovementsException;
import br.elo7.probesapi.application.exceptions.ProbeNotFoundException;

public interface ProbesService {
	public void setup(ProbesSetup probesSetup)
			throws InvalidConfigurationProbesException, InvalidCoordinateException, InvalidMovementsException;

	public ProbeApp move(String probeId) throws InvalidCoordinateException, ProbeNotFoundException;

	public ProbeApp newMovements(String probeId, String movements)
			throws ProbeNotFoundException, InvalidMovementsException;

	public ProbeApp getInfo(String probeId) throws ProbeNotFoundException;
}
