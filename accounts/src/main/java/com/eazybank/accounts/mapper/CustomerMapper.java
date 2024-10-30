package com.eazybank.accounts.mapper;

import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    
        /**
     * Maps a {@link Customer} entity to a {@link CustomerDto} object.
     *
     * @param customer The {@link Customer} entity to be mapped.
     * @param customerDto The {@link CustomerDto} object to which the entity will be mapped.
     * @return The {@link CustomerDto} object with the mapped data.
     */
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }
    
        /**
     * Maps a {@link CustomerDto} object to a {@link Customer} entity.
     *
     * @param customerDto The {@link CustomerDto} object containing the data to be mapped.
     * @param customer The {@link Customer} entity to which the data will be mapped.
     * @return The {@link Customer} entity with the mapped data.
     */
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
