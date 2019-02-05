package br.elo7.probesapi.application.entities;

import java.util.List;
import java.util.stream.Collectors;

import br.elo7.mars.domain.CartesianCoordinate;
import br.elo7.mars.domain.exceptions.InvalidCoordinateException;
import br.elo7.probesapi.application.exceptions.InvalidConfigurationProbesException;
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
	
	public void validateConfigurationProbes() throws InvalidConfigurationProbesException {
		int size = probes.stream().map(ProbeApp::getCurrentPosition).distinct().collect(Collectors.toList()).size();
		if (size != probes.size())
			throw new InvalidConfigurationProbesException();
	}
	
	public void validateLimits() throws InvalidCoordinateException {
		for (ProbeApp probe : probes) {
		if (probe == null || limit == null ||
			probe.getCurrentPosition() == null ||
			probe.getCurrentPosition().getX() == null || probe.getCurrentPosition().getY() == null ||
			limit.getX() == null || limit.getY() == null ||
			limit.getX() < 1 || limit.getY() < 1 ||
			probe.getCurrentPosition().getX() > limit.getX() || probe.getCurrentPosition().getY() > limit.getY())
			throw new InvalidCoordinateException();
		}
	}
}
