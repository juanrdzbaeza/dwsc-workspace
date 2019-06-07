package dwsc.microservice.eureka.client.managegame.service;


import java.util.ArrayList;

import dwsc.microservice.eureka.client.managegame.domain.*;

public interface GameService {
	
	public ArrayList<Game> getGamesFromDB();
	
	public boolean createGameInDB(String n, String d, String c);
	
	public boolean updateGameInDB(Integer game_id, String n, String d, String c);

	public boolean deleteGameInDB(Integer game_id);
	
	public ArrayList<Game> findGameByName(String name);
	
	public Game findGameById(Integer game_id);
}
