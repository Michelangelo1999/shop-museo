package jana60.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Assortimento;

public interface AssortimentoRepo extends CrudRepository<Assortimento, Integer> {

	public Optional<Assortimento> findById(Integer id);

}
