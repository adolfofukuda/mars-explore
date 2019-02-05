package br.elo7.probesapi.application.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.elo7.mars.domain.CartesianCoordinate;
import br.elo7.mars.domain.enums.MovementEnum;
import br.elo7.probesapi.application.exceptions.InvalidMovementsException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@RedisHash("ProbeApp")
public class ProbeApp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2532692576329888122L;
	@Getter
	private String id;
	@Getter @Setter
	private CartesianCoordinate currentPosition;
	@Getter @Setter
	private String heading;
	@Getter @Setter
	private String movements;
	
	@JsonIgnore
	public List<String> getMovementsAsList() {
		List<String> result = new ArrayList<String>();
		for (int i = 0 ;i < movements.length(); i++) {
			result.add(movements.substring(i, i+1));
		}
		return result;
	}
	
	@JsonIgnore
	public void validateMovements() throws InvalidMovementsException {
		List<String> movements = getMovementsAsList();
		for (String movement: movements) {
			if (MovementEnum.getBySymbol(movement) == null) {
				throw new InvalidMovementsException(String.format("Invalid Movement %s", movement));
			}
		}
	}
}