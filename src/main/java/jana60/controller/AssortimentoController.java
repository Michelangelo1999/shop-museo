package jana60.controller;

import java.time.LocalDate;
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

import jana60.model.Assortimento;
import jana60.model.Rifornimento;
import jana60.repository.AssortimentoRepo;
import jana60.repository.ProdottoRepo;
import jana60.repository.RifornimentoRepo;

@Controller
@RequestMapping("/assortimento")
public class AssortimentoController {
	@Autowired
	private AssortimentoRepo repoAss;

	@Autowired
	private ProdottoRepo repo;

	@Autowired
	private RifornimentoRepo repoRif;

	// ASSORTIMENTO
	@GetMapping
	public String assList(Model model) {
		model.addAttribute("assortimentoList", repoAss.findAll());
		model.addAttribute("rifList", repoRif.findAll());
		return "/assortimento/assortimento"; // -> il nome o path di un template che si trova in /resources/templates
	}

	@GetMapping("/add")
	public String assForm(Model model) {
		model.addAttribute("assortimento", new Assortimento());
		model.addAttribute("prodottiList", repo.findAll());
		return "/assortimento/addA";
	}

	@PostMapping("/add")
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
		if (formAssortimento.getData().isAfter(LocalDate.now())) {
			br.addError(new FieldError("formAssortimento", "data", "La data non pu?? essere futura!"));
			hasErrors = true;
		}

		if (hasErrors) {
			// se ci sono errori non salvo l'assortimento su database ma ritorno alla form
			// precaricata
			model.addAttribute("prodottiList", repo.findAll());
			return "/assortimento/addA";
		} else {
			// se non ci sono errori salvo l'assortimento che arriva dalla form

			repoAss.save(formAssortimento);

			return "redirect:/rifornimento/add/" + formAssortimento.getId();

			// return "redirect:/assortimento"; // non cercare un template, ma fai la HTTP
			// redirect a quel path
		}
	}

	// EDIT ASSORTIMENTO
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer assortimentoId, Model model) {
		Optional<Assortimento> result = repoAss.findById(assortimentoId);
		// controllo se l'assortimento con quell'id ?? presente
		if (result.isPresent()) {
			// preparo il template con al form passandogli l'assortimento trovato sul
			// database

			model.addAttribute("assortimento", result.get());
			model.addAttribute("prodottiList", repo.findAll());
			return "/assortimento/addA";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"L'assortimento con id " + assortimentoId + " non ?? presente");
		}

	}

	// DETTAGLI assortimento
	@GetMapping("/detail/{id}")
	public String assortimentoDetail(@PathVariable("id") Integer assortimentoId, Model model) {

		Optional<Assortimento> assortimento = repoAss.findById(assortimentoId);
		List<Rifornimento> rifornimento = repoRif.findByAssortimentoId(assortimentoId);
		if (assortimento.isPresent()) {
			model.addAttribute("assortimento", assortimento.get());
			model.addAttribute("rifList", rifornimento);
			return "/assortimento/detail";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"L'assortimento con id " + assortimentoId + " non ?? presente");
		}

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer assortimentoId, RedirectAttributes redAtt) {
		Optional<Assortimento> result = repoAss.findById(assortimentoId);
		if (result.isPresent()) {
			repoAss.delete(result.get());
			redAtt.addFlashAttribute("successSms", "L'assortimento vuoto ?? stato eliminato dalla lista");
			return "redirect:/assortimento";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"L'assortimento con id " + assortimentoId + " non ?? presente nell'ordine!");
		}
	}
}
