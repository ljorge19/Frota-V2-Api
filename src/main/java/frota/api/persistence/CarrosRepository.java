package frota.api.persistence;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import frota.api.model.Carros;

public interface CarrosRepository extends MongoRepository<Carros, String> { //CrudRepository<User, String> {

	public Optional<Carros> findById(String id);
	
}
