package br.elo7.probesapi.applicataion.entities;

import br.elo7.mars.domain.CartesianCoordinate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProbeApp {
	@Getter
	private String id;
	@Getter 
	private CartesianCoordinate currentPosition;
	@Getter 
	private String heading;
	@Getter @Setter
	private String movements;
}