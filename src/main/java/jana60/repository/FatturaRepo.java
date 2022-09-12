package jana60.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Acquisto;
import jana60.model.Fattura;

public interface FatturaRepo extends CrudRepository<Fattura, Integer> {

	Fattura findByAcquisto(Optional<Acquisto> acquisto);

}
