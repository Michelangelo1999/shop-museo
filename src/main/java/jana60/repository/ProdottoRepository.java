package jana60.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto, Integer> {
	
	//List<Prodotto>  findByGetQuantitaMagazzinoGreaterThan(int zero); //OrderByQuantitaAsc();
	//List<Prodotto> findAllOrderByQuantitaDesc();

}
