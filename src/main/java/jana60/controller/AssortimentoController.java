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

import jana60.model.Assortimento;
import jana60.repository.AssortimentoRepo;
import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/assortimento")
public class AssortimentoController {
	@Autowired
	private AssortimentoRepo repoAss;

	@Autowired
	private ProdottoRepo repo;

	// ASSORTIMENTO
	@GetMapping
	public String assList(Model model) {
		model.addAttribute("assortimento", repoAss.findAll());
		return "/assortimento/assortimento"; // -> il nome o path di un template che si trova in /resources/templates
	}

	@GetMapping("/add")
	public String assForm(Model model) {
		model.addAttribute("assortimento", new Assortimento());
		model.addAttribute("prodottiList", repo.findAll());
		return "/assortimento/add";
	}

	@PostMapping("/add")
	public String save(@Valid @ModelAttribute Assortimento formAss, BindingResult br, Model model) {
		// se ho errori torno alla form
		if (br.hasErrors()) {
			// passo al model la lista degli utenti
			model.addAttribute("prodottiList", repo.findAll());
			return "/assortimento/add";
		} else { // se non ho errori salvo il borrowing
			repoAss.save(formAss);
			return "redirect:/assortimento/assortimento";
		}

	}
}
