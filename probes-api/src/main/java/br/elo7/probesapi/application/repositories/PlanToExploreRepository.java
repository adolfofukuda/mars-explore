package br.elo7.probesapi.application.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.elo7.probesapi.applicataion.entities.PlanToExplore;

@Repository
public interface PlanToExploreRepository extends CrudRepository<PlanToExplore, String> {

}
