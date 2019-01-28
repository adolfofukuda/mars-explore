package br.elo7.mars.domain.enums;

public enum MovementEnum {
	LEFT("L"), RIGHT("R"), MOVE("M");
	
	String symbol;
	MovementEnum(String symbolValue) {
		symbol = symbolValue;
	}
	
	public static MovementEnum getBySymbol(String symbol) {
		for (MovementEnum m : MovementEnum.values()) {
			if (m.symbol.equalsIgnoreCase(symbol)) {
				return m;
			}
		}
		return null;
	}
}
