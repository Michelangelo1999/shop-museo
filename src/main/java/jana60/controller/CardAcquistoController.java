package jana60.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		List<CardAcquisto> carrello = repoCard.findByAcquistoId(acquistoId);
		model.addAttribute("carrello", carrello);
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
		if (formCardAcquisto.getQuantita() > formCardAcquisto.getProdotto().getQuantitaDisponibile()) {
			br.addError(new FieldError("cardAcquisto", "quantita", "La quantità disponibile non è sufficiente!!"));
			hasErrors = true;
		}
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

	// controller per cancellare
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer cardAcquistoId, RedirectAttributes redAtt) {
		Optional<CardAcquisto> result = repoCard.findById(cardAcquistoId);
		if (result.isPresent()) {
			repoCard.delete(result.get());
			redAtt.addFlashAttribute("successSms",
					"Il prodotto " + result.get().getProdotto().getNome() + " è stato eliminato dal carrello");
			return "redirect:/card/add/" + result.get().getAcquisto().getId();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"L'acquisto con id " + cardAcquistoId + " non è presente nell'ordine!");
		}
	}
}
