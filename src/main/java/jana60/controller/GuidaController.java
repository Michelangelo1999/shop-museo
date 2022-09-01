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

import jana60.model.Guida;
import jana60.repository.GuidaRepository;
import jana60.repository.VisitaRepository;

@Controller
@RequestMapping("/guide")
public class GuidaController {

	@Autowired
	private GuidaRepository repo;
	
	@Autowired
	private VisitaRepository repoVisite;
	
	@GetMapping
	public String schedeGuide(Model model) {
		model.addAttribute("schedaGuida", repo.findAll());
		return "guide/listaguide";
	}
	
	@GetMapping("/aggiungiguida")
	public String aggiungiGuida(Model model) {
		model.addAttribute("aggiungiGuida", new Guida());
		model.addAttribute("listaVisite", repoVisite.findAll());
		return "guide/aggiungiguida";
	}
	
	@PostMapping("/salva")
	public String salvaGuida (@Valid @ModelAttribute("aggiungiGuida")Guida guidaAggiunta, BindingResult br) {
		if (br.hasErrors()) {
			return "guide/aggiungiguida";
		}else {
			repo.save(guidaAggiunta);
			return "redirect:/guide";
		}
	}
	
	@GetMapping ("elimina/{id}")
	public String eliminaGuida (@PathVariable ("id") Integer idGuida, RedirectAttributes reAt) {
		Optional<Guida> guidaEliminata = repo.findById(idGuida);
		if (guidaEliminata.isPresent()) {
			repo.delete(guidaEliminata.get());
			return "redirect:/";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
			  "Guida selezionata inesistente");
		}
	}
	
	@GetMapping ("modifica/{id}")
	public String modificaGuida (@PathVariable ("id") Integer idGuida,Model model) {
		Optional <Guida> guidaModificata = repo.findById(idGuida);
		if (guidaModificata.isPresent()) {
			model.addAttribute("modificaGuida",guidaModificata.get());
			return "guide/aggiungiguida";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Guida selezionata inesistente");
		}
	}
}
