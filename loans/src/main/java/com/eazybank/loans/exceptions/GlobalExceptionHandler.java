package com.eazybank.loans.exceptions;

import com.eazybank.loans.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
        /**
     * Handles {@link MethodArgumentNotValidException} by mapping its validation errors to a {@link ResponseEntity} with status {@code 400}.
     *
     * @param ex         The {@link MethodArgumentNotValidException} to handle
     * @param headers     The HTTP headers of the request
     * @param status      The HTTP status code
     * @param request     The HTTP request
     * @return             A {@link ResponseEntity} containing a map of validation errors with status {@code 400}
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
        
        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }


        /**
     * Handles {@link LoanAlreadyExistsException} by creating an error response DTO and returning it with a status of {@code 400}.
     *
     * @param ex         The {@link LoanAlreadyExistsException} to handle
     * @param request     The HTTP request that triggered the exception
     * @return             A {@link ResponseEntity} containing an error response DTO with status {@code 400}
     */
    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExistsException(LoanAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

        /**
     * Handles {@link ResourceNotFoundException} by creating an error response DTO and returning it with a status of {@code 404}.
     *
     * @param ex         The {@link ResourceNotFoundException} to handle
     * @param request     The HTTP request that triggered the exception
     * @return             A {@link ResponseEntity} containing an error response DTO with status {@code 404}
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

        /**
     * Handles all exceptions by creating an error response DTO and returning it with a status of {@code 500}.
     *
     * @param ex         The {@link Exception} to handle
     * @param request     The HTTP request that triggered the exception
     * @return             A {@link ResponseEntity} containing an error response DTO with status {@code 500}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
