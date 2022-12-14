package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Guida;
import jana60.model.Image;
import jana60.model.Prodotto;
import jana60.model.Visita;

public interface ImageRepository extends CrudRepository<Image, Integer> {

	public List<Image> findByProdotto(Prodotto prodotto);

	public List<Image> findByGuida(Guida guida);

	public List<Image> findByVisita(Visita visita);

}
