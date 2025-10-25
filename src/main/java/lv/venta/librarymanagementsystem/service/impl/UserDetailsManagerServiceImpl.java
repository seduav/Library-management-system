package lv.venta.librarymanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lv.venta.librarymanagementsystem.config.UserDetailsConfig;
import lv.venta.librarymanagementsystem.model.User;
import lv.venta.librarymanagementsystem.repo.UserRepo;

@Service
public class UserDetailsManagerServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) throw new UsernameNotFoundException(username + " is not found");
		
		UserDetails details = new UserDetailsConfig(user);
		return details;
	}

}