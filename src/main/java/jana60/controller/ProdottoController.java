package jana60.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Prodotto;
import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/prodotto")
public class ProdottoController {

	@Autowired
	private ProdottoRepo repo;

	@GetMapping
	public String prodottoList(Model model) {
		model.addAttribute("prodotto", repo.findAll());
		model.addAttribute("newProdotto", new Prodotto());
		return "/prodotto/prodotto"; // -> il nome o path di un template che si trova in /resources/templates
	}
}