package com.eazybank.accounts.mapper;

import com.eazybank.accounts.dto.AccountsDto;
import com.eazybank.accounts.entity.Accounts;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    
    
        /**
     * Maps the provided {@link AccountsDto} to an {@link Accounts} object by copying the relevant fields.
     *
     * @param accountsDto  The {@link AccountsDto} object to be mapped.
     * @param accounts     The {@link Accounts} object to receive the mapped data.
     * @return              The updated {@link AccountsDto} object with the mapped data.
     *
     * @see AccountsDto
     * @see Accounts
     */
    public static AccountsDto  mapToAccountDto(AccountsDto accountsDto, Accounts accounts){
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }
    
    
        /**
     * Maps the provided {@link AccountsDto} to an {@link Accounts} entity by copying the relevant fields.
     *
     * @param accountsDto  The {@link AccountsDto} object to be mapped.
     * @param accounts     The {@link Accounts} object to receive the mapped data.
     *
     * @return              The updated {@link Accounts} object with the mapped data.
     *
     * @see AccountsDto
     * @see Accounts
     */
    public Accounts mapToAccountEntity(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
