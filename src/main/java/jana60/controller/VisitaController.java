package jana60.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import jana60.model.Image;
import jana60.model.ImageForm;
import jana60.model.Visita;
import jana60.repository.GuidaRepository;
import jana60.repository.ImageRepository;
import jana60.repository.VisitaRepository;
import jana60.service.ImageService;

@Controller
@RequestMapping("/visite")
public class VisitaController {

	@Autowired
	private VisitaRepository repo;

	@Autowired
	private GuidaRepository repoGuide;

	@Autowired
	private ImageService service;

	@Autowired
	private ImageRepository imageRepo;

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
	public String salvaVisita(@Valid Model model, @ModelAttribute("aggiungiVisita") Visita visitaAggiunta,
			BindingResult br) {
		boolean hasErrors = br.hasErrors();
		if (visitaAggiunta.getDataInizio().isBefore(LocalDateTime.now())
				|| visitaAggiunta.getDataFine().isBefore(visitaAggiunta.getDataInizio())) {
			br.addError(new FieldError("visitaAggiunta", "dataInizio", "Inserimento data non valido!!"));
			hasErrors = true;
		}
		if (hasErrors) {
			model.addAttribute("listaGuide", repoGuide.findAll());
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

	// IMMAGINI -----------

	// creo la view con la vista delle immagini collegate a un prodotto e la form
	// per aggiungerne una

	@GetMapping("/{visitaId}")
	public String visitaImage(@PathVariable("visitaId") Integer visitaId, Model model) {

		// questa è la lista delle immagini per quella visita
		List<Image> images = service.getImageByVisitaId(visitaId);

		// chiedo al service di istanziare una imgForm per quel prodotto, nel caso
		// voglia aggiungere una img nuova
		ImageForm imageForm = service.createImageFormVisita(visitaId);

		model.addAttribute("imageList", images);
		model.addAttribute("imageForm", imageForm);

		return "/visite/detail";
	}

	// controller che riceve il post della form e salva sul db l'immagine; prende in
	// IN l'imageform che va convertito prima del salvataggio tramite il service
	@PostMapping("/save")
	public String saveImage(@ModelAttribute("imageForm") ImageForm imageForm) {
		try {
			Image savedImage = service.createImageVisita(imageForm);
			return "redirect:/visite/" + savedImage.getVisita().getId();
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
		}
	}

	@RequestMapping(value = "/{imageId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageContentV(@PathVariable("imageId") Integer imageId) {
		// recupero il content nel database
		byte[] content = service.getImageContentV(imageId);

		// preparo gli headers della response con il tipo di contenuto
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		// ritorno il conteuto di headers e lo stato http (guarda ispeziona elemento)
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
}
