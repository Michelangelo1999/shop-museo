package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import jana60.model.Rifornimento;

public interface RifornimentoRepo extends CrudRepository<Rifornimento, Integer> {

	List<Rifornimento> findByAssortimentoId(Integer assortimentoId);

}
