package com.shadi.expection.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shadi.expection.GenericException;
import com.shadi.utils.ConstantValues;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ProblemDetail> handleGenericException(GenericException exception){
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setProperty(ConstantValues.MESSAGE, exception.getMessage());
		problemDetail.setProperty(ConstantValues.STATUS, ConstantValues.ERROR_MESSAGE);
		problemDetail.setStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setType(ConstantValues.ERROR_MESSAGE_URL);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
	}
}
