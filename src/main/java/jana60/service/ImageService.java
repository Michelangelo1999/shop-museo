package jana60.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jana60.model.Guida;
import jana60.model.Image;
import jana60.model.ImageForm;
import jana60.model.Prodotto;
import jana60.model.Visita;
import jana60.repository.GuidaRepository;
import jana60.repository.ImageRepository;
import jana60.repository.ProdottoRepo;
import jana60.repository.VisitaRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private ProdottoRepo prodottoRepo;

	@Autowired
	private VisitaRepository visitaRepo;

	@Autowired
	private GuidaRepository guidaRepo;

	// PRODOTTO ----------

	// prima ricerco il prodotto tramite id, poi passo quel prodotto alla repo
	// immagini per vedere tutte le img ad esso associate
	public List<Image> getImageByProdottoId(Integer prodottoId) {

		Prodotto prodotto = prodottoRepo.findById(prodottoId).get();

		return imageRepo.findByProdotto(prodotto);
	}

	// passo l'id del prodotto, voglio un immagine per quel prodotto (qui inizio a
	// crearla, ma non è convertita)
	// ritorna infatti come valore una imageForm con un prodotto associato (alla
	// fine posso associarne più di uno)
	public ImageForm createImageForm(Integer prodottoId) {
		Prodotto prodotto = prodottoRepo.findById(prodottoId).get(); // per ora accedo al prodotto a cui dare l'ing
																		// tramite il suo id

		// creo l'imageform
		ImageForm img = new ImageForm();

		// associo a questo imageform un prodotto, e lo faccio tramite il suo metodo set
		img.setProdotto(prodotto);
		return img;
	}

	// ora devo convertire l'imageform in immagine e salvarlo sul db
	// il metodo lancia un eccezione perche potrebbe andare in errore
	public Image createImage(ImageForm imageForm) throws IOException {
		Image imgToSave = new Image(); // creo una nuova immagine, per ora vuota
		imgToSave.setProdotto(imageForm.getProdotto()); // setto il prodotto per questa immagine, che è lo stesso
														// prodotto dell'imgForm che passo come paramentro

		// converto il multipart in un array di byte (multipart = imgForm --> byte[] =
		// img)
		if (imageForm.getContentMultipart() != null) {
			byte[] contentSerialized = imageForm.getContentMultipart().getBytes(); // prendo il contentmultipar della
																					// form -> trasformo in byte
																					// (serializzo) -> salvo in un array
																					// di byte
			imgToSave.setContent(contentSerialized); // il content dell'img è tipo byte[], guarda la classe
		}

		// salvo img sul db e la ritorno
		return imageRepo.save(imgToSave);
	}

	// metodo che restituisce il contenuto dell'immagine a partire dal suo id
	// (restituisce il contenuto, quindi l'array di byte, che a scermo viene
	// mostrato come immagine
	public byte[] getImageContent(Integer id) {
		Image img = imageRepo.findById(id).get();
		return img.getContent();
	}

	// VISITE GUIDATE ------------------

	public List<Image> getImageByVisitaId(Integer visitaId) {
		Visita visita = visitaRepo.findById(visitaId).get();
		return imageRepo.findByVisita(visita);
	}

	public ImageForm createImageFormVisita(Integer visitaId) {
		Visita visita = visitaRepo.findById(visitaId).get(); // per ora accedo al prodotto a cui dare l'ing
																// tramite il suo id

		// creo l'imageform
		ImageForm imgVisita = new ImageForm();

		// associo a questo imageform un prodotto, e lo faccio tramite il suo metodo set
		imgVisita.setVisita(visita);
		return imgVisita;
	}

	public Image createImageVisita(ImageForm imageFormVisita) throws IOException {
		Image imgToSave = new Image(); // creo una nuova immagine, per ora vuota
		imgToSave.setVisita(imageFormVisita.getVisita());
		// converto il multipart in un array di byte (multipart = imgForm --> byte[] =
		// img)
		if (imageFormVisita.getContentMultipart() != null) {
			byte[] contentSerialized = imageFormVisita.getContentMultipart().getBytes();
			imgToSave.setContent(contentSerialized); // il content dell'img è tipo byte[], guarda la classe
		}

		// salvo img sul db e la ritorno
		return imageRepo.save(imgToSave);
	}

	public byte[] getImageContentV(Integer id) {
		Image imgVisita = imageRepo.findById(id).get();
		return imgVisita.getContent();
	}

	// GUIDE TURISTICHE ------------------

	public List<Image> getImageByGuidaId(Integer guidaId) {
		Guida guida = guidaRepo.findById(guidaId).get();
		return imageRepo.findByGuida(guida);
	}

	public ImageForm createImageFormGuida(Integer guidaId) {
		Guida guida = guidaRepo.findById(guidaId).get(); // per ora accedo al prodotto a cui dare l'ing
															// tramite il suo id

		// creo l'imageform
		ImageForm imgGuida = new ImageForm();

		// associo a questo imageform un prodotto, e lo faccio tramite il suo metodo set
		imgGuida.setGuida(guida);
		return imgGuida;
	}

	public Image createImageGuida(ImageForm imageFormGuida) throws IOException {
		Image imgToSave = new Image(); // creo una nuova immagine, per ora vuota
		imgToSave.setGuida(imageFormGuida.getGuida());
		// converto il multipart in un array di byte (multipart = imgForm --> byte[] =
		// img)
		if (imageFormGuida.getContentMultipart() != null) {
			byte[] contentSerialized = imageFormGuida.getContentMultipart().getBytes();
			imgToSave.setContent(contentSerialized); // il content dell'img è tipo byte[], guarda la classe
		}

		// salvo img sul db e la ritorno
		return imageRepo.save(imgToSave);
	}

	public byte[] getImageContentG(Integer id) {
		Image imgGuida = imageRepo.findById(id).get();
		return imgGuida.getContent();
	}
}
