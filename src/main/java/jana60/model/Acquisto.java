package jana60.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Acquisto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate data;

	private int quantita;

	private double prezzoTotale;

	@ManyToMany
	private List<Prodotto> prodotto;

	@ManyToOne
	private Carrello carrello;

	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public List<Prodotto> getProdotto() {
		return prodotto;
	}

	public void setProdotto(List<Prodotto> prodotto) {
		this.prodotto = prodotto;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	/*
	 * // custom public int getQuantInt() { int quantitaInt = 0; Iterator<Quantita>
	 * quantIterator = this.quantitaAcq.iterator();
	 * 
	 * while (quantIterator.hasNext()) { Quantita current = quantIterator.next();
	 * quantitaInt = 0; quantitaInt += current.getQuantita(); }
	 * 
	 * return quantitaInt; }
	 * 
	 * public double getPrezzoAc() { double costo = 0; Iterator<Prodotto>
	 * prodIterator = this.prodotto.iterator();
	 * 
	 * while (prodIterator.hasNext()) { Prodotto current = prodIterator.next(); int
	 * quantita = this.getQuantInt(); costo += quantita * this.getPrezzoTotale(); }
	 * return costo + 1; }
	 */
}
