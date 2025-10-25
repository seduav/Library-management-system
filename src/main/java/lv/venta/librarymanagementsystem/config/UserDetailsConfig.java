package lv.venta.librarymanagementsystem.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lv.venta.librarymanagementsystem.model.Authority;
import lv.venta.librarymanagementsystem.model.User;

public class UserDetailsConfig implements UserDetails{

	private User user;
	
	public UserDetailsConfig(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Iterator<Authority> iterator = user.getAuthorities().iterator();
		while(iterator.hasNext()) {
			authorities.add(new SimpleGrantedAuthority(iterator.next().getTitle()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}