package dwsc.microservices.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dwsc.microservices.eureka.client.service.PlayerGamesService;

@RestController
public class PlayerGamesController {
	
	@Autowired
	private PlayerGamesService playerGamesService;
	
	// Mapping the path in the microservice to create a player-game relationship
	// with the specified player and game
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody boolean addFollower(@RequestParam("dni_player") String dni_player,
			@RequestParam("game_id") int game_id) {
		return playerGamesService.addPlayerGameInDB(dni_player, game_id);
	}
	
	// Mapping the path in the microservice to delete a player-game relationship
	// between the specified player and game
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public @ResponseBody boolean deleteFollower(@RequestParam("dni_player") String dni_player,
			@RequestParam("game_id") int game_id) {
		return playerGamesService.deletePlayerGameFromDB(dni_player, game_id);
	}
	
}
