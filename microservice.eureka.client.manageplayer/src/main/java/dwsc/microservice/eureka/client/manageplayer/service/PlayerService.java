package dwsc.microservice.eureka.client.manageplayer.service;

import java.util.ArrayList;
import dwsc.microservice.eureka.client.manageplayer.domain.Players;

public interface PlayerService {
	public ArrayList<Players> getPlayersFromDB();
	public Players getPlayerByDNIFromDB(String dni);
	public ArrayList<Players> getPlayerByNameFromDB(String name);
	public ArrayList<Players> getPlayerBySurnameFromDB(String surname);
	public Players createPlayerInDB(String dni, String name, String surname, int age);
	public boolean deletePlayerByDNIFromDB(String dni);
	public Players updatePlayerInDB(String dni, String name, String surname, int age);
}
