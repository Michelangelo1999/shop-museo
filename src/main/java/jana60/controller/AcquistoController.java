package jana60.controller;

import java.time.LocalDate;
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
import jana60.repository.AcquistoRepo;
import jana60.repository.CardRepo;
import jana60.repository.FatturaRepo;
import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {

	@Autowired
	private AcquistoRepo repoAc;

	@Autowired
	private ProdottoRepo repo;

	@Autowired
	private CardRepo repoCard;

	@Autowired
	private FatturaRepo repoFattura;

	@GetMapping
	public String assList(Model model) {
		model.addAttribute("acquistoList", repoAc.findAll());
		return "/acquisto/acquisto"; // -> il nome o path di un template che si trova in /resources/templates
	}

	@GetMapping("/add")
	public String acForm(Model model) {
		model.addAttribute("acquisto", new Acquisto());
		model.addAttribute("prodottiList", repo.findAll());

		return "/acquisto/add";
	}

	@PostMapping("/save")
	public String doSave(@Valid @ModelAttribute("acquisto") Acquisto formAc, BindingResult br, Model model) {
		boolean hasErrors = br.hasErrors();
		boolean validateData = true;
		if (formAc.getId() != null) { // sono in edit non in create
			Acquisto acBeforeUpdate = repoAc.findById(formAc.getId()).get();
			if (acBeforeUpdate.getData() == (formAc.getData())) {
				validateData = false;
			}
		}
		if (formAc.getData().isAfter(LocalDate.now())) {
			br.addError(new FieldError("formAc", "data", "La data non pu?? essere futura!"));
			hasErrors = true;
		}
		// se ho errori torno alla form
		if (hasErrors) {
			// se ci sono errori non salvo l'assortimento su database ma ritorno alla form
			// precaricata
			model.addAttribute("prodottiList", repo.findAll());

			return "/acquisto/add";
		} else {
			// se non ci sono errori salvo il book che arriva dalla form

			repoAc.save(formAc);

			return "redirect:/card/add/" + formAc.getId();
		}
	}

	// EDIT ACQUISTO
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer acquistoId, Model model) {
		Optional<Acquisto> result = repoAc.findById(acquistoId);

		// controllo se l'assortimento con quell'id ?? presente
		if (result.isPresent()) {
			// preparo il template con al form passandogli l'assortimento trovato sul
			// database

			model.addAttribute("acquisto", result.get());
			model.addAttribute("prodottiList", repo.findAll());

			return "/acquisto/add";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"L'acquisto con id " + acquistoId + " non ?? presente");
		}

	}

	// DETTAGLI ACQUISTO
	@GetMapping("/detail/{id}")
	public String acquistoDetail(@PathVariable("id") Integer acquistoId, Model model) {

		Optional<Acquisto> acquisto = repoAc.findById(acquistoId);
		// List<CardAcquisto> cardAc = repoCard.findByAcquistoId(acquistoId);
		if (acquisto.isPresent()) {
			model.addAttribute("acquisto", acquisto.get());

			return "/acquisto/detail";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"L'acquisto con id " + acquistoId + " non ?? presente");
		}

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer acquistoId, RedirectAttributes redAtt) {
		Optional<Acquisto> result = repoAc.findById(acquistoId);
		if (result.isPresent()) {
			repoAc.delete(result.get());
			redAtt.addFlashAttribute("successSms", "L'assortimento vuoto ?? stato eliminato dalla lista");
			return "redirect:/acquisto";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"L'assortimento con id " + acquistoId + " non ?? presente nell'ordine!");
		}
	}
}
