package jana60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Prodotto;
import jana60.repository.AcquistoRepository;
import jana60.repository.AssortimentoRepository;
import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/magazzino")
public class MagazzinoController {

	@Autowired
	ProdottoRepo prodottiRepo;

	@Autowired
	AcquistoRepository acquistiRepo;

	@Autowired
	AssortimentoRepository assortimentiRepo;

	@GetMapping
	public String magazzino(Model model) {

		model.addAttribute("assortimenti", assortimentiRepo.findAll());
		model.addAttribute("acquisti", acquistiRepo.findAll());

		int zero = 0;

		List<Prodotto> prodottiPresenti = (List<Prodotto>) prodottiRepo.findAll(); // OrderByQuantitaAsc();

		if (prodottiPresenti.isEmpty()) {
			model.addAttribute("prodottiPresenti", "Non ci sono prodotti presenti in magazzino");
		} else {
			model.addAttribute("prodottiPresenti", prodottiPresenti);
		}

		return "/magazzino/magazzino";
	}

}
