package br.elo7.probesapi.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.probesapi.applicataion.entities.ProbesSetup;
import br.elo7.probesapi.application.interfaces.ProbesService;

@RestController
@RequestMapping(path = "mars-exploration",produces = MediaType.APPLICATION_JSON_VALUE)
public class MarsExplorationController {
	@Autowired 
	private ProbesService probesService;
	
	@PostMapping
	public void setup(@RequestBody ProbesSetup probes) throws InvalidCoordinateException {
		probesService.setup(probes);
	}
}
