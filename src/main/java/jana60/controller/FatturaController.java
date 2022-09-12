package jana60.controller;

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
import jana60.model.Fattura;
import jana60.repository.AcquistoRepo;
import jana60.repository.FatturaRepo;

@Controller
@RequestMapping("/fattura")
public class FatturaController {
	
	@Autowired
	FatturaRepo repo;
	
	@Autowired
	AcquistoRepo repoAc;
	
	@GetMapping("/{id}")
	public String fattura(@PathVariable("id") Integer acquistoId, Model model) {
		Optional<Acquisto> acquistoDaFatturare = repoAc.findById(acquistoId);
		if(acquistoDaFatturare.isPresent()) {
			model.addAttribute(acquistoDaFatturare);
			Fattura fattura = new Fattura();
			Acquisto acquistoDafatturare = repoAc.findById(acquistoId).get();
			fattura.setAcquisto(acquistoDafatturare);
			model.addAttribute("fattura", fattura);
			return "acquisto/fattura";
		}
		return "/acquisto/fattura";
	}
	
	@PostMapping("/save")
	public String saveFattura(@Valid @ModelAttribute("fattura") Fattura formFattura, BindingResult br, Model model) {
		boolean hasErrors = br.hasErrors();

		if (hasErrors) {
			// se ci sono errori non salvo l'assortimento su database ma ritorno alla form
			// precaricata
			model.addAttribute("acquistiList", repoAc.findAll());

			return "/acquisto/fattura";
		} else {
			// se non ci sono errori salvo l'assortimento che arriva dalla form

			repo.save(formFattura);

			return "redirect:/acquisto";

			// return "redirect:/assortimento"; // non cercare un template, ma fai la HTTP
			// redirect a quel path
		}
	}

}
