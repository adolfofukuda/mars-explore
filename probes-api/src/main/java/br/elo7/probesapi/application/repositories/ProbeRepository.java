package br.elo7.probesapi.application.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.elo7.probesapi.application.entities.ProbeApp;

@Repository
public interface ProbeRepository extends CrudRepository<ProbeApp, String> {
	
}