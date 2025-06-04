package br.edu.ifce.meuprimeirospringboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifce.meuprimeirospringboot.beans.Address;
import br.edu.ifce.meuprimeirospringboot.beans.Phone;
import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.enums.Ethnicity;
import br.edu.ifce.meuprimeirospringboot.repository.UserRepository;

@SpringBootApplication
public class MeuprimeirospringbootApplication implements CommandLineRunner  {
	@Autowired
	private UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static void main(String[] args) {
		SpringApplication.run(MeuprimeirospringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User u = new User();
		u.setCpf("00000000000");
		u.setName("Fulano de Tal");
		u.setEmail("fulano@gmail.com");
		u.setEthnicity(Ethnicity.Indígena);
		u.setDtBirth(new Date());
		u.setPassword(passwordEncoder.encode("123"));
		
		Address e = new Address();
		e.setNeighborhood("Jereissati");
		e.setZipCode("60000-000");
		e.setStreet("Rua I");
		e.setNumber("777");
		
		List<Phone> l = new ArrayList<Phone>();
		Phone t1 = new Phone();
		t1.setPhNumber("9999-9999");
		t1.setIsMain(true);
		t1.setIsWpp(true);
		l.add(t1);
			
		u.setAddress(e);
		u.setTelefones(l);
	
		userRepository.save(u);

		Long n =  userRepository.count();
		System.out.println(n);
		
	}

}
