package br.elo7.mars.domain;

import java.util.Arrays;
import java.util.List;

import br.elo7.mars.domain.interfaces.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartesianCoordinate implements Coordinate<Integer> {

	private Integer x;
	private Integer y;
	
	@Override
	public Coordinate<Integer> getCurrent() {
		return this;
	}

	@Override
	public Coordinate<Integer> add(Coordinate<Integer> to) {
		List<Integer> values =	to.getCoordinateValues();
		return new CartesianCoordinate(this.x + values.get(0),this.y + values.get(1));
	}

	@Override
	public List<Integer> getCoordinateValues() {
		return Arrays.asList(this.x, this.y);
	}
}
