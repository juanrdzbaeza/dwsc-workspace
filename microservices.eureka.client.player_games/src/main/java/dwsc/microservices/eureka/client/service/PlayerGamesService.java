package dwsc.microservices.eureka.client.service;

public interface PlayerGamesService {

	public boolean addPlayerGameInDB(String dni_player, int game_id);
	
	public boolean deletePlayerGameFromDB(String dni_player, int game_id);

}
