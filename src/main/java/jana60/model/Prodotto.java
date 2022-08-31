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

	@OneToMany(mappedBy = "prodotto")
	private List<Assortimento> assortimento;

	@OneToMany(mappedBy = "prodotto")
	private List<Acquisto> acquisto;

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

	public List<Assortimento> getAssortimento() {
		return assortimento;
	}

	public void setAssortimento(List<Assortimento> assortimento) {
		this.assortimento = assortimento;
	}

	public List<Acquisto> getAcquisto() {
		return acquisto;
	}

	public void setAcquisto(List<Acquisto> acquisto) {
		this.acquisto = acquisto;
	}

	// metodi custom
	// public int getQuantitaMagazzino() {
	// int quantitaDisp = 0;
	// int assortiti = this.assortimento.getQuantita();
	// int acquistati = this.acquisto.getQuantita();
	// quantitaDisp = assortiti - acquistati;
	// return quantitaDisp;
	// }
}
