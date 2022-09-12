package jana60.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jana60.model.Prodotto;
import jana60.repository.ProdottoRepo;

@Controller
@RequestMapping("/prodotto")

public class ProdottoController {

	@Autowired
	private ProdottoRepo repo;

	@GetMapping
	public String prodottoLista(Model m) {
		m.addAttribute("prodotto", repo.findAll());
		m.addAttribute("newProdotto", new Prodotto());
		return "/prodotto/prodotto";
	}

	@GetMapping("/crea")
	public String creaProdotto(Model m) {
		m.addAttribute("prodotto", new Prodotto());
		return "/prodotto/crea";
	}

	@PostMapping("/crea")
	public String crea(@Valid @ModelAttribute("prodotto") Prodotto creaProdotto, BindingResult br) {
		if (br.hasErrors()) {
			return "/prodotto/crea";
		} else {
			repo.save(creaProdotto);
			return "redirect:/prodotto";
		}
	}

	@GetMapping("/modifica/{id}")
	public String modificaProdotto(@PathVariable("id") Integer prodottoId, Model model) {
		Optional<Prodotto> result = repo.findById(prodottoId);
		if (result.isPresent()) {
			model.addAttribute("prodotto", result.get());
			return "/prodotto/crea";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il prodotto " + prodottoId + " non è presente");
		}
	}

	@GetMapping("/elimina/{id}")
	public String eliminaProdotto(@PathVariable("id") Integer prodottoId, RedirectAttributes ra) {
		Optional<Prodotto> result = repo.findById(prodottoId);
		if (result.isPresent()) {
			repo.delete(result.get());
			ra.addFlashAttribute("successMessage", "Il prodotto " + result.get().getNome() + " è stato eliminato!");
			return "redirect:/prodotto";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il prodotto " + prodottoId + " non è presente");
		}
	}

	@GetMapping("/vetrina")
	public String vetrinaProdotto(Model m) {
		m.addAttribute("prodotto", repo.findAll());
		return "/prodotto/vetrina";
	}

}
