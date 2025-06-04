package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.repository.UserRepository;
import br.edu.ifce.meuprimeirospringboot.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não Encontrado"));
		
		return new UserDetailsImpl(user);
	}

}
