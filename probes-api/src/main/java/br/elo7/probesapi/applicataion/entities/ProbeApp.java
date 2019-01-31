package br.elo7.probesapi.applicataion.entities;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import br.elo7.mars.domain.CartesianCoordinate;
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
	@Getter 
	private CartesianCoordinate currentPosition;
	@Getter 
	private String heading;
	@Getter @Setter
	private String movements;
}