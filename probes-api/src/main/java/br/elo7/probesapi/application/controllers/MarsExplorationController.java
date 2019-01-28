package br.elo7.probesapi.application.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.elo7.probesapi.applicataion.entities.ProbeApp;

@RestController
@RequestMapping(path = "mars-exploration",produces = MediaType.APPLICATION_JSON_VALUE)
public class MarsExplorationController {
	@PostMapping
	public void setup(@RequestBody ProbeApp probe) {
		System.out.println(probe);
	}
}
