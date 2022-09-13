package jana60.controller;

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

import jana60.model.PrenotazioneVisita;
import jana60.model.Visita;
import jana60.repository.PrenotazioneVisitaRepository;
import jana60.repository.VisitaRepository;

@Controller
@RequestMapping("/prenotazionevisita")
public class PrenotazioneVisitaController {

	@Autowired
	private PrenotazioneVisitaRepository repo;
	
	@Autowired
	private VisitaRepository repoVisita;
	
	@GetMapping ("/nuova/{id}")
	public String aggiungiPrenotazione (Model model, @PathVariable("id") Integer visitaId) {
		PrenotazioneVisita nuovaPrenotazione = new PrenotazioneVisita();
		Visita visitaDaPrenotare = repoVisita.findById(visitaId).get();
		nuovaPrenotazione.setVisita(visitaDaPrenotare);
		model.addAttribute("aggiungiPrenotazione", nuovaPrenotazione);
		return "prenotazionevisite/aggiungiprenotazionevisite";
	}
	@PostMapping ("/salva")
	public String salvaPrenotazione (@Valid @ModelAttribute("aggiungiPrenotazione")PrenotazioneVisita prenotazioneAggiunta, BindingResult br) {
		if (br.hasErrors()) {
			return "prenotazionevisite/aggiungiprenotazionevisite";
		}else {
			repo.save(prenotazioneAggiunta);
			return "redirect:/";
		}
	}
}
