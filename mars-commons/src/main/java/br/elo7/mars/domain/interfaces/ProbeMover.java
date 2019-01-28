package br.elo7.mars.domain.interfaces;

import java.util.List;

import org.javatuples.Pair;

import br.elo7.mars.domain.Heading;
import br.elo7.mars.domain.exceptions.InvalidCoordinateException;


public interface ProbeMover<C extends Coordinate<?>, H extends Heading> {
	List<Pair<C, H>>  moveTo(List<String> movementList) throws InvalidCoordinateException; 
}
