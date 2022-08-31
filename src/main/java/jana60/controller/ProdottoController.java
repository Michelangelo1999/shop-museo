package jana60.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Prodotto;
import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/")
public class ProdottoController {

	@Autowired
	private ProdottoRepo repo;

	@GetMapping
	public String prodottoList(Model m) {
		m.addAttribute("prodotto", repo.findAll());
		return "/prodotto/lista";
	}

	@GetMapping("/crea")
	public String prodottoCrea(Model m) {
		m.addAttribute("prodotto", new Prodotto());
		return "/prodotto/crea";
	}

	@PostMapping("/crea")
	public String crea(@Valid @ModelAttribute("prodotto") Prodotto creaProdotto, BindingResult br) {
		if (br.hasErrors()) {
			return "/prodotto/crea";
		} else {
			repo.save(creaProdotto);
			return "redirect:/";
		}
	}
}