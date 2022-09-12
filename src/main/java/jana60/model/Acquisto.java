package jana60.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Acquisto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate data;

	@Column(columnDefinition = "double default 0.00")
	private double prezzoTotale;

	@OneToMany(mappedBy = "acquisto")
	private List<CardAcquisto> cardAcquisto;
	
	@OneToOne
	private Fattura fattura;

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
	
	public List<CardAcquisto> getCardAcquisto() {
		return cardAcquisto;
	}

	public void setCardAcquisto(List<CardAcquisto> cardAcquisto) {
		this.cardAcquisto = cardAcquisto;
	}

	public Fattura getFattura() {
		return fattura;
	}

	public void setFattura(Fattura fattura) {
		this.fattura = fattura;
	}
	
	

}
