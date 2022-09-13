package jana60.model;

import javax.persistence.Basic;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {

	private Integer id;

	@Basic(optional = true)
	private Guida guida;

	@Basic(optional = true)
	private Prodotto prodotto;

	@Basic(optional = true)
	private Visita visita;

	private MultipartFile contentMultipart;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Guida getGuida() {
		return guida;
	}

	public void setGuida(Guida guida) {
		this.guida = guida;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public MultipartFile getContentMultipart() {
		return contentMultipart;
	}

	public void setContentMultipart(MultipartFile contentMultipart) {
		this.contentMultipart = contentMultipart;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

}
