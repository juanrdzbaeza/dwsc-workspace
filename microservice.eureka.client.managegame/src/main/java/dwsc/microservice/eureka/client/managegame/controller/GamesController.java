package dwsc.microservice.eureka.client.managegame.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dwsc.microservice.eureka.client.managegame.domain.Games;
import dwsc.microservice.eureka.client.managegame.repository.GamesRepository;


@RestController
public class GamesController {
	@Autowired
	GamesRepository gameRepository;			
	
	// Mapping the path in the microservice to get all games
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public ResponseEntity<Object> findGames(){
		Iterable<Games> games = gameRepository.findAll();
		if(games != null) {
			return ResponseEntity.status(HttpStatus.OK).body(games);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// Mapping the path in the microservice to get games by their id
	@RequestMapping(value = "/games/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findGameById(@PathVariable Integer id){
		Optional<Games> game = gameRepository.findById(id);
		if(game.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(game);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// Mapping the path in the microservice to get games by their name
	@RequestMapping(value = "/games/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<Object> findGameByName(@PathVariable String name){
		String finalName = name.replace("+", " ");
		Games game = gameRepository.findByName(finalName);
		if(game != null) {
			return ResponseEntity.status(HttpStatus.OK).body(game);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// Mapping the path in the microservice to add a game
	@RequestMapping(value = "/games/add", method = RequestMethod.POST)
	public ResponseEntity<Object> insertGame(@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("cover_url") String cover_url){
		Games existing_game = gameRepository.findByName(name);
		if(existing_game != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		else {
			Games game = new Games();
			game.setName(name);
			game.setDescription(description);
			game.setCover_url(cover_url);
			game = gameRepository.save(game);
			return ResponseEntity.status(HttpStatus.CREATED).body(game);
		}
	}
	
	// Mapping the path in the microservice to delete a game
	@RequestMapping(value = "/games/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteGame(@PathVariable Integer id){
		Optional<Games> existing_game = gameRepository.findById(id);
		if(existing_game.isPresent()) {
			gameRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(existing_game);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// Mapping the path in the microservice to update a game
	@RequestMapping(value = "/games/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateGame(@RequestParam("game_id") Integer game_id,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("cover_url") String cover_url){
		Optional<Games> existing_game = gameRepository.findById(game_id);
		if(existing_game.isPresent()) {
			Games game = new Games();
			game.setGame_id(game_id);
			game.setName(name);
			game.setDescription(description);
			game.setCover_url(cover_url);
			game = gameRepository.save(game);
			return ResponseEntity.status(HttpStatus.OK).body(game);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
