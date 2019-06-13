package dwsc.microservice.eureka.client.followings.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dwsc.microservice.eureka.client.followings.domain.Followings;

@Service
public class FollowingsServiceImpl implements FollowingsService{
	
	private Connection connect2DB() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:33306/games_microservices";
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public ArrayList<Followings> getFollowings(){
		ArrayList<Followings> followings = new ArrayList<Followings>();
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the followings and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM followings ORDER BY dni_player");
			while(rs.next()) {
				Followings following = new Followings();
				
				following.setDni_player(rs.getString("dni_player"));
				following.setDni_follower(rs.getString("dni_follower"));
				
				followings.add(following);
			}
		} catch (SQLException e) {
			System.err.println("[FollowingsService - getFollowings] SQLException while"
					+ " querying the followings");
			System.err.println(e.getMessage());
		}
		
		return followings;
	}
	
	@Override
	public Followings getFollowing(String dni_player, String dni_follower) {
		Followings following = null;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the following and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM followings "
					+ "WHERE dni_player = '" + dni_player + "' "
					+ "AND dni_follower = '" + dni_follower+ "'");
			while(rs.next()) {
				following = new Followings();
				
				following.setDni_player(rs.getString("dni_player"));
				following.setDni_follower(rs.getString("dni_follower"));
				
			}
		} catch (SQLException e) {
			System.err.println("[FollowingsService - getFollowing] SQLException while"
					+ " querying the following");
			System.err.println(e.getMessage());
		}
		
		return following;
	}
	
	@Override
	public Followings addFollowerInDB(String dni_player, String dni_follower) {
		Followings followings = new Followings();
		followings.setDni_player(dni_player);
		followings.setDni_follower(dni_follower);
		int result = 0;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Creates the new following relationship in the db
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate("INSERT INTO followings (dni_player, dni_follower)"
					+ " VALUES ('" + dni_player + "', '" + dni_follower + "')");
		} catch (SQLException e) {
			System.err.println("[FollowingsService - addFollowerInDB] SQLException while"
					+ " creating the following relationship in the database");
			System.err.println(e.getMessage());
		}
		
		if(result == 1) {
			return followings;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean deleteFollowerFromDB(String dni_player, String dni_follower) {
		boolean deleted = false;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Deletes the following relationship specified from the db
		try {
			Statement st = conn.createStatement();
			int result = st.executeUpdate("DELETE FROM followings WHERE dni_player = '" 
			+ dni_player + "' AND dni_follower = '" + dni_follower + "'");
			if (result != 0) {
				deleted = true;
			}
		} catch (SQLException e) {
			System.err.println("[FollowingsService - deleteFollowerFromDB] SQLException while"
					+ " deleting the following relationship in the database");
			System.err.println(e.getMessage());
		}
		
		return deleted;
	}

}
