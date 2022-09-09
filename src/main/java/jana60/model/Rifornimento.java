package jana60.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rifornimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private double prezzo;

	private int quantita;

	@ManyToOne
	private Prodotto prodotto;

	@ManyToOne
	private Assortimento assortimento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Assortimento getAssortimento() {
		return assortimento;
	}

	public void setAssortimento(Assortimento assortimento) {
		this.assortimento = assortimento;
	}

	public void setAssortimento(Optional<Assortimento> assortimento2) {
		// TODO Auto-generated method stub

	}


	public void setAcquisto(Optional<Acquisto> acquisto2) {
		// TODO Auto-generated method stub

	}

}
