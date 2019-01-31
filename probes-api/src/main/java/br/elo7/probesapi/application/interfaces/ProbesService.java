package br.elo7.probesapi.application.interfaces;

import java.util.List;
import java.util.Map;

import br.elo7.probesapi.applicataion.entities.ProbeApp;
import br.elo7.probesapi.applicataion.entities.ProbesSetup;
import br.elo7.probesapi.application.exceptions.InvalidConfigurationProbesException;

public interface ProbesService {
	public void setup(ProbesSetup probeSetup) throws InvalidConfigurationProbesException;
	public List<ProbeApp> move(List<String> probesIds);
	public void newMovements(Map<String, String> probesMovements);
	public ProbeApp getInfo(String probeId);
}
