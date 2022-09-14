package jana60.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String username;

	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> ruoli;

	// getter e setter

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Role> ruoli) {
		this.ruoli = ruoli;
	}

}
