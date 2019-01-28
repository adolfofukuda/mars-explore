package br.elo7.mars.domain.entities;

import java.util.List;

import org.javatuples.Pair;

import br.elo7.mars.domain.CartesianCoordinate;
import br.elo7.mars.domain.Heading;
import br.elo7.mars.domain.ProbeMoverInCartesianCoordinates;
import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.mars.domain.interfaces.Coordinate;
import br.elo7.mars.domain.interfaces.ProbeMover;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Probe {
	@Getter
	private String id;
	@Getter
	private Coordinate<Integer> limit;
	@Getter
	private Coordinate<Integer> initialPosition;
	@Getter
	private Heading heading;
	@Getter
	private List<String> movements;

	public Pair<Coordinate<Integer>, Heading> getLastKnownLocation() throws InvalidCoordinateException {
		return movements == null || movements.isEmpty()
				? new Pair<Coordinate<Integer>, Heading>(initialPosition, heading)
				: getProbeMover().moveTo(movements).get(movements.size()-1);
	}

	private ProbeMover<Coordinate<Integer>, Heading> getProbeMover() {
		return new ProbeMoverInCartesianCoordinates(
				(CartesianCoordinate) limit, (CartesianCoordinate) initialPosition, heading);
	}

}
