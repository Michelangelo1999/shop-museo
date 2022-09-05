package jana60.model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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

	@ManyToMany(mappedBy = "prodotto")
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
	// int assortiti = ((Assortimento) this.assortimento).getQuantita();
	/// int acquistati = ((Acquisto) this.acquisto).getQuantita();
	// quantitaDisp = assortiti - acquistati;
	// return quantitaDisp;
	// }

	// public int getQuantitaAssortimento() {
	// int quantita = 0;
	// List<Assortimento> ass = this.assortimento;
	// Iterator<Assortimento> assIter = ass.iterator();
	// while (assIter.hasNext()) {
	// Assortimento current = assIter.next();
	// quantita += current.getQuantita();
	// }
	// return quantita;
	// }

	public int quantitaDisponibile() {
		int quantitaDisponibile = 0;

		int assortiti = 0;
		int acquistati = 0;

		Iterator<Assortimento> assIter = this.assortimento.iterator();
		Iterator<Acquisto> acqIter = this.acquisto.iterator();

		while (assIter.hasNext()) {
			Assortimento current = assIter.next();
			assortiti += current.getQuantInt();
		}

		while (acqIter.hasNext()) {
			Acquisto current = acqIter.next();
			acquistati += current.getQuantInt();
		}

		quantitaDisponibile = assortiti - acquistati;

		return quantitaDisponibile;
	}
}
