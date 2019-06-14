package dwsc.microservice.eureka.client.followings.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Followings {
	@Id
	@JoinColumn(name="dni")
	private String dni_player;
	
	@Id
	@JoinColumn(name="dni")
	private String dni_follower;
/* getters & setters */
	public String getDni_player() {
		return dni_player;
	}

	public void setDni_player(String dni_player) {
		this.dni_player = dni_player;
	}

	public String getDni_follower() {
		return dni_follower;
	}

	public void setDni_follower(String dni_follower) {
		this.dni_follower = dni_follower;
	}
}
