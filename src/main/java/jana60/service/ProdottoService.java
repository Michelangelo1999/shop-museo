package jana60.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jana60.model.Prodotto;
import jana60.repository.ProdottoRepository;

@Service
public class ProdottoService {

	@Autowired
	ProdottoRepository prodottoRepo;

	public List<Prodotto> getProdottiPresenti() throws NullPointerException {
		List<Prodotto> prodottiAll = (List<Prodotto>) prodottoRepo.findAll();
		List<Prodotto> prodottiPresenti = new ArrayList<Prodotto>();

		Iterator<Prodotto> prodottiAllIterator = prodottiAll.iterator();
		while (prodottiAllIterator.hasNext()) {
			Prodotto current = prodottiAllIterator.next();
			if (current.getQuantitaDisponibile() > 0) {
				prodottiPresenti.add(current);
			}
		}

		return prodottiPresenti;
	}

}
