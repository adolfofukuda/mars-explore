package br.elo7.mars.domain.interfaces;

import java.util.List;

public interface Coordinate<T> {
	Coordinate<T> getCurrent();
	Coordinate<T> add(Coordinate<T> to);
	List<T> getCoordinateValues();
}
