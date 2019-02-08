package br.elo7.probesapi.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.probesapi.application.entities.ProbeApp;
import br.elo7.probesapi.application.entities.ProbesSetup;
import br.elo7.probesapi.application.exceptions.InvalidConfigurationProbesException;
import br.elo7.probesapi.application.exceptions.InvalidMovementsException;
import br.elo7.probesapi.application.exceptions.ProbeNotFoundException;
import br.elo7.probesapi.application.interfaces.ProbesService;

@RestController
@RequestMapping(path = "mars-explore",produces = MediaType.APPLICATION_JSON_VALUE)
public class MarsExplorationController {
	@Autowired 
	private ProbesService probesService;
	
	@PostMapping("/setup")
	public void setup(@RequestBody ProbesSetup probes) throws InvalidConfigurationProbesException, InvalidCoordinateException, InvalidMovementsException {
		probesService.setup(probes);
	}
	
	@GetMapping("/probe/{probeId}/info")
	public ProbeApp getInfo(@PathVariable String probeId) throws ProbeNotFoundException {
		return probesService.getInfo(probeId);
	}
	
	@PutMapping("/probe/{probeId}/movements/{moves}")
	public ProbeApp newMovements(@PathVariable String probeId, @PathVariable String moves) throws ProbeNotFoundException, InvalidMovementsException {
		return probesService.newMovements(probeId, moves);
	}
	
	@GetMapping("/probe/{probeId}/move") 
	public ProbeApp move(@PathVariable String probeId) throws InvalidCoordinateException, ProbeNotFoundException {
		return probesService.move(probeId);
	}
}
