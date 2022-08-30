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

import jana60.model.Acquisto;
import jana60.repository.AcquistoRepo;
import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {

	@Autowired
	private AcquistoRepo repoAc;

	@Autowired
	private ProdottoRepo repo;

	@GetMapping("/add")
	public String acForm(Model model) {
		model.addAttribute("acquisto", new Acquisto());
		model.addAttribute("prodottiList", repo.findAll());
		return "/acquisto/add";
	}

	@PostMapping("/save")
	public String doSave(@Valid @ModelAttribute Acquisto formAc, BindingResult br, Model model) {
		// se ho errori torno alla form
		if (br.hasErrors()) {
			model.addAttribute("prodottiList", repo.findAll());
			return "/acquisto/add";
		} else { // se non ho errori salvo il borrowing
			repoAc.save(formAc);
			return "redirect:/detail/" + formAc.getProdotto().getId();
		}
	}
}
