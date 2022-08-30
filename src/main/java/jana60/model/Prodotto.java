package jana60.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String nome;

	@Lob
	private String descrizione;

	@NotNull
	private double prezzo;

	private int quantita;

	@OneToMany(mappedBy = "prodotto")
	private java.util.List<Assortimento> assortimento;

	@OneToMany(mappedBy = "prodotto")
	private List<Acquisto> acquito;

	// getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	public java.util.List<Assortimento> getAssortimento() {
		return assortimento;
	}

	public void setAssortimento(java.util.List<Assortimento> assortimento) {
		this.assortimento = assortimento;
	}

	public List<Acquisto> getAcquito() {
		return acquito;
	}

	public void setAcquito(List<Acquisto> acquito) {
		this.acquito = acquito;
	}

	// metodi custom
	/*
	 * ritorna il numero di prodotti disponibili, calcolato come:
	 * 
	 * numero di prodotti in magazzino - numero di prodotti venduti
	 */
	public int getProdottiAssortimento() {
		int prodDisponibiliAs = 0;
		for (Assortimento as : this.assortimento) {
			if (as.getQuantita() != 0) {
				prodDisponibiliAs += 1;
			}
		}
		return prodDisponibiliAs;

	}

	public int getProdottiAcquisto() {
		int prodDisponibiliAc = 0;
		for (Acquisto as : this.acquito) {
			if (as.getQuantita() != 0) {
				prodDisponibiliAc -= 1;
			}
		}
		return prodDisponibiliAc;

	}
}
