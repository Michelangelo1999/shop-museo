package jana60.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Acquisto;

public interface AcquistoRepo extends CrudRepository<Acquisto, Integer> {

	List<Acquisto> findByIdOrderByIdAsc(Integer acquistoId);

	

}
