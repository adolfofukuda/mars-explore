package br.elo7.mars.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.elo7.mars.domain.interfaces.Coordinate;

public class CartesianCoordinateTest {

	@Test
	public void moveTo() {
		Coordinate<Integer> coordinate = new CartesianCoordinate(1, 2);
		Coordinate<Integer> result = new CartesianCoordinate(0, 1);

		assertEquals(result, coordinate.add(new CartesianCoordinate(-1, -1)));
	}

}
