package jana60.repository;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Prodotto;

public interface ProdottoRepo extends CrudRepository<Prodotto, Integer> {

	public Integer countByNome(String nome);
}
