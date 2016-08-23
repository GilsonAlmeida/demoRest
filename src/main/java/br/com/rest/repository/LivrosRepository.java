package br.com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rest.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

	
	
}
