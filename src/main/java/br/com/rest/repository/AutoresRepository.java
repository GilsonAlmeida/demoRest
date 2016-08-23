package br.com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rest.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{

	
	
}
