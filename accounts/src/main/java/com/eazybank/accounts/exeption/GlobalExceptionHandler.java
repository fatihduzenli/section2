package com.eazybank.accounts.exeption;

import com.eazybank.accounts.dto.ErrorResponseDto;
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
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
    //The validation errors are then iterated over using a forEach loop. For each error, the field name (fieldName)
// and validation message (validationMsg) are extracted. he fieldName is obtained from the FieldError object,
// T and the validationMsg is obtained from the error object itself. These are then added to the validationErrors map.
    
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

    /**
 * This method handles all exceptions that are not explicitly handled by other exception handlers.
 * It creates an {@link ErrorResponseDto} with the provided exception message, HTTP status INTERNAL_SERVER_ERROR,
 * and the current timestamp.
 *
 * @param exception The {@code Exception} that occurred.
 * @param webRequest The current web request that triggered the exception.
 * @return A {@link ResponseEntity} containing the {@link ErrorResponseDto} with the specified details.
 */
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
