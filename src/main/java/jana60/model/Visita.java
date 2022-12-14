package jana60.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
public class Visita {

	// attributi classe

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String titolo;

	private String descrizione;

	private LocalDateTime dataInizio;

	private LocalDateTime dataFine;

	// notnull
	@Column(columnDefinition = "double default 0.00")
	private double prezzo;

	@Max(100)
	@Column(columnDefinition = "int default 0")
	private int capienza;

	@ManyToOne // notnull
	private Guida guida;

	@OneToMany(mappedBy = "visita", cascade = CascadeType.ALL)
	private List<PrenotazioneVisita> prenotazioni;

	// getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public int getNumeroPrenotati() {
		int numeroPrenotati = 0;
		for (PrenotazioneVisita pv : prenotazioni) {
			numeroPrenotati += pv.getNumeriBiglietti();
		}
		return numeroPrenotati;
	}

	public Guida getGuida() {
		return guida;
	}

	public void setGuida(Guida guida) {
		this.guida = guida;
	}

	// metodi custom
	// formatter
	public String orarioInizio() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy HH:mm");
		String formatDateTime = dataInizio.format(formatter);
		return formatDateTime;
	}

	public String orarioFine() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String formatDateTime = dataFine.format(formatter);
		return formatDateTime;
	}

}
