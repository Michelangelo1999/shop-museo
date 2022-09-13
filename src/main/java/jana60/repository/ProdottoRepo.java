package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Prodotto;

public interface ProdottoRepo extends CrudRepository<Prodotto, Integer> {

	public Integer countByNome(String nome);

	List<Prodotto> findAllByOrderByNome();

}
