package dwsc.microservice.eureka.client.managegame.domain;

public class Player {
	
	private String name;
	private String surname;
	private String age;
	private String dni;
	private Game[] games;
	private Player[] followers;
	private Player[] followings;
	
	public Player(String n, String s, String a, String d) {
		this.name	 	= n;
		this.surname 	= n;
		this.age 		= n;
		this.dni 		= n;
	}
	
}
