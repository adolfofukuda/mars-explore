package br.elo7.mars.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import br.elo7.mars.domain.enums.MovementEnum;
import br.elo7.mars.domain.enums.WindRoseEnum;
import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.mars.domain.interfaces.Coordinate;
import br.elo7.mars.domain.interfaces.ProbeMover;

public class ProbeMoverInCartesianCoordinates implements ProbeMover<Coordinate<Integer>, Heading> {
	private Map<WindRoseEnum, CartesianCoordinate> headingsMap = new HashMap<WindRoseEnum, CartesianCoordinate>() {
		private static final long serialVersionUID = 5831659762309460741L;
		{
			put(WindRoseEnum.NORTH, new CartesianCoordinate(0, 1));
			put(WindRoseEnum.EAST, new CartesianCoordinate(1, 0));
			put(WindRoseEnum.SOUTH, new CartesianCoordinate(0, -1));
			put(WindRoseEnum.WEST, new CartesianCoordinate(-1, 0));
		}
	};

	private CartesianCoordinate initialPosition;
	private CartesianCoordinate limit;

	private Heading heading;

	private Map<MovementEnum, Runnable> movesMap = new HashMap<MovementEnum, Runnable>() {
		private static final long serialVersionUID = 8264967909369250215L;
		{
			put(MovementEnum.LEFT, () -> heading = new Heading(heading.previous()));
			put(MovementEnum.RIGHT, () -> heading = new Heading(heading.next()));
			put(MovementEnum.MOVE, () -> initialPosition = (CartesianCoordinate) initialPosition
					.add(headingsMap.get(heading.currentWindRose())));

		}
	};

	public ProbeMoverInCartesianCoordinates(CartesianCoordinate limit, CartesianCoordinate initialPosition, Heading heading) {
		this.limit = limit;
		this.heading = heading;
		this.initialPosition = initialPosition;
	}

	@Override
	public List<Pair<Coordinate<Integer>, Heading>> moveTo(List<String> movementList) throws InvalidCoordinateException {

		List<Pair<Coordinate<Integer>, Heading>> result = new ArrayList<Pair<Coordinate<Integer>, Heading>>();
		for (String m : movementList) {
			movesMap.get(MovementEnum.getBySymbol(m)).run();
			if (initialPosition.getX() > limit.getX() || initialPosition.getY() > limit.getY()) {
				throw new InvalidCoordinateException();
			}
			result.add(new Pair<Coordinate<Integer>, Heading>(initialPosition, heading));
		}
		return result;
	}

}
