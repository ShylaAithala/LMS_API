package com.ninja.lms.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja.lms.entity.ExceptionResponse;


@ControllerAdvice
@RestController
public class CustomExceptionHandler{
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@ExceptionHandler(AlreadyExistsValidationException.class)
    public final ResponseEntity<ExceptionResponse> handlerValidationException(AlreadyExistsValidationException ex, WebRequest request){
 
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex,WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), errors.toString(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
	    
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	   
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		   
				ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), NestedExceptionUtils.getMostSpecificCause(ex).getMessage(), request.getDescription(false));
			   
				return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
				
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionResponse> handleMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request){
		
		String provided = "Invalid Input data";
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), provided, ex.getLocalizedMessage());
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
