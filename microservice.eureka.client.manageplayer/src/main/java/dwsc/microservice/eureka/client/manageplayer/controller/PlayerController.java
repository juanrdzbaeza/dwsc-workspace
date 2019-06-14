package dwsc.microservice.eureka.client.manageplayer.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dwsc.microservice.eureka.client.manageplayer.domain.Players;
import dwsc.microservice.eureka.client.manageplayer.feign.clients.DataValidatorClient;
import dwsc.microservice.eureka.client.manageplayer.service.PlayerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestController
public class PlayerController {
	@Autowired
	private PlayerService playerService;
	
	/*@Autowired
	private DiscoveryClient discoveryClient;*/
	@Autowired
	private DataValidatorClient dataValidatorClient;
	
	// Mapping the path in the microservice to get all players
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> getPlayers() {
		ArrayList<Players> players = playerService.getPlayersFromDB();
		if(players.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(players);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(players);
		}
	}
	
	// Mapping the path in the microservice to get a player by its DNI
	@RequestMapping(value = "/dni/", method = RequestMethod.GET)
	public ResponseEntity<Players> getPlayerByDNI(@RequestParam("dni") String dni) {
		Players player = playerService.getPlayerByDNIFromDB(dni);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(player);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(player);
		}
	}
	
	// Mapping the path in the microservice to get players by their name
	@RequestMapping(value = "/name/", method = RequestMethod.GET)
	public ResponseEntity<Object> getPlayerByName(@RequestParam("name") String name) {
		ArrayList<Players> players = playerService.getPlayerByNameFromDB(name);
		if(players.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(players);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(players);
		}
	}
	
	// Mapping the path in the microservice to get players by their surname
	@RequestMapping(value = "/surname/", method = RequestMethod.GET)
	public ResponseEntity<Object> getPlayerBySurname(
			@RequestParam("surname") String surname) {
		ArrayList<Players> players = playerService.getPlayerBySurnameFromDB(surname);
		if(players.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(players);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(players);
		}
	}
	
	// Mapping the path in the microservice to create a player with the specified properties
	@RequestMapping(value = "/player/", method = RequestMethod.POST)
	public ResponseEntity<Players> createPlayer(@RequestParam("dni") String dni,
			@RequestParam("name") String name,
			@RequestParam("surname") String surname, 
			@RequestParam("age") int age) {		
		/*Map<String, String> params = new TreeMap<String, String>();
		params.put("data", dni);
		List<ServiceInstance> serviceList = discoveryClient.getInstances(
				"SAMPLE-CLIENT-DATA_VALIDATOR");
		if(serviceList != null && serviceList.size() > 0) {
			URI uri = serviceList.get(0).getUri();
			String url = uri.toString() + "/{data}";
			if(uri != null) {
				validator = (new RestTemplate()).getForObject(url, Boolean.class, params);
			}
		}*/
		Players player = playerService.getPlayerByDNIFromDB(dni);
		if(player == null) {
			boolean validator = dataValidatorClient.validateData(dni).getBody();
			
			if(validator) {
				Players inserted = playerService.createPlayerInDB(dni, name, surname, age);
				if(inserted != null){
					return ResponseEntity.status(HttpStatus.CREATED).body(inserted);
				}
				else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(inserted);
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
	}
	
	// Mapping the path in the microservice to delete a player by its DNI
	@RequestMapping(value = "/player/", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePlayerByDNI(@RequestParam("dni") String dni) {
		boolean deleted = playerService.deletePlayerByDNIFromDB(dni);
		if(deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// Mapping the path in the microservice to update a player by its DNI and using
	// the values specified by parameters
	@RequestMapping(value = "/player/", method = RequestMethod.PUT)
	public ResponseEntity<Players> updatePlayer(@RequestParam("dni") String dni, 
			@RequestParam("name") String name,
			@RequestParam("surname") String surname, 
			@RequestParam("age") int age) {
		Players player = playerService.getPlayerByDNIFromDB(dni);
		if(player != null) {
			Players updated = playerService.updatePlayerInDB(dni, name, surname, age);
			if(updated != null){
				return ResponseEntity.status(HttpStatus.OK).body(updated);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updated);
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
