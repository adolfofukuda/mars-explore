package br.elo7.mars.domain.enums;

public enum WindRoseEnum {
	NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");
	
    String direction;
	
	WindRoseEnum(String directionSymbol) {
		direction = directionSymbol;
	}
	
	public static WindRoseEnum getBySymbol(String directionSymbol) {
		for (WindRoseEnum e : WindRoseEnum.values()) {
			if (e.direction.equalsIgnoreCase(directionSymbol)) {
				return e;
			}
		}
		return null;
	}
	
	public String getSymbol() {
		return direction;
	}
}
