package com.eazybank.accounts.service.impl;

import com.eazybank.accounts.constants.AccountsConstants;
import com.eazybank.accounts.dto.AccountsDto;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.entity.Accounts;
import com.eazybank.accounts.entity.Customer;
import com.eazybank.accounts.exception.CustomerAlreadyExistsException;
import com.eazybank.accounts.exception.ResourceNotFoundException;
import com.eazybank.accounts.mapper.AccountMapper;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import com.eazybank.accounts.service.IAccountService;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final CustomerMapper customerMapper;


    /**
     * This method is used to create a new account for a customer.
     *
     * @param customerDto The DTO containing the customer's details.
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        // Maps the provided customer details to a Customer object.
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        // Checks if a customer already exists with the same mobile number.
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number "
                    + customerDto.getMobileNumber());
        }

        // Sets the creation timestamp and the creator's name.


        // Saves the newly created customer to the database.
        Customer savedCustomer = customerRepository.save(customer);

        // Creates a new account for the saved customer and saves it to the database.
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

        /**
     * This method is used to fetch the account details of a customer based on their mobile number.
     *
     * @param mobileNumber The mobile number of the customer whose account details are to be fetched.
     * @return A DTO containing the customer's details along with their account details.
     * @throws ResourceNotFoundException If the customer or their account is not found in the database.
     */
    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", " mobile number ", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", " customer id ", customer.getCustomerId().toString())
        );

      CustomerDto customerDto=  CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
      customerDto.setAccountsDto(AccountMapper.mapToAccountDto(accounts, new AccountsDto()));
        return customerDto;
    }

        /**
     * This method is used to update the details of an existing account for a customer.
     *
     * @param customerDto The DTO containing the updated customer's details.
     * @return true if the account details are successfully updated, false otherwise.
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccountEntity(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", " mobile number ", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);
      return true;
    }


}
