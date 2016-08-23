package br.com.rest.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rest.domain.Autor;
import br.com.rest.services.AutoresService;

@RestController
@RequestMapping("/autores")
public class AutoresResource {

	@Autowired
	private AutoresService autoresService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Autor autor){
		autor = autoresService.salvar(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping( value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Autor> buscar(@PathVariable("id")Long id){
		return ResponseEntity.status(HttpStatus.OK).body(autoresService.buscar(id));
	}
	
	
	
//	@RequestMapping( value= "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable Long id){
//		autoresService.deletar(id);
//		return ResponseEntity.noContent().build();
//		
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Void> update(@RequestBody Autor Autor, @PathVariable("id") Long id) {
//		Autor.setId(id);
//		autoresService.atualizar(Autor);
//		return ResponseEntity.noContent().build();
//	}
}
