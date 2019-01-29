package br.elo7.probesapi.applicataion.entities;

import java.util.List;

import br.elo7.mars.domain.CartesianCoordinate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProbesSetup {
	@Getter
	private CartesianCoordinate limit;
	@Getter
	private List<ProbeApp> probes;
}
