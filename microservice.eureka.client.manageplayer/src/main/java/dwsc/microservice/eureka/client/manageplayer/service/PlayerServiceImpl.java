package dwsc.microservice.eureka.client.manageplayer.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dwsc.microservice.eureka.client.manageplayer.domain.Player;

@Service
public class PlayerServiceImpl implements PlayerService{
	
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
	public ArrayList<Player> getPlayersFromDB() {
		ArrayList<Player> players = new ArrayList<Player>();
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players ORDER BY name");
			while(rs.next()) {
				Player player = new Player();
				
				player.setDni(rs.getString("dni"));
				player.setName(rs.getString("name"));
				player.setSurname(rs.getString("surname"));
				player.setAge(rs.getInt("age"));
				
				players.add(player);
			}
		} catch (SQLException e) {
			System.err.println("[PlayerService - getPlayersFromDB] SQLException while"
					+ " querying the players");
			System.err.println(e.getMessage());
		}
		
		return players;
	}
	
	@Override
	public Player getPlayerByDNIFromDB(String dni) {
		Player player = null;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players by DNI and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players WHERE "
					+ "dni = '" + dni + "' ORDER BY name");
			while(rs.next()) {
				Player p = new Player();
				
				p.setDni(rs.getString("dni"));
				p.setName(rs.getString("name"));
				p.setSurname(rs.getString("surname"));
				p.setAge(rs.getInt("age"));
				
				player = p;
			}
		} catch (SQLException e) {
			System.err.println("[PlayerService - getPlayersFromDB] SQLException while"
					+ " querying the players");
			System.err.println(e.getMessage());
		}
		
		return player;
	}
	
	@Override
	public ArrayList<Player> getPlayerByNameFromDB(String name){
		ArrayList<Player> players = new ArrayList<Player>();
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players by name and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players WHERE "
					+ "name LIKE '%" + name + "%' ORDER BY name");
			while(rs.next()) {
				Player player = new Player();
				
				player.setDni(rs.getString("dni"));
				player.setName(rs.getString("name"));
				player.setSurname(rs.getString("surname"));
				player.setAge(rs.getInt("age"));
				
				players.add(player);
			}
		} catch (SQLException e) {
			System.err.println("[PlayerService - getPlayersFromDB] SQLException while"
					+ " querying the players");
			System.err.println(e.getMessage());
		}
		
		return players;
	}
	
	@Override
	public ArrayList<Player> getPlayerBySurnameFromDB(String surname){
		ArrayList<Player> players = new ArrayList<Player>();
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players by surname and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players WHERE "
					+ "surname LIKE '%" + surname + "%' ORDER BY name");
			while(rs.next()) {
				Player player = new Player();
				
				player.setDni(rs.getString("dni"));
				player.setName(rs.getString("name"));
				player.setSurname(rs.getString("surname"));
				player.setAge(rs.getInt("age"));
				
				players.add(player);
			}
		} catch (SQLException e) {
			System.err.println("[PlayerService - getPlayersFromDB] SQLException while"
					+ " querying the players");
			System.err.println(e.getMessage());
		}
		
		return players;
	}
	
	@Override
	public boolean createPlayerInDB(String dni, String name, String surname, int age) {
		boolean created = false;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Creates the new player in the db
		try {
			Statement st = conn.createStatement();
			int result = st.executeUpdate("INSERT INTO players (dni, name, surname, age)"
					+ " VALUES ('" + dni + "', '" + name + "', '" + surname + "', " + age + ")");
			if (result != 0) {
				created = true;
			}
		} catch (SQLException e) {
			System.err.println("[PlayerService - createPlayerInDB] SQLException while"
					+ " creating the player in the database");
			System.err.println(e.getMessage());
		}
		
		return created;
	}
	
	@Override
	public boolean deletePlayerByDNIFromDB(String dni) {
		boolean deleted = false;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Deletes the player specified from the db
		try {
			Statement st = conn.createStatement();
			int result = st.executeUpdate("DELETE FROM players WHERE dni = '" + dni + "'");
			if (result != 0) {
				deleted = true;
			}
		} catch (SQLException e) {
			System.err.println("[PlayerService - deletePlayerByDNIFromDB] SQLException while"
					+ " deleting the player in the database");
			System.err.println(e.getMessage());
		}
		
		return deleted;
	}
	
	@Override
	public boolean updatePlayerInDB(String dni, String name, String surname, int age) {
		boolean updated = false;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Updates the player specified from the db
		try {
			Statement st = conn.createStatement();
			int result = st.executeUpdate("UPDATE players SET name = '" + name 
					+ "', surname = '" + surname + "', age = " + age 
					+ " WHERE dni = '" + dni + "'");
			if (result != 0) {
				updated = true;
			}
		} catch (SQLException e) {
			System.err.println("[PlayerService - updatePlayerInDB] SQLException while"
					+ " updating the player in the database");
			System.err.println(e.getMessage());
		}
		
		return updated;
	}
	
}
