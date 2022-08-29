package jana60.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
	
	@OneToOne
	private Assortimento assortimento;
	
	@OneToOne
	private Acquisto acquisto;

	//getters and setters
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

	public Assortimento getAssortimento() {
		return assortimento;
	}

	public void setAssortimento(Assortimento assortimento) {
		this.assortimento = assortimento;
	}

	public Acquisto getAcquisto() {
		return acquisto;
	}

	public void setAcquisto(Acquisto acquisto) {
		this.acquisto = acquisto;
	}
	
	
	
	
}
