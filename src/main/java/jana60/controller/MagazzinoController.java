package jana60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Prodotto;
import jana60.repository.ProdottoRepo;
import jana60.service.ProdottoService;

@Controller
@RequestMapping("/magazzino")
public class MagazzinoController {

	@Autowired
	ProdottoRepo prodottiRepo;

	@Autowired
	ProdottoService service;

	@GetMapping
	public String magazzino(Model model) {

		List<Prodotto> prodottiPresenti = service.getProdottiPresenti();

		model.addAttribute("prodotti", prodottiPresenti);

		return "/magazzino/magazzino";
	}

}
