package br.elo7.probesapi.applicataion.entities;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import br.elo7.mars.domain.CartesianCoordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@RedisHash("PlanToExplore")
public class PlanToExplore implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1691097829116394432L;
	@Getter
	private String id;
	@Getter 
	private CartesianCoordinate limits;
}
