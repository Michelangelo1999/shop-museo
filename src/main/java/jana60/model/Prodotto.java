package jana60.model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotEmpty
	private String nome;

	@Lob
	private String descrizione;

	@NotNull
	@NotEmpty
	private double prezzo;

	@OneToMany(mappedBy = "prodotto")
	private List<CardAcquisto> cardAcquisti;

	@OneToMany(mappedBy = "prodotto")
	private List<Rifornimento> rifornimenti;

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

	public List<CardAcquisto> getCardAcquisti() {
		return cardAcquisti;
	}

	public void setCardAcquisti(List<CardAcquisto> cardAcquisti) {
		this.cardAcquisti = cardAcquisti;
	}

	public List<Rifornimento> getRifornimenti() {
		return rifornimenti;
	}

	public void setRifornimenti(List<Rifornimento> rifornimenti) {
		this.rifornimenti = rifornimenti;
	}

	// metodi custom
	public int getQuantAcquistata() {
		int quantitaAcquistata = 0;
		Iterator<CardAcquisto> acquistIter = this.cardAcquisti.iterator();
		while (acquistIter.hasNext()) {
			CardAcquisto current = acquistIter.next();
			LocalDate unMeseFa = LocalDate.now().minusDays(30);
			if (current.getAcquisto().getData().isAfter(unMeseFa)) {
				quantitaAcquistata += current.getQuantita();
			}

		}
		return quantitaAcquistata;
	}

	// metodo custom che restituisce la quantità
	public int getQuantitaDisponibile() {
		int quantitaNetta = 0;
		int quantitaAcquistata = 0;
		int quantitaAssortita = 0;

		Iterator<Rifornimento> rifornimentiIter = this.rifornimenti.iterator();
		while (rifornimentiIter.hasNext()) {
			Rifornimento current = rifornimentiIter.next();
			quantitaAssortita += current.getQuantita();
		}

		Iterator<CardAcquisto> acquistiIter = this.cardAcquisti.iterator();
		while (acquistiIter.hasNext()) {
			CardAcquisto current = acquistiIter.next();
			quantitaAcquistata += current.getQuantita();
		}

		quantitaNetta = quantitaAssortita - quantitaAcquistata;

		return quantitaNetta;
	}

}
