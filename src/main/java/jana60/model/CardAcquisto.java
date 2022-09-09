package jana60.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CardAcquisto {
// carrello
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int quantita;

	@ManyToOne
	private Acquisto acquisto;

	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public List<Acquisto> getAcquisti() {
//		return acquisti;
//	}
//
//	public void setAcquisti(List<Acquisto> acquisti) {
//		this.acquisti = acquisti;
//	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Acquisto getAcquisto() {
		return acquisto;
	}

	public void setAcquisto(Acquisto acquisto) {
		this.acquisto = acquisto;
	}

	public void setAcquisto(Optional<Acquisto> acquisto2) {
		// TODO Auto-generated method stub

	}

}
