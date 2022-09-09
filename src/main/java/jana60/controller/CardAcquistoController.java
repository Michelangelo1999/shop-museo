package jana60.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Acquisto;
import jana60.model.CardAcquisto;
import jana60.repository.AcquistoRepo;
import jana60.repository.CardRepo;
import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/card")
public class CardAcquistoController {

	@Autowired
	private CardRepo repoCard;

	@Autowired
	private AcquistoRepo repoAc;

	@Autowired
	private ProdottoRepo repo;

	@GetMapping
	public String card(Model model) {
		model.addAttribute("cardAcList", repoCard.findAll());
		return "redirect:/acquisto/detail/{id}";
	}

	@GetMapping("/add/{id}")
	public String rifornimetoForm(Model model, @PathVariable("id") Integer acquistoId) {
		CardAcquisto card = new CardAcquisto();
		Acquisto acquisto = repoAc.findById(acquistoId).get();
		card.setAcquisto(acquisto);
		model.addAttribute("cardAcquisto", card);
		model.addAttribute("prodottiList", repo.findAll());
		model.addAttribute("acList", repoAc.findAll());

		return "/cardAcquisto/addC";

	}

	@PostMapping("/add")
	public String save(@Valid @ModelAttribute("cardAcquisto") CardAcquisto formCardAcquisto, BindingResult br,
			Model model) {
		// testo se ci sono errori di validazione
		boolean hasErrors = br.hasErrors();

		if (hasErrors) {
			// se ci sono errori non salvo l'assortimento su database ma ritorno alla form
			// precaricata
			model.addAttribute("prodottiList", repo.findAll());

			return "/cardAcquisto/addC";
		} else {
			// se non ci sono errori salvo l'assortimento che arriva dalla form

			repoCard.save(formCardAcquisto);

			return "redirect:/card/add/" + formCardAcquisto.getAcquisto().getId();

			// return "redirect:/assortimento"; // non cercare un template, ma fai la HTTP
			// redirect a quel path
		}
	}
}
