package dwsc.microservices.eureka.client.player_games.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class PlayerGames {
	@Id
	@JoinColumn(name="dni")
	private String dni_player;
	
	@Id
	@JoinColumn(name="game_id")
	private Integer game_id;
	/*geters & seters */
	public String getDni_player() {
		return dni_player;
	}

	public void setDni_player(String dni_player) {
		this.dni_player = dni_player;
	}

	public Integer getGame_id() {
		return game_id;
	}

	public void setGame_id(Integer game_id) {
		this.game_id = game_id;
	}

}
