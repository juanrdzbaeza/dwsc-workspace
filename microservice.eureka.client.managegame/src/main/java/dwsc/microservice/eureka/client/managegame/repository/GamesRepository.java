package dwsc.microservice.eureka.client.managegame.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import dwsc.microservice.eureka.client.managegame.domain.Games;

public interface GamesRepository extends CrudRepository<Games, Integer> {
	Iterable<Games> findAll();
	Optional<Games> findById(Integer id);
	Games findByName(String name);
	<S extends Games> S save(S entity);
	void deleteById(Integer id);
}
