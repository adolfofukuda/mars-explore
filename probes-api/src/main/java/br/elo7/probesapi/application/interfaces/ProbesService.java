package br.elo7.probesapi.application.interfaces;

import java.util.List;
import java.util.Map;

import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.probesapi.applicataion.entities.ProbeApp;
import br.elo7.probesapi.applicataion.entities.ProbesSetup;

public interface ProbesService {
	public void setup(ProbesSetup probeSetup) throws InvalidCoordinateException;
	public List<ProbeApp> move(List<String> probesIds);
	public void newMovements(Map<String, String> probesMovements);
	public ProbeApp getInfo(String probeId);
}
