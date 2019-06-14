package dwsc.microservices.eureka.client.player_games.service;

import java.util.ArrayList;

import dwsc.microservices.eureka.client.player_games.domain.PlayerGames;

public interface PlayerGamesService {
	public ArrayList<PlayerGames> getPlayerGames();
	public PlayerGames getPlayerGame(String dni_player, int game_id);
	public PlayerGames addPlayerGameInDB(String dni_player, int game_id);
	public boolean deletePlayerGameFromDB(String dni_player, int game_id);
}
