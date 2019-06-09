package dwsc.microservice.eureka.client.manageplayer.service;

import java.util.ArrayList;

import dwsc.microservice.eureka.client.manageplayer.domain.Player;

public interface PlayerService {
	
	public ArrayList<Player> getPlayersFromDB();

	public Player getPlayerByDNIFromDB(String dni);
	
	public ArrayList<Player> getPlayerByNameFromDB(String name);
	
	public ArrayList<Player> getPlayerBySurnameFromDB(String surname);
	
	public boolean createPlayerInDB(String dni, String name, String surname, int age);
	
	public boolean deletePlayerByDNIFromDB(String dni);
	
	public boolean updatePlayerInDB(String dni, String name, String surname, int age);
	
	
}
