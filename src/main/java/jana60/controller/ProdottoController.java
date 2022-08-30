package jana60.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/")
public class ProdottoController {

	@Autowired
	private ProdottoRepo repo;

	@GetMapping
	public String prodottoList(Model model) {
		model.addAttribute("prodotto", repo.findAll());
		return "/prodotto/list";
	}
}
