package br.edu.ifce.meuprimeirospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.meuprimeirospringboot.beans.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
