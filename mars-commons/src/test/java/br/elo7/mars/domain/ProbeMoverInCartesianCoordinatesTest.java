package br.elo7.mars.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.javatuples.Pair;
import org.junit.Test;

import br.elo7.mars.domain.enums.WindRoseEnum;
import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.mars.domain.interfaces.Coordinate;

public class ProbeMoverInCartesianCoordinatesTest {

	@Test
	public void tinyExploration()  {
		ProbeMoverInCartesianCoordinates mover = new ProbeMoverInCartesianCoordinates(new CartesianCoordinate(5, 5),
				new CartesianCoordinate(1, 2), new Heading(WindRoseEnum.NORTH));

		String a[] = new String[] { "L", "M", "L", "M", "L", "M", "L", "M", "M" };
		Pair<Coordinate<Integer>, Heading> lastPos = null;
		try {
			lastPos = mover.moveTo(Arrays.asList(a)).get(a.length - 1);
		} catch (InvalidCoordinateException e) {
			fail();
		}
		Pair<CartesianCoordinate, Heading> result = new Pair<CartesianCoordinate, Heading>(
				new CartesianCoordinate(1, 3), new Heading(WindRoseEnum.NORTH));
		assertEquals(result, lastPos);
	}

}
