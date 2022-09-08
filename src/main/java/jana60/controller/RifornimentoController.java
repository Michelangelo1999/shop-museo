package jana60.controller;

import java.util.Optional;

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
import jana60.model.Rifornimento;
import jana60.repository.AssortimentoRepo;
import jana60.repository.ProdottoRepo;
import jana60.repository.RifornimentoRepo;

@Controller
@RequestMapping("/rifornimento")
public class RifornimentoController {

	// REPOSITORY
	@Autowired
	private RifornimentoRepo repoRif;

	@Autowired
	private ProdottoRepo repo;

	@Autowired
	private AssortimentoRepo repoAss;

	@GetMapping
	public String assList(Model model) {
		model.addAttribute("rifList", repoRif.findAll());
		return "redirect:/assortimento/detail/{id}";
	}

	@GetMapping("/add")
	public String rifForm(Model model) {
		Rifornimento rifornimento = new Rifornimento();
		Integer id = 1;
		Optional<Assortimento> assortimento = repoAss.findById(id);
		rifornimento.setAssortimento(assortimento);
		model.addAttribute("rifornimento", rifornimento);
		model.addAttribute("prodottiList", repo.findAll());
		model.addAttribute("assList", repoAss.findAll());

		return "/rifornimento/addR";
	}

	@PostMapping("/add")
	public String save(@Valid @ModelAttribute("rifornimento") Rifornimento formRifornimento, BindingResult br,
			Model model) {
		// testo se ci sono errori di validazione
		boolean hasErrors = br.hasErrors();

		if (hasErrors) {
			// se ci sono errori non salvo l'assortimento su database ma ritorno alla form
			// precaricata
			model.addAttribute("prodottiList", repo.findAll());

			return "/rifornimento/addR";
		} else {
			// se non ci sono errori salvo l'assortimento che arriva dalla form

			repoRif.save(formRifornimento);

			return "redirect:/rifornimento/add";

			// return "redirect:/assortimento"; // non cercare un template, ma fai la HTTP
			// redirect a quel path
		}
	}
}
