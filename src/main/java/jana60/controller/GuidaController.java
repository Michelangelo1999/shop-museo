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
import jana60.repository.VisitaRepository;
import jana60.service.GuidaImageService;
import jana60.service.ImageService;

@Controller
@RequestMapping("/")
public class GuidaController {

	@Autowired
	GuidaRepository guideRepo;

	@Autowired
	GuidaImageService service;

	@GetMapping
	public String guide(Model model) {
		
		model.addAttribute("guide", guideRepo.findAll());

		return "/guida/list";
	}

	@GetMapping("/detail/{id}")
	public String guidaDetail(@PathVariable("id") Integer guidaId, Model model) {
		Optional<Guida> guida = guideRepo.findById(guidaId);
		if (guida.isPresent()) {
			model.addAttribute("guida", guida.get());
			model.addAttribute("imageList", service.getImageByGuidaId(guidaId));
			model.addAttribute("imageForm", service.createGuidaImageForm(guidaId));

			return "/guida/detail";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guida with id " + guidaId + " is not present");
		}
	}

	// parte di controller dedicato alle immagini delle guide
	@GetMapping("/{guidaId}")
	public String prodottoImage(@PathVariable("guidaId") Integer guidaId, Model model) {

		// questa è la lista delle immagini per quel prodotto
		List<Image> images = service.getImageByGuidaId(guidaId);

		// chiedo al service di istanziare una imgForm per quel prodotto, nel caso
		// voglia aggiungere una img nuova
		ImageForm imageForm = service.createGuidaImageForm(guidaId);

		model.addAttribute("imageList", images);
		model.addAttribute("imageForm", imageForm);
		model.addAttribute("guida", guideRepo.findAll());

		return "/guida/list";
	}

	// controller che riceve il post della form e salva sul db l'immagine; prende in
	// IN l'imageform che va convertito prima del salvataggio tramite il service
	@PostMapping("/save")
	public String saveImage(@ModelAttribute("imageForm") ImageForm imageForm) {
		try {
			Image savedImage = service.createGuidaImage(imageForm);
			System.out.println(savedImage.getGuida());
			return "redirect:/guida/detail/" + savedImage.getGuida().getId();
			//return "redirect:/guida/list";
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
		}
	}

	@RequestMapping(value = "/{imageId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageContent(@PathVariable("imageId") Integer imageId) {
		// recupero il content nel database
		byte[] content = service.getImageContent(imageId);

		// preparo gli headers della response con il tipo di contenuto
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		// ritorno il conteuto di headers e lo stato http (guarda ispeziona elemento)
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
}
