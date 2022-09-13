package jana60.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jana60.model.Guida;
import jana60.model.Image;
import jana60.model.ImageForm;
import jana60.repository.GuidaRepository;
import jana60.repository.ImageRepository;
import jana60.service.ImageService;

@Controller
@RequestMapping("/guide")
public class GuidaController {

	@Autowired
	private GuidaRepository repo;

	@Autowired
	private ImageService service;

	@Autowired
	private ImageRepository imageRepo;

	@GetMapping
	public String schedeGuide(Model model) {
		model.addAttribute("schedaGuida", repo.findAll());
		return "guide/listaguide";
	}

	@GetMapping("/aggiungiguida")
	public String aggiungiGuida(Model model) {
		model.addAttribute("aggiungiGuida", new Guida());
		return "guide/aggiungiguida";
	}

	@PostMapping("/salva")
	public String salvaGuida(@Valid @ModelAttribute("aggiungiGuida") Guida guidaAggiunta, BindingResult br) {
		if (br.hasErrors()) {
			return "guide/aggiungiguida";
		} else {
			repo.save(guidaAggiunta);
			return "redirect:/guide";
		}
	}

	@GetMapping("/elimina/{id}")
	public String eliminaGuida(@PathVariable("id") Integer idGuida, RedirectAttributes reAt) {
		Optional<Guida> guidaEliminata = repo.findById(idGuida);
		if (guidaEliminata.isPresent()) {
			repo.delete(guidaEliminata.get());
			return "redirect:/guide";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guida selezionata inesistente");
		}
	}

	@GetMapping("/modifica/{id}")
	public String modificaGuida(@PathVariable("id") Integer idGuida, Model model) {
		Optional<Guida> guidaModificata = repo.findById(idGuida);
		if (guidaModificata.isPresent()) {
			model.addAttribute("aggiungiGuida", guidaModificata.get()); // aggiungiGuida deve coincidere con quello dell
																		// aggiungo guida
			return "guide/aggiungiguida";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guida selezionata inesistente");
		}
	}

	// IMMAGINI -----------

	// creo la view con la vista delle immagini collegate a un prodotto e la form
	// per aggiungerne una

	@GetMapping("/{guidaId}")
	public String visitaImage(@PathVariable("guidaId") Integer guidaId, Model model) {

		// questa è la lista delle immagini per quella visita
		List<Image> images = service.getImageByGuidaId(guidaId);

		// chiedo al service di istanziare una imgForm per quel prodotto, nel caso
		// voglia aggiungere una img nuova
		ImageForm imageForm = service.createImageFormGuida(guidaId);

		model.addAttribute("imageList", images);
		model.addAttribute("imageForm", imageForm);

		return "/guide/detail";
	}

	// controller che riceve il post della form e salva sul db l'immagine; prende in
	// IN l'imageform che va convertito prima del salvataggio tramite il service
	@PostMapping("/save")
	public String saveImage(@ModelAttribute("imageForm") ImageForm imageForm) {
		try {
			Image savedImage = service.createImageGuida(imageForm);
			return "redirect:/guide/" + savedImage.getGuida().getId();
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
		}
	}

	@RequestMapping(value = "/{imageId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageContentG(@PathVariable("imageId") Integer imageId) {
		// recupero il content nel database
		byte[] content = service.getImageContentG(imageId);

		// preparo gli headers della response con il tipo di contenuto
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		// ritorno il conteuto di headers e lo stato http (guarda ispeziona elemento)
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
}
