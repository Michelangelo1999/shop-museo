package jana60.controller;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Image;
import jana60.model.ImageForm;
import jana60.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private ImageService service;
	
	//creo la view con la vista delle immagini collegate a un prodotto e la form per aggiungerne una
	
	@GetMapping("/{prodottoId}")
	public String prodottoImage(@PathVariable("prodottoId") Integer prodottoId, Model model) {
		
		//questa è la lista delle immagini per quel prodotto
		List<Image> images = service.getImageByProdottoId(prodottoId);
		
		//chiedo al service di istanziare una imgForm per quel prodotto, nel caso voglia aggiungere una img nuova
		ImageForm imageForm = service.createImageForm(prodottoId);
		
		model.addAttribute("imageList", images);
		model.addAttribute("imageForm", imageForm);
		
		return "/image/list";
	}
	
	//controller che riceve il post della form e salva sul db l'immagine; prende in IN l'imageform che va convertito prima del salvataggio tramite il service
	@PostMapping("/save")
	public String saveImage(@ModelAttribute("imageForm") ImageForm imageForm) {
		try {
			Image savedImage = service.createImage(imageForm);
			return "redirect:/image/" + savedImage.getProdotto().getId();
		} catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
		}
	}
	
	@RequestMapping(value = "/{imageId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageContent(@PathVariable("imageId") Integer imageId){
		//recupero il content nel database
		byte[] content = service.getImageContent(imageId);
		
		//preparo gli headers della response con il tipo di contenuto
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    
	    //ritorno il conteuto di headers e lo stato http (guarda ispeziona elemento)
	    return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}

}
