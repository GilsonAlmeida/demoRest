package br.com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rest.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long>{

	
	
}
