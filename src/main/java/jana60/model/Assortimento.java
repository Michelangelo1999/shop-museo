package jana60.model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Assortimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String nomeFornitore;
	
	private LocalDate data;
	
	
	@NotNull
	@Column(columnDefinition = "double default 0.00")
	private double costo;
	
	@ManyToMany(mappedBy = "assortimenti")
	private List<Prodotto> prodotti;
	
	@ManyToMany
	private List<Quantita> quantitaAss;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFornitore() {
		return nomeFornitore;
	}

	public void setNomeFornitore(String nomeFornitore) {
		this.nomeFornitore = nomeFornitore;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}


	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public List<Quantita> getQuantitaAss() {
		return quantitaAss;
	}

	public void setQuantitaAss(List<Quantita> quantitaAss) {
		this.quantitaAss = quantitaAss;
	}
	
	//custom
    public int getQuantInt() {
    	int quantitaInt = 0;
    	Iterator<Quantita> quantIterator = this.quantitaAss.iterator();
    	
    	while(quantIterator.hasNext()) {
    		Quantita current = quantIterator.next();
    		quantitaInt += current.getQuantita();
    	}
    	return quantitaInt;
    }
	
}
