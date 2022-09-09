package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.CardAcquisto;

public interface CardRepo extends CrudRepository<CardAcquisto, Integer> {
	
}
