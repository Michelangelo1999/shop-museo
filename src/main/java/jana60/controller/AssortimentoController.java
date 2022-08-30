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
		model.addAttribute("assortimentoList", repoAss.findAll());
		return "/assortimento/assortimento"; // -> il nome o path di un template che si trova in /resources/templates
	}

	@GetMapping("/add")
	public String assForm(Model model) {
		model.addAttribute("assortimento", new Assortimento());
		model.addAttribute("prodottiList", repo.findAll());
		return "/assortimento/addA";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("assortimento") Assortimento formAssortimento, BindingResult br,
			Model model) {
		// testo se ci sono errori di validazione
		boolean hasErrors = br.hasErrors();
		boolean validateNome = true;
		if (formAssortimento.getId() != null) { // sono in edit non in create
			Assortimento assBeforeUpdate = repoAss.findById(formAssortimento.getId()).get();
			if (assBeforeUpdate.getNomeFornitore().equals(formAssortimento.getNomeFornitore())) {
				validateNome = false;
			}
		}

		if (hasErrors) {
			// se ci sono errori non salvo l'assortimento su database ma ritorno alla form
			// precaricata
			model.addAttribute("prodottiList", repo.findAll());
			return "/assortimento/addA";
		} else {
			// se non ci sono errori salvo il book che arriva dalla form
			try {
				repoAss.save(formAssortimento);
			} catch (Exception e) { // gestisco eventuali eccezioni sql
				model.addAttribute("errorMessage", "Unable to save");
				model.addAttribute("categoryList", repo.findAll());
				return "/assortimento/addA";
			}
			return "redirect:/assortimento"; // non cercare un template, ma fai la HTTP redirect a quel path
		}
	}
}
