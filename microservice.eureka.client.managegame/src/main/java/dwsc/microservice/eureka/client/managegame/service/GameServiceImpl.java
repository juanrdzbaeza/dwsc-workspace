package dwsc.microservice.eureka.client.managegame.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dwsc.microservice.eureka.client.managegame.domain.Game;

@Service
public class GameServiceImpl implements GameService{

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
	public ArrayList<Game> getGamesFromDB() {
		ArrayList<Game> myGames = new ArrayList<Game>();
		
		Connection conn = this.connect2DB();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM games");
			
			while (rs.next()) {
				Game g = new Game();
				
				g.setGame_id(rs.getInt("game_id"));
				g.setName(rs.getString("name"));
				g.setDescription(rs.getString("description"));
				g.setCover_url(rs.getString("cover_url"));
				
				myGames.add(g);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("[GameService - getGamesFromDB] SQLException while querying the games"); 
			System.err.println(e.getMessage());
		}
		return myGames;
	}

	/**
	 * 
	 */
	@Override
	public boolean createGameInDB(String name, String description, String cover_url) {
		boolean created = false;
	    
	    //Connect to db
	    Connection conn = this.connect2DB();
	    
	    //Query the games and add them to the list
	    try {
	      Statement st = conn.createStatement();
	      int result = st.executeUpdate("INSERT INTO games (name, description, cover_url)"
	          + " VALUES ('" + name + "', '" + description + "', '" + cover_url + "')");
	      if (result != 0) {
	        created = true;
	      }
	      st.close();
	      conn.close();
	    } catch (SQLException e) {
	      System.err.println("[GameService - createGameInDB] SQLException while"
	          + " creating the game in the database");
	      System.err.println(e.getMessage());
	    }
	    
	    return created;
	  }
	
	/**
	 * 
	 */
	@Override
	public boolean deleteGameInDB(Integer game_id) {
		boolean deleted = false;
	    
	    //Connect to db
	    Connection conn = this.connect2DB();
	    
	    try {
	    	Statement st = conn.createStatement();
		    int result = st.executeUpdate("DELETE FROM games where game_id = " + game_id);
		    if (result != 0) {
		    	  deleted = true;
		    }
		    st.close();
		    conn.close();
		} catch (SQLException e) {
			System.err.println("[GameService - deleteGameInDB] SQLException while"
		          + " deleting the game in the database");
		    System.err.println(e.getMessage());
		}
		return deleted;
	}



	
	/**
	 * 
	 */
	@Override
	public boolean updateGameInDB(Integer game_id, String n, String d, String c) {
		boolean updated = false;
		
		//Connect to db
	    Connection conn = this.connect2DB();
	    
	    try {
	    	Statement st = conn.createStatement();
	    	int result = st.executeUpdate("UPDATE games SET name = '"+n+"', description'"+d+"', cover_url'"+c+"' WHERE game_id = '" + game_id +"';");
	    	if (result != 0) {
	    		updated = true;
		      }
		      st.close();
		      conn.close();
			
		} catch (SQLException e) {
			System.err.println("[GameService - updateGameInDB] SQLException while"
			          + " updating the game in the database");
			    System.err.println(e.getMessage());
		}
		
		return updated;
	}

	
	/**
	 * 
	 */
	@Override
	public ArrayList<Game> findGameByName(String name) {
		ArrayList<Game> gamesFounds = new ArrayList<Game>();
		
		Connection conn = this.connect2DB();
		
		try {
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM games WHERE name LIKE '%"+name+"%'");
			
			while (rs.next()) {
				Game g = new Game();
				g.setGame_id(rs.getInt("game_id"));
				g.setName(rs.getString("name"));
				g.setDescription(rs.getString("description"));
				g.setCover_url(rs.getString("cover_url"));
				
				gamesFounds.add(g);
				
				rs.next();
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			System.err.println("[GameService - findGameByName] SQLException while querying a game"); 
			System.err.println(e.getMessage());
		}
		
		return gamesFounds;
	}

	
	/**
	 * 
	 */
	@Override
	public Game findGameById(Integer game_id) {
		Game game = new Game();
		
		//Connect to db
	    Connection conn = this.connect2DB();
	    
	    try {
	    	Statement st = conn.createStatement();
	    	ResultSet rs = st.executeQuery("SELECT * FROM games WHERE game_id = " + game_id);
		    
	    	while (rs.next()) {
		    	
		    	game.setGame_id(rs.getInt("game_id"));
		    	game.setName(rs.getString("name"));
		    	game.setDescription(rs.getString("description"));
		    	game.setCover_url(rs.getString("cover_url"));
		    	
		    }
		    st.close();
		    conn.close();
		} catch (SQLException e) {
			System.err.println("[GameService - findGameById] SQLException while "
		          + " deleting the game in the database");
		    System.err.println(e.getMessage());
		}
		
		return game;
	}
	



}// final clase
