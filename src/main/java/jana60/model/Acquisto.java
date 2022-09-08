package jana60.model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Acquisto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate data;

	@Column(columnDefinition = "double default 0.00")
	private double prezzoTotale;	
	
	@ManyToMany
	private List<Prodotto> prodotto;
	
	@ManyToMany
	private List<Quantita> quantitaAcq;
	
//	@ManyToMany(mappedBy = "acquisto")
//	private Carrello carrello;


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

	public List<Quantita> getQuantitaAcq() {
		return quantitaAcq;
	}

	public void setQuantitaAcq(List<Quantita> quantitaAcq) {
		this.quantitaAcq = quantitaAcq;
	}

	// custom
		public int getQuantInt() {
			int quantitaInt = 0;
			Iterator<Quantita> quantIterator = this.quantitaAcq.iterator();

			while (quantIterator.hasNext()) {
				Quantita current = quantIterator.next();
				quantitaInt = 0;
				quantitaInt += current.getQuantita();
			}

			return quantitaInt;
		}

		public double getPrezzoAc() {
			double costo = 0;
			Iterator<Prodotto> prodIterator = this.prodotto.iterator();

			while (prodIterator.hasNext()) {
				Prodotto current = prodIterator.next();
				int quantita = this.getQuantInt();
				costo += quantita * this.getPrezzoTotale();
			}
			return costo + 1;
		}
}
