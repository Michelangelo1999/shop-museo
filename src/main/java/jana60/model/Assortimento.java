package jana60.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Assortimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String nomeFornitore;

	private LocalDate data;

	@NotNull
	private Integer[] quantita;

	@NotNull
	private double[] costo;

	@ManyToMany
	private List<Prodotto> prodotto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFornitore() {
		return nomeFornitore;
	}

	public void setNomeFornitore(String nomeFornitore) {
		this.nomeFornitore = nomeFornitore;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer[] getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer[] quantita) {
		this.quantita = quantita;
	}

	public double[] getCosto() {
		return costo;
	}

	public void setCosto(double[] costo) {
		this.costo = costo;
	}

	public List<Prodotto> getProdotto() {
		return prodotto;
	}

	public void setProdotto(List<Prodotto> prodotto) {
		this.prodotto = prodotto;
	}

}
