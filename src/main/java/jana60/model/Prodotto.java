package jana60.model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany
	private List<Assortimento> assortimenti;
	
	@ManyToMany
	private List<Acquisto> acquisti;
	
//	@ManyToMany
//	private List<Quantita> quantitaProd;

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

	public List<Assortimento> getAssortimenti() {
		return assortimenti;
	}

	public void setAssortimenti(List<Assortimento> assortimenti) {
		this.assortimenti = assortimenti;
	}

	public List<Acquisto> getAcquisti() {
		return acquisti;
	}

	public void setAcquisti(List<Acquisto> acquisti) {
		this.acquisti = acquisti;
	}

//	public List<Quantita> getQuantitaProd() {
//		return quantitaProd;
//	}
//
//	public void setQuantitaProd(List<Quantita> quantitaProd) {
//		this.quantitaProd = quantitaProd;
//	}

	
	public int quantitaDisponibile() {
		int quantitaDisponibile = 0;
		
		int assortiti = 0;
		int acquistati = 0;
		
		Iterator<Assortimento> assIter = this.assortimenti.iterator();
		Iterator<Acquisto> acqIter = this.acquisti.iterator();
		
		while(assIter.hasNext()) {
			Assortimento current = assIter.next();
			assortiti += current.getQuantInt();
		}
		
		while(acqIter.hasNext()) {
			Acquisto current = acqIter.next();
			acquistati += current.getQuantInt();
		}
		
		quantitaDisponibile = assortiti-acquistati;
		
		return quantitaDisponibile;
	}
	
}
