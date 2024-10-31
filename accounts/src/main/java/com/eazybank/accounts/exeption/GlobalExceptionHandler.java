package com.eazybank.accounts.exeption;

import com.eazybank.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    
/**
 * This method handles the {@link CustomerAlreadyExistsException} by creating an {@link ErrorResponseDto}
 * with the provided exception message, HTTP status BAD_REQUEST, and the current timestamp.
 *
 * @param exception The {@link CustomerAlreadyExistsException} that occurred.
 * @param webRequest The current web request that triggered the exception.
 * @return A {@link ResponseEntity} containing the {@link ErrorResponseDto} with the specified details.
 */
@ExceptionHandler(CustomerAlreadyExistsException.class)
public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                                                 WebRequest webRequest) {
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
            HttpStatus.BAD_REQUEST,
            exception.getMessage(),
            LocalDateTime.now());

    return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
}

        /**
     * This method handles the {@code NoHandlerFoundException} by returning a {@link ResponseEntity} with a "Resource not found" message and HTTP status NOT_FOUND.
     *
     * @param exception The {@code NoHandlerFoundException} that occurred.
     * @return A {@link ResponseEntity} containing a "Resource not found" message and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
    }

        /**
     * This method handles the {@code ResourceNotFoundException} by creating an {@link ErrorResponseDto}
     * with the provided exception message, HTTP status NOT_FOUND, and the current timestamp.
     *
     * @param exception The {@code ResourceNotFoundException} that occurred.
     * @param webRequest The current web request that triggered the exception.
     * @return A {@link ResponseEntity} containing the {@link ErrorResponseDto} with the specified details.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                                 WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                            WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}