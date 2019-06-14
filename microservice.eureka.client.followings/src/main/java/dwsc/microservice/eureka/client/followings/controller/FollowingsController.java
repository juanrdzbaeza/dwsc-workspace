package dwsc.microservice.eureka.client.followings.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dwsc.microservice.eureka.client.followings.domain.Followings;
import dwsc.microservice.eureka.client.followings.service.FollowingsService;

@RestController
public class FollowingsController {
	@Autowired
	private FollowingsService followingsService;
	
	// Mapping the path in the microservice to get following relationships
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> getFollowings(){
		ArrayList<Followings> followings = followingsService.getFollowings();
		if(followings.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(followings);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(followings);
		}
	}
	
	// Mapping the path in the microservice to get a following relationship
	// with the specified players
	@RequestMapping(value = "/following", method = RequestMethod.GET)
	public ResponseEntity<Followings> getFollowing(@RequestParam("dni_player") String dni_player,
			@RequestParam("dni_follower") String dni_follower) {
		Followings following = followingsService.getFollowing(dni_player, dni_follower);
		if(following == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(following);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(following);
		}
	}
	
	// Mapping the path in the microservice to create a following relationship
	// with the specified players
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Followings> addFollower(@RequestParam("dni_player") String dni_player,
			@RequestParam("dni_follower") String dni_follower) {
		Followings following = followingsService.getFollowing(dni_player, dni_follower);
		if(following == null) {
			Followings added = followingsService.addFollowerInDB(dni_player, dni_follower);
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
	
	// Mapping the path in the microservice to delete a following relationship
	// between the specified players
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFollower(@RequestParam("dni_player") String dni_player,
			@RequestParam("dni_follower") String dni_follower) {
		boolean deleted = followingsService.deleteFollowerFromDB(dni_player, dni_follower);
		if(deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
