package br.edu.ifce.meuprimeirospringboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifce.meuprimeirospringboot.beans.Address;
import br.edu.ifce.meuprimeirospringboot.beans.Phone;
import br.edu.ifce.meuprimeirospringboot.beans.Role;
import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.enums.Ethnicity;
import br.edu.ifce.meuprimeirospringboot.repository.RoleRepository;
import br.edu.ifce.meuprimeirospringboot.repository.UserRepository;

@SpringBootApplication
public class MeuprimeirospringbootApplication implements CommandLineRunner  {
	@Autowired
	private UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired private RoleRepository roleRepository;
 
	public static void main(String[] args) {
		SpringApplication.run(MeuprimeirospringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Cria roles se não existirem
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
        	Role admin = new Role();
			admin.setName("ROLE_ADMIN");
			return roleRepository.save(admin);
        });
        
        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
        	Role user = new Role();
			user.setName("ROLE_USER");
			return roleRepository.save(user);
        });
		
        if (userRepository.findByEmail("admin@ifce.edu.br").isEmpty()) {
        	User u = new User();
        	u.setCpf("00000000000");
        	u.setName("Administrador");
        	u.setEmail("admin@ifce.edu.br");
        	u.setEthnicity(Ethnicity.Negro);
        	u.setDtBirth(new Date());
        	u.setPassword(passwordEncoder.encode("123"));
        	
        	Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            u.setRoles(roles);

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

}
