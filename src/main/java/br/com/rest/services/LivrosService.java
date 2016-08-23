package br.com.rest.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rest.domain.Comentario;
import br.com.rest.domain.Livro;
import br.com.rest.repository.ComentariosRepository;
import br.com.rest.repository.LivrosRepository;
import br.com.rest.services.exception.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	public ComentariosRepository comentariosRepository;
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	public Livro buscar(Long id){
		Livro livro = livrosRepository.findOne(id);
		if(livro == null){
			throw new LivroNaoEncontradoException("O livro não pode ser encontrado");
		}
		return livro;
	}
	
	public Livro salvar(Livro livro){
		livro.setId( null);
		return livrosRepository.save(livro);
	}
	
	public void deletar(Long id){
		try {
			livrosRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pode ser encontrado");
		}
		
	}
	
	public void atualizar(Livro livro){
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro){
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId , Comentario comentario){
		Livro livro = buscar(livroId);
		comentario.setLivro(livro);
		comentario.setData(new Date());
		return comentariosRepository.save(comentario);
	}
	
	public List<Comentario>listarComentarios(Long livroId){
		Livro livro = buscar(livroId);
		return livro.getComentarios();
	}
	
}
