package dwsc.microservices.eureka.client.player_games.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dwsc.microservices.eureka.client.player_games.domain.PlayerGames;

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
	public ArrayList<PlayerGames> getPlayerGames(){
		ArrayList<PlayerGames> playerGames = new ArrayList<PlayerGames>();
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players' games and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM player_games ORDER BY dni_player");
			while(rs.next()) {
				PlayerGames playerGame = new PlayerGames();
				
				playerGame.setDni_player(rs.getString("dni_player"));
				playerGame.setGame_id(rs.getInt("game_id"));
				
				playerGames.add(playerGame);
			}
		} catch (SQLException e) {
			System.err.println("[PlayerGamesService - getPlayerGames] SQLException while"
					+ " querying the players' games");
			System.err.println(e.getMessage());
		}
		
		return playerGames;
	}
	
	@Override
	public PlayerGames getPlayerGame(String dni_player, int game_id) {
		PlayerGames playerGame = null;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the player's game specified
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM player_games "
					+ "WHERE dni_player = '" + dni_player + "' "
					+ "AND game_id = " + game_id);
			while(rs.next()) {
				playerGame = new PlayerGames();
				
				playerGame.setDni_player(rs.getString("dni_player"));
				playerGame.setGame_id(rs.getInt("game_id"));
				
			}
		} catch (SQLException e) {
			System.err.println("[PlayerGamesService - getPlayerGame] SQLException while"
					+ " querying the player's game specified");
			System.err.println(e.getMessage());
		}
		
		return playerGame;
	}
	
	@Override
	public PlayerGames addPlayerGameInDB(String dni_player, int game_id) {
		PlayerGames playerGames = new PlayerGames();
		playerGames.setDni_player(dni_player);
		playerGames.setGame_id(game_id);
		int result = 0;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Creates the new player-game relationship in the db
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate("INSERT INTO player_games (dni_player, game_id)"
					+ " VALUES ('" + dni_player + "', " + game_id + ")");
		} catch (SQLException e) {
			System.err.println("[PlayerGamesService - addPlayerGameInDB] SQLException while"
					+ " creating the player-game relationship in the database");
			System.err.println(e.getMessage());
		}
		
		if(result == 1) {
			return playerGames;
		}
		else {
			return null;
		}
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