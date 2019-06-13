package dwsc.microservice.eureka.client.followings.service;

import java.util.ArrayList;

import dwsc.microservice.eureka.client.followings.domain.Followings;

public interface FollowingsService {
	public ArrayList<Followings> getFollowings();
	
	public Followings getFollowing(String dni_player, String dni_follower);
	
	public Followings addFollowerInDB(String dni_player, String dni_follower);
	
	public boolean deleteFollowerFromDB(String dni_player, String dni_follower);

}
