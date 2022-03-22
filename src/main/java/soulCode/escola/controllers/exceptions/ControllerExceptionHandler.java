package soulCode.escola.controllers.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import soulCode.escola.service.exceptions.DataIntegrityViolationException;
import soulCode.escola.service.exceptions.ObjectNotFoundException;

//é uma clase para tratar erros e excecões
@ControllerAdvice
public class ControllerExceptionHandler {
	
	//é uma classe java que é responsável por atender as requisições do cliente
	//Define um objeto par afornecer informações de solicitação do cliente a um servelet.
	//O trabalho é receber requisições enviados ao servidor 
	//ResponseEntity - É uma Entidade de resposta com o cabeçaho 

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request){
		
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataFormatada = formatador.format(data);
		
		StandardError error = new StandardError(dataFormatada, HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request){
		
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataFormatada = formatador.format(data);
		
		StandardError error = new StandardError(dataFormatada, HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
