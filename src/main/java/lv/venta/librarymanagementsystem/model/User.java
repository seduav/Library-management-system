package lv.venta.librarymanagementsystem.model;

import java.util.ArrayList;
import java.util.Collection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idu")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idu;
	
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[A-Za-z.]+", message = "Only letters and dot allowed")
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Collection<Authority> authorities = new ArrayList<>();
	
	public User(String username, String password, Authority ... auths) {
		setUsername(username);
		setPassword(password);
		for(Authority tempA: auths)
			addAuthority(tempA);
	}
	
	public void addAuthority(Authority authority) {
		if(!authorities.contains(authority))
			authorities.add(authority);
	}
	
	public void removeAuthority(Authority authority) {
		if(authorities.contains(authority))
			authorities.remove(authority);
	}
	
}