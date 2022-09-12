package jana60.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class PrenotazioneVisita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo Obbligatiorio")
	private String nome;
	
	@NotEmpty(message = "Campo Obbligatiorio")
	private String cognome;
	
	private String telefono;
	
	@ManyToOne
	private Visita visita;
	
	private Short numeriBiglietti;
	
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public Short getNumeriBiglietti() {
		return numeriBiglietti;
	}

	public void setNumeriBiglietti(Short numeriBiglietti) {
		this.numeriBiglietti = numeriBiglietti;
	}
	
	
}
