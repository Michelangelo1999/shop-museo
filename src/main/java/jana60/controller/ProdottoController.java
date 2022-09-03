package jana60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Prodotto;
import jana60.repository.ProdottoRepository;

@Controller
@RequestMapping("/prodotto")
public class ProdottoController {
	
	@Autowired
	ProdottoRepository prodottoRepo;

	@GetMapping("/vetrina")
	public String vetrina(Model model) {
		List<Prodotto> listaProdotti = prodottoRepo.findAllByOrderByNome();
		model.addAttribute("listaProdotti", listaProdotti);
		
		return "/prodotto/vetrina";
	}
}
