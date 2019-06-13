package dwsc.microservice.eureka.client.manageplayer.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dwsc.microservice.eureka.client.manageplayer.domain.Players;

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
	public ArrayList<Players> getPlayersFromDB() {
		ArrayList<Players> players = new ArrayList<Players>();
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players ORDER BY name");
			while(rs.next()) {
				Players player = new Players();
				
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
	public Players getPlayerByDNIFromDB(String dni) {
		Players player = null;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players by DNI and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players WHERE "
					+ "dni = '" + dni + "' ORDER BY name");
			while(rs.next()) {
				Players p = new Players();
				
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
	public ArrayList<Players> getPlayerByNameFromDB(String name){
		ArrayList<Players> players = new ArrayList<Players>();
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players by name and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players WHERE "
					+ "name LIKE '%" + name + "%' ORDER BY name");
			while(rs.next()) {
				Players player = new Players();
				
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
	public ArrayList<Players> getPlayerBySurnameFromDB(String surname){
		ArrayList<Players> players = new ArrayList<Players>();
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Query the players by surname and add them to the list
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players WHERE "
					+ "surname LIKE '%" + surname + "%' ORDER BY name");
			while(rs.next()) {
				Players player = new Players();
				
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
	public Players createPlayerInDB(String dni, String name, String surname, int age) {
		Players player = new Players();
		player.setDni(dni);
		player.setName(name);
		player.setSurname(surname);
		player.setAge(age);
		int result = 0;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Creates the new player in the db
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate("INSERT INTO players (dni, name, surname, age)"
					+ " VALUES ('" + dni + "', '" + name + "', '" + surname + "', " + age + ")");
		} catch (SQLException e) {
			System.err.println("[PlayerService - createPlayerInDB] SQLException while"
					+ " creating the player in the database");
			System.err.println(e.getMessage());
		}
		
		if(result == 1) {
			return player;
		}
		else {
			return null;
		}
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
	public Players updatePlayerInDB(String dni, String name, String surname, int age) {
		Players player = new Players();
		player.setDni(dni);
		player.setName(name);
		player.setSurname(surname);
		player.setAge(age);
		int result = 0;
		
		//Connect to db
		Connection conn = this.connect2DB();
		
		//Updates the player specified from the db
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate("UPDATE players SET name = '" + name 
					+ "', surname = '" + surname + "', age = " + age 
					+ " WHERE dni = '" + dni + "'");
		} catch (SQLException e) {
			System.err.println("[PlayerService - updatePlayerInDB] SQLException while"
					+ " updating the player in the database");
			System.err.println(e.getMessage());
		}
		
		if(result == 1) {
			return player;
		}
		else {
			return null;
		}
	}
	
}
