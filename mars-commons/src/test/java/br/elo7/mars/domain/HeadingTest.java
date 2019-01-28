package br.elo7.mars.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.elo7.mars.domain.enums.WindRoseEnum;

public class HeadingTest {

	@Test
	public void rotateFromSouthToWest() {
		Heading heading = new Heading(WindRoseEnum.SOUTH);
		assertEquals(WindRoseEnum.WEST, heading.next());
	}

	@Test
	public void rotateFromSouthToNorth() {
		Heading heading = new Heading(WindRoseEnum.SOUTH);
		heading.next();	
		assertEquals(WindRoseEnum.NORTH, heading.next());
	}
	
	@Test
	public void rotateFromNorthToWest() {
		Heading heading = new Heading(WindRoseEnum.NORTH);
		assertEquals(WindRoseEnum.WEST, heading.previous());
	}
	
	@Test
	public void rotateFromNorthToSouth() {
		Heading heading = new Heading(WindRoseEnum.SOUTH);
		heading.previous();	
		assertEquals(WindRoseEnum.NORTH, heading.previous());
	}
	
	@Test
	public void rotateFromWestToNorth() {
		Heading heading = new Heading(WindRoseEnum.WEST);
		assertEquals(WindRoseEnum.NORTH, heading.next());
	}
}
