package dwsc.microservices.eureka.client.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

@Service
public class PlayerGamesServiceImpl implements PlayerGamesService{

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
	
	@Override
	public boolean addPlayerGameInDB(String dni_player, int game_id) {
		boolean created = false;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Creates the new player-game relationship in the db
		try {
			Statement st = conn.createStatement();
			int result = st.executeUpdate("INSERT INTO player_games (dni_player, game_id)"
					+ " VALUES ('" + dni_player + "', " + game_id + ")");
			if (result != 0) {
				created = true;
			}
		} catch (SQLException e) {
			System.err.println("[PlayerGamesService - addPlayerGameInDB] SQLException while"
					+ " creating the player-game relationship in the database");
			System.err.println(e.getMessage());
		}
		
		return created;
	}

	@Override
	public boolean deletePlayerGameFromDB(String dni_player, int game_id) {
		boolean deleted = false;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Deletes the player-game relationship specified from the db
		try {
			Statement st = conn.createStatement();
			int result = st.executeUpdate("DELETE FROM player_games WHERE dni_player = '" 
			+ dni_player + "' AND game_id = " + game_id);
			if (result != 0) {
				deleted = true;
			}
		} catch (SQLException e) {
			System.err.println("[PlayerGamesService - deletePlayerGameFromDB] SQLException while"
					+ " deleting the player-game relationship in the database");
			System.err.println(e.getMessage());
		}
		
		return deleted;
	}
	
}
