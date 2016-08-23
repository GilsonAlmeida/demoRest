package br.com.rest.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rest.domain.Comentario;
import br.com.rest.domain.Livro;
import br.com.rest.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService livrosService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro){
		livro = livrosService.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping( value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity <?> buscar(@PathVariable Long id){
		Livro livro = livrosService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@RequestMapping( value= "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		livrosService.deletar(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosService.atualizar(livro);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/comentarios", method= RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId,
			@RequestBody Comentario comentario){
		
		livrosService.salvarComentario(livroId, comentario); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}/comentarios", method= RequestMethod.GET)
	public ResponseEntity<List<Comentario>>listarComentarios(
			@PathVariable("id")Long livroId){
		List<Comentario> comentarios = livrosService.listarComentarios(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
}
