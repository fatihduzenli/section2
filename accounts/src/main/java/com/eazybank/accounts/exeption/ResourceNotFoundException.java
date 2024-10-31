package com.eazybank.accounts.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throws a {@code ResourceNotFoundException} when a specific resource is not found with the given input data.
 *
 * //@param resourceName The name of the resource that was not found.
 *  //@param fieldName    The name of the field that was used to search for the resource.
 * //@param fieldValue   The value of the field that was used to search for the resource.
 * @throws ResourceNotFoundException when the resource is not found with the given input data.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue){
        super(String.format("%s not found with given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
