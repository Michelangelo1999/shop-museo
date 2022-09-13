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

import jana60.model.Visita;
import jana60.repository.GuidaRepository;
import jana60.repository.VisitaRepository;

@Controller
@RequestMapping("/visite")
public class VisitaController {

	@Autowired
	private VisitaRepository repo;

	@Autowired
	private GuidaRepository repoGuide;

	@GetMapping
	public String schedeVisite(Model model) {
		model.addAttribute("schedaVisita", repo.findAll());
		return "visite/listavisite";
	}

	@GetMapping("/aggiungivisita")
	public String aggiungiVisita(Model model) {
		model.addAttribute("aggiungiVisita", new Visita());
		model.addAttribute("listaGuide", repoGuide.findAll());
		return "visite/aggiungivisita";
	}

	@PostMapping("/salva")
	public String salvaVisita(@Valid @ModelAttribute("aggiungiVisita") Visita visitaAggiunta, BindingResult br) {
		if (br.hasErrors()) {
			return "visite/aggiungivisita";
		} else {
//			LocalDateTime ldt = visitaAggiunta.getDataInizio();
			repo.save(visitaAggiunta);
			return "redirect:/visite";
		}
	}

//	String str = "1986-04-08 12:30";
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//	LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

	@GetMapping("/elimina/{id}")
	public String eliminaVisita(@PathVariable("id") Integer idVisita, RedirectAttributes reAt) {
		Optional<Visita> visitaEliminata = repo.findById(idVisita);
		if (visitaEliminata.isPresent()) {
			repo.delete(visitaEliminata.get());
			return "redirect:/visite";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visita selezionata inesistente");
		}
	}

	@GetMapping("modifica/{id}")
	public String modificaVisita(@PathVariable("id") Integer idVisita, Model model) {
		Optional<Visita> visitaModificata = repo.findById(idVisita);
		if (visitaModificata.isPresent()) {
			model.addAttribute("aggiungiVisita", visitaModificata.get());
			model.addAttribute("listaGuide", repoGuide.findAll());
			return "visite/aggiungivisita";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visita selezionata inesistente");
		}
	}
}
