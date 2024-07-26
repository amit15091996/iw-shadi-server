package com.shadi.expection.handle;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.shadi.expection.AccessDeniedException;
import com.shadi.expection.CustomException;
import com.shadi.expection.GenericException;
import com.shadi.expection.InternalServerError;
import com.shadi.expection.NotFoundException;
import com.shadi.utils.AppConstants;
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
	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<Object, Object>> noDataAvailable(NotFoundException notFoundException,
			WebRequest request) {

		CustomException customException = new CustomException(AppConstants.Not_Found, AppConstants.Not_Found_desc,
				LocalDateTime.now(), notFoundException.getMessage(), request.getDescription(false));
		Map<Object, Object> notFound = new HashMap<>();
		notFound.put(AppConstants.statusCode, customException.getStatusCode());
		notFound.put(AppConstants.status, customException.getStatus());
		notFound.put(AppConstants.timeStamp, customException.getTimestamp().toString());
		notFound.put(AppConstants.statusMessage, customException.getMessage());
		notFound.put(AppConstants.description, customException.getDescription());
		return ResponseEntity.ok(notFound);

	}
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Map<Object, Object>> internalServerError(InternalServerError internalServerError,
			WebRequest request) {

		CustomException badRequestException = new CustomException(AppConstants.Internal_Server_Error,
				AppConstants.Internal_Server_Error_desc, LocalDateTime.now(), internalServerError.getMessage(),
				request.getDescription(false));

		Map<Object, Object> internalServerErrorMap = new HashMap<>();

		internalServerErrorMap.put(AppConstants.statusCode, badRequestException.getStatusCode());
		internalServerErrorMap.put(AppConstants.status, badRequestException.getStatus());
		internalServerErrorMap.put(AppConstants.timeStamp, badRequestException.getTimestamp().toString());
		internalServerErrorMap.put(AppConstants.statusMessage, badRequestException.getMessage());
		internalServerErrorMap.put(AppConstants.description, badRequestException.getDescription());
		return ResponseEntity.ok(internalServerErrorMap);

	}
	
	 @ExceptionHandler(AccessDeniedException.class)
	    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
	        // Handle access denied exception
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                             .body(Collections.singletonMap("error", "Access Denied"));
	    }
}
