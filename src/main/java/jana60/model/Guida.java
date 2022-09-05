package jana60.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import jana60.repository.GuidaRepository;

@Entity
public class Guida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo Obbligatiorio")
	private String nome;
	
	@NotEmpty(message = "Campo Obbligatiorio")
	private String cognome;
	
	@OneToMany(mappedBy = "guida")
	private List<Visita> visite;
	
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

	public List<Visita> getVisite() {
		return visite;
	}

	public void setVisite(List<Visita> visite) {
		this.visite = visite;
	}
	
}
