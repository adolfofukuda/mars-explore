package br.elo7.mars.domain;

import java.util.Arrays;
import java.util.List;

import br.elo7.mars.domain.enums.WindRoseEnum;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Heading {

	private List<WindRoseEnum> rose = Arrays.asList(WindRoseEnum.NORTH, WindRoseEnum.EAST, WindRoseEnum.SOUTH,
			WindRoseEnum.WEST);
	private int currentIndex = 0;

	public Heading(WindRoseEnum head) {
		for (int i = 0; i < rose.size(); i++) {
			if (head.equals(rose.get(i))) {
				currentIndex = i;
				break;
			}
		}
	}

	public WindRoseEnum next() {
		return rose.get(this.nextIndex());
	}


	public WindRoseEnum previous() {
		return rose.get(this.previousIndex());
	}

	public int nextIndex() {
		if (currentIndex == (rose.size() - 1))
			return 0;
		return ++currentIndex;
	}

	public int previousIndex() {
		if (currentIndex == 0)
			return (rose.size() - 1);
		return --currentIndex;
	}

	public WindRoseEnum currentWindRose() {
		return rose.get(currentIndex);
	}
}
