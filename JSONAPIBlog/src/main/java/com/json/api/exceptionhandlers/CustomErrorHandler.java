package com.json.api.exceptionhandlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler 
{
	@Value("${com.json.api.error.tag}")
	private String errorTag;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		ErrorResponse errorresponse = new ErrorResponse();
		errorresponse.setError(errorTag);

		return new ResponseEntity(errorresponse, HttpStatus.BAD_REQUEST);

	}
	
	
}