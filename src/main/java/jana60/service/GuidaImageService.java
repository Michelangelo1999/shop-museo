package jana60.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jana60.model.Guida;
import jana60.model.Image;
import jana60.model.ImageForm;
import jana60.repository.GuidaRepository;
import jana60.repository.ImageRepository;
import jana60.repository.ProdottoRepository;

@Service
public class GuidaImageService {

	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private GuidaRepository guidaRepo;

	// ricerco la guida tramite id, poi passo la guida alla repo img per vedere le
	// img ad essa associata
	public List<Image> getImageByGuidaId(Integer guidaId) {
		Guida guida = guidaRepo.findById(guidaId).get();
		return imageRepo.findByGuida(guida);
	}

	// passo l'id della guida, voglio un img per quella guida: qui la creo, ma non
	// la converto -> ritorna un imageForm
	public ImageForm createGuidaImageForm(Integer guidaId) {
		Guida guida = guidaRepo.findById(guidaId).get();
		ImageForm img = new ImageForm();
		img.setGuida(guida);
		return img;
	}

	// converto l'imgform in img
	public Image createGuidaImage(ImageForm imageForm) throws IOException {
		Image imgToSave = new Image();
		imgToSave.setGuida(imageForm.getGuida());

		if (imageForm.getContentMultipart() != null) {
			byte[] contentSerialized = imageForm.getContentMultipart().getBytes();
			imgToSave.setContent(contentSerialized);
		}

		return imageRepo.save(imgToSave);
	}

	// metodo che restituisce il contenuto dell'immagine a partire dal suo id
	// (restituisce il contenuto, quindi l'array di byte, che a scermo viene
	// mostrato come immagine
	public byte[] getImageContent(Integer id) {
		Image img = imageRepo.findById(id).get();
		return img.getContent();
	}

}
