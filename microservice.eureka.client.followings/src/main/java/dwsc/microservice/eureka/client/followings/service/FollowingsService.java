package dwsc.microservice.eureka.client.followings.service;


public interface FollowingsService {
	
	public boolean addFollowerInDB(String dni_player, String dni_follower);
	  
	public boolean deleteFollowerFromDB(String dni_player, String dni_follower);

}
