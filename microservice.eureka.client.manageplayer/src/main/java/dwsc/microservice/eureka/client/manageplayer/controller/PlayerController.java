package dwsc.microservice.eureka.client.manageplayer.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dwsc.microservice.eureka.client.manageplayer.domain.Player;
import dwsc.microservice.eureka.client.manageplayer.service.PlayerService;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;



@RestController
public class PlayerController {
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	// Mapping the path in the microservice to get all players
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Player> getPlayers() {
		return playerService.getPlayersFromDB();
	}
	
	// Mapping the path in the microservice to get a player by its DNI
	@RequestMapping(value = "/dni/", method = RequestMethod.GET)
	public @ResponseBody Player getPlayerByDNI(@RequestParam("dni") String dni) {
		return playerService.getPlayerByDNIFromDB(dni);
	}
	
	// Mapping the path in the microservice to get players by their name
	@RequestMapping(value = "/name/", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Player> getPlayerByName(@RequestParam("name") String name) {
		return playerService.getPlayerByNameFromDB(name);
	}
	
	// Mapping the path in the microservice to get players by their surname
	@RequestMapping(value = "/surname/", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Player> getPlayerBySurname(
			@RequestParam("surname") String surname) {
		return playerService.getPlayerBySurnameFromDB(surname);
	}
	
	// Mapping the path in the microservice to create a player with the specified properties
	// -1	=>	DNI not valid
	//  0	=>	Cannot insert the new player
	//  1	=>	Inserted player
	@RequestMapping(value = "/player/", method = RequestMethod.POST)
	public @ResponseBody int createPlayer(@RequestParam("dni") String dni,
			@RequestParam("name") String name,
			@RequestParam("surname") String surname, 
			@RequestParam("age") int age) {
		boolean validator = false;
		
		Map<String, String> params = new TreeMap<String, String>();
		params.put("data", dni);
		List<ServiceInstance> serviceList = discoveryClient.getInstances(
				"client-data_validator");
		if(serviceList != null && serviceList.size() > 0) {
			URI uri = serviceList.get(0).getUri();
			String url = uri.toString() + "/{data}";
			if(uri != null) {
				validator = (new RestTemplate()).getForObject(url, Boolean.class, params);
			}
		}
		
		if(validator) {
			boolean inserted = playerService.createPlayerInDB(dni, name, surname, age);
			if(inserted){
				return 1;
			}
			return 0;
		}
		return -1;
	}
	
	// Mapping the path in the microservice to delete a player by its DNI
	@RequestMapping(value = "/player/", method = RequestMethod.DELETE)
	public @ResponseBody boolean deletePlayerByDNI(@RequestParam("dni") String dni) {
		return playerService.deletePlayerByDNIFromDB(dni);
	}
	
	// Mapping the path in the microservice to update a player by its DNI and using
	// the values specified by parameters
	@RequestMapping(value = "/player/", method = RequestMethod.PUT)
	public @ResponseBody boolean updatePlayer(@RequestParam("dni") String dni, 
			@RequestParam("name") String name,
			@RequestParam("surname") String surname, 
			@RequestParam("age") int age) {
		return playerService.updatePlayerInDB(dni, name, surname, age);
	}

}
