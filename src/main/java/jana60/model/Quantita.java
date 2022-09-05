package jana60.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Quantita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int quantita;

//	@ManyToMany(mappedBy = "quantitaProd")
//	private List<Prodotto> prodotti;

	@ManyToMany(mappedBy = "quantitaAss")
	private List<Assortimento> assortimenti;

	@ManyToMany(mappedBy = "quantitaAcq")
	private List<Acquisto> acquisti;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
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
}
