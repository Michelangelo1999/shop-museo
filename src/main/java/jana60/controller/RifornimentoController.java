package jana60.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	// RIFORNIMENTO - ASSORTIMENTO
	@GetMapping("/add/{id}")
	public String rifForm(Model model,@PathVariable("id") Integer assortimentoId) {
		Rifornimento rifornimento = new Rifornimento();
		Assortimento assortimento = repoAss.findById(assortimentoId).get();
		rifornimento.setAssortimento(assortimento);
		
		List<Rifornimento> listaRifornimenti = repoRif.findByAssortimentoId(assortimentoId);
		model.addAttribute("listaRifornimenti", listaRifornimenti);
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

			return "redirect:/rifornimento/add/" + formRifornimento.getAssortimento().getId();
		}
	}
	
//	@DeleteMapping
//	public String delete() {
//		
//	}
	//controller per cancellare
		@GetMapping("/delete/{id}")
		public String delete(@PathVariable("id") Integer rifornimentoId, RedirectAttributes redAtt) {
			Optional<Rifornimento> result = repoRif.findById(rifornimentoId);
			if(result.isPresent()) {
				repoRif.delete(result.get());
				redAtt.addFlashAttribute("successSms", "Il rifornimento del prodotto " + result.get().getProdotto().getNome() + " è stato eliminato dall'assortimento");
				return "redirect:/rifornimento/add/" + result.get().getAssortimento().getId();
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il rifornimento con id " + rifornimentoId + " non è presente nell'ordine!");
			}
		}

}
