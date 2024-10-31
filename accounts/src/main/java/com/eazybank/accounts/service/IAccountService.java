package com.eazybank.accounts.service;

import com.eazybank.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto The customer details to create an account for.
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
