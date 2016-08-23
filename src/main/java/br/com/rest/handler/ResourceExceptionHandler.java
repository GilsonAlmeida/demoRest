package br.com.rest.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.rest.domain.DetalhesErro;
import br.com.rest.services.exception.AutorExistenteException;
import br.com.rest.services.exception.AutorNaoEncontradoException;
import br.com.rest.services.exception.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException
	(LivroNaoEncontradoException e, HttpServletRequest request ){
		
		DetalhesErro erro  = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O livro não pode ser encontrado");
		erro.setMensagemDesenvolvdor("http://erros.rest/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistenteException
	(AutorExistenteException e, HttpServletRequest request ){
		
		DetalhesErro erro  = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Autor já existente");
		erro.setMensagemDesenvolvdor("http://erros.rest/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoException
	(AutorNaoEncontradoException e, HttpServletRequest request ){
		
		DetalhesErro erro  = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Autor não encontrado");
		erro.setMensagemDesenvolvdor("http://erros.rest/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
