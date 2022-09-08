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

import javax.persistence.OneToOne;

import javax.persistence.OneToMany;

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
	@Column(columnDefinition = "double default 0.00")
	private double costo;

	
	@ManyToMany
	private List<Prodotto> prodotto;
	
	

	@OneToMany(mappedBy = "assortimento")

	private List<Rifornimento> rifornimento;


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



	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}


	public List<Prodotto> getProdotto() {
		return prodotto;
	}

	public List<Rifornimento> getRifornimento() {
		return rifornimento;

	}

	public void setRifornimento(List<Rifornimento> rifornimento) {
		this.rifornimento = rifornimento;
	}

	// custom
	// public int getQuantInt() {
	// int quantitaInt = 0;
	// Iterator<Quantita> quantIterator = this.quantitaAss.iterator();

	// while (quantIterator.hasNext()) {
	// Quantita current = quantIterator.next();
	// quantitaInt += current.getQuantita();
	// }

	// return quantitaInt;
	// }

	/*
	 * public double getCostoAs() { double costo = 0; Iterator<Prodotto>
	 * prodIterator = this.prodotto.iterator();
	 * 
	 * while (prodIterator.hasNext()) { Prodotto current = prodIterator.next();
	 * costo += this.getQuantInt() * this.getCosto(); } return costo; }
	 */

	/*
	 * public int getQuantInt() { int quantitaInt = 0; Iterator<Quantita>
	 * quantIterator = this.quantitaAss.iterator();
	 * 
	 * while (quantIterator.hasNext()) { Quantita current = quantIterator.next();
	 * quantitaInt = 0; quantitaInt = current.getQuantita(); } return quantitaInt; }
	 * 
	 * public double getCostoAs() { int i = 0; double costo = 0; Iterator<Prodotto>
	 * prodIterator = this.prodotto.iterator();
	 * 
	 * while (prodIterator.hasNext()) {
	 * 
	 * Prodotto current = prodIterator.next(); int quantita = this.getQuantInt();
	 * 
	 * costo += this.getCosto()[i] * quantita; i++; } return costo; }
	 */

}
