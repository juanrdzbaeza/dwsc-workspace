package dwsc.microservice.eureka.client.managegame.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dwsc.microservice.eureka.client.managegame.domain.Game;
import dwsc.microservice.eureka.client.managegame.service.GameService;

@RestController
public class GameController {
	
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Game> getGames() { 
		return gameService.getGamesFromDB();
	}
	 
	@RequestMapping(value = "/game/", method = RequestMethod.POST)
	public @ResponseBody Boolean setGame(@RequestParam("name") String n, @RequestParam("description") String d, @RequestParam("cover_url") String c) { 
		return gameService.createGameInDB(n, d, c);
	}
	
	@RequestMapping(value = "/game/", method = RequestMethod.DELETE)
	public @ResponseBody Boolean deleteGameById(@RequestParam("game_id") Integer game_id) { 
		return gameService.deleteGameInDB(game_id);
	}
	
	@RequestMapping(value = "/game/", method = RequestMethod.PUT)
	public @ResponseBody Boolean updateGameById(@RequestParam("name") String n, @RequestParam("description") String d, @RequestParam("cover_url") String c) { 
		return gameService.createGameInDB(n, d, c);
	}
	
	@RequestMapping(value = "/game/", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Game> getGame(@RequestParam("name") String name) { 
		return gameService.findGameByName(name);
	}
	
	@RequestMapping(value = "/gameid/", method = RequestMethod.GET)
	public @ResponseBody Game getGameById(@RequestParam("game_id") Integer game_id) { 
		return gameService.findGameById(game_id);
	}
	
}
