package dwsc.microservice.eureka.client.followings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
														
import dwsc.microservice.eureka.client.followings.service.FollowingsService;

@RestController
public class FollowingsController {
  
  @Autowired
  private FollowingsService followingsService;
  
  
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public @ResponseBody boolean addFollower(@RequestParam("dni_player") String dni_player, @RequestParam("dni_follower") String dni_follower) {
	  return followingsService.addFollowerInDB(dni_player, dni_follower);
  }
  
  

  @RequestMapping(value = "/", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteFollower(@RequestParam("dni_player") String dni_player,
      @RequestParam("dni_follower") String dni_follower) {
    return followingsService.deleteFollowerFromDB(dni_player, dni_follower);
  }

}
