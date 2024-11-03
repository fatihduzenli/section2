package com.eazybank.loans.service;

import com.eazybank.loans.dto.LoanDto;

public interface ILoansService {

    void createLoan(String mobileNumber);

    LoanDto fetchLoanDetails(String mobileNumber);

    boolean updateLoan(LoanDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
