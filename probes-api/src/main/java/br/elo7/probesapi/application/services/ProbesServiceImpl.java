package br.elo7.probesapi.application.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.elo7.probesapi.applicataion.entities.ProbeApp;
import br.elo7.probesapi.applicataion.entities.ProbesSetup;
import br.elo7.probesapi.application.exceptions.InvalidConfigurationProbesException;
import br.elo7.probesapi.application.interfaces.ProbesService;
import br.elo7.probesapi.application.repositories.ProbeRepository;

@Service
public class ProbesServiceImpl implements ProbesService {

	@Autowired
	private ProbeRepository repository;
	
	@Override
	public void setup(ProbesSetup probeSetup) throws InvalidConfigurationProbesException {
		
		
	}

	@Override
	public List<ProbeApp> move(List<String> probesIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newMovements(Map<String, String> probesMovements) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProbeApp getInfo(String probeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
