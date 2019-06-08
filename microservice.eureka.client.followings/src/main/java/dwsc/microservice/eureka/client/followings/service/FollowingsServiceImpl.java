package dwsc.microservice.eureka.client.followings.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

@Service
public class FollowingsServiceImpl implements FollowingsService {
	
	/**
	 * conexion a la base de datos
	 * @return
	 */
	private Connection connect2DB() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/games_microservices"; 
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
			return conn; 
	}

	/**
	 * 
	 */
	@Override
	public boolean addFollowerInDB(String dni_player, String dni_follower) {
	    boolean created = false;
	    
	    //Connect to db
	    Connection conn = this.connect2DB();
	    
	    
	    try {
	    	Statement st = conn.createStatement();
	    	int result = st.executeUpdate(
	    		"INSERT INTO followings (dni_player, dni_follower) VALUES ('" + dni_player + "', '" + dni_follower + "')"
	    	);
	      if (result != 0) {
	    	  created = true;
	      }
	    } catch (SQLException e) {
	      System.err.println("[FollowingsService - addFollowerInDB] SQLException while"
	          + " creating the following relationship in the database");
	      System.err.println(e.getMessage());
	    }
	    
	    return created;
	}

	/**
	 * 
	 */
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
