package dwsc.microservices.eureka.client.player_games.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dwsc.microservices.eureka.client.player_games.service.PlayerGamesService;
import dwsc.microservices.eureka.client.player_games.domain.*;

@RestController
public class PlayerGamesController {
	@Autowired
	private PlayerGamesService playerGamesService;
	
	// Mapping the path in the microservice to get all players' games
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> getPlayerGames(){
		ArrayList<PlayerGames> playerGames = playerGamesService.getPlayerGames();
		if(playerGames.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(playerGames);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(playerGames);
		}
	}
	
	// Mapping the path in the microservice to get all players' games
	@RequestMapping(value = "/playergame/", method = RequestMethod.GET)
	public ResponseEntity<PlayerGames> getPlayerGame(@RequestParam("dni_player") String dni_player,
			@RequestParam("game_id") int game_id){
		PlayerGames playerGame = playerGamesService.getPlayerGame(dni_player, game_id);
		if(playerGame == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(playerGame);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(playerGame);
		}
	}
	
	// Mapping the path in the microservice to create a player-game relationship
	// with the specified player and game
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<PlayerGames> addPlayerGame(@RequestParam("dni_player") String dni_player,
			@RequestParam("game_id") int game_id) {
		PlayerGames playerGame = playerGamesService.getPlayerGame(dni_player, game_id);
		if(playerGame == null) {
			PlayerGames added = playerGamesService.addPlayerGameInDB(dni_player, game_id);
			if(added != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(added);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(added);
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
	}
	
	// Mapping the path in the microservice to delete a player-game relationship
	// between the specified player and game
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePlayerGame(@RequestParam("dni_player") String dni_player,
			@RequestParam("game_id") int game_id) {
		boolean deleted = playerGamesService.deletePlayerGameFromDB(dni_player, game_id);
		if(deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}